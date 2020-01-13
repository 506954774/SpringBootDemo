package com.ilinklink.spring_boot.service.impl;

import com.ilinklink.spring_boot.ServerConstants;
import com.ilinklink.spring_boot.exception.AdminErrorCode;
import com.ilinklink.spring_boot.exception.AdminException;
import com.ilinklink.spring_boot.mapper.MemberMapper;
import com.ilinklink.spring_boot.model.JpushParams;
import com.ilinklink.spring_boot.model.RedisGetParams;
import com.ilinklink.spring_boot.model.RedisSetParams;
import com.ilinklink.spring_boot.rabbitMQ.MsgProducer;
import com.ilinklink.spring_boot.service.MemberService;
import com.ilinklink.spring_boot.upload.FastDFSClient;
import com.ilinklink.spring_boot.utils.RedisUtil;
import com.ilinklink.spring_boot.web.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import lombok.extern.slf4j.Slf4j;

import static com.ilinklink.spring_boot.ServerConstants.LOGGER_PREFIX;

/**
 * MemberServiceImpl
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2019/11/28  19:43
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
@Slf4j
@Service
public class MemberServiceImpl extends BaseService implements MemberService {

    private static final String MASTER_SECRET = "5a2aa9def491189d451ef8f4";
    private static final String APP_KEY = "411a51f9642b0b74a620bde3";
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private  MsgProducer MsgProducer;

    @Autowired
    private FastDFSClient fastDFSClient;
    @Value("${dfs.client.host}")
    private String dfsHost;
    @Value("${dfs.max.size}")
    private int maxSize;

    @Override
    public boolean exist(String memberId) throws AdminException {
        if (memberId == null || "".equals(memberId)) {
            log.warn(LOGGER_PREFIX+"参数为空!", "");
            throw new AdminException(AdminErrorCode.PARAMS_EMPTY, getMessage(AdminErrorCode.PARAMS_EMPTY));

        }
        // return true;
        try {
            return memberMapper.queryMemberExist(memberId) > 0;
        } catch (Exception e) {
            log.error(LOGGER_PREFIX+"查询会员是否存在,操作异常!", e);
            return false;
        }
    }

    @Override
    public void redisSet(RedisSetParams params) throws AdminException {
        if (params == null || "".equals(params.getKey())) {
            log.warn(LOGGER_PREFIX+"参数为空!", "");
            throw new AdminException(AdminErrorCode.PARAMS_EMPTY, getMessage(AdminErrorCode.PARAMS_EMPTY));

        }
        try {
            redisUtil.set(params.getKey(), params.getValue());
        } catch (Exception e) {
            log.error(LOGGER_PREFIX+"redis存,操作异常!", e);
            throw e;
        }
    }

    @Override
    public String redisGet(RedisGetParams params) throws AdminException {
        if (params == null || "".equals(params.getKey())) {
            log.warn(LOGGER_PREFIX+"参数为空!", "");
            throw new AdminException(AdminErrorCode.PARAMS_EMPTY, getMessage(AdminErrorCode.PARAMS_EMPTY));

        }
        log.info("key", params.getKey());
        try {
            return redisUtil.get(params.getKey());
        } catch (Exception e) {
            log.error(LOGGER_PREFIX+"redis取,操作异常!", e);
            throw e;
        }
    }

    @Override
    public void jPush(JpushParams params) throws AdminException {

        if (params == null || "".equals(params.getRegistId())||"".equals(params.getTitle())||"".equals(params.getRegistId())) {
            log.warn(LOGGER_PREFIX+"参数为空!", "");
            throw new AdminException(AdminErrorCode.PARAMS_EMPTY, getMessage(AdminErrorCode.PARAMS_EMPTY));
        }

        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());

        // For push, all you need do is to build PushPayload object.
        PushPayload payload = buildPushObject(params.getRegistId(),params.getTitle(),params.getContent());

        try {
            PushResult result = jpushClient.sendPush(payload);
            log.info("Got result - " + result);

        } catch (APIConnectionException e) {
            // Connection error, should retry later
            log.error("Connection error, should retry later", e);

        } catch (APIRequestException e) {
            // Should review the error, and fix the request
            log.error("Should review the error, and fix the request", e);
            log.info("HTTP Status: " + e.getStatus());
            log.info("Error Code: " + e.getErrorCode());
            log.info("Error Message: " + e.getErrorMessage());
        }
    }

    public static PushPayload buildPushObject(String registrationId,String title,String content) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.registrationId(registrationId))
                .setNotification(Notification.android(content, title, null))
                .build();
    }


    @Override
    public String uploadFile(MultipartFile mFile) throws AdminException {
        if (mFile == null) {
            log.warn(LOGGER_PREFIX+"上传内容为空!", "");
            throw new AdminException(AdminErrorCode.UPLOAD_CONTENT_EMPTY, getMessage(AdminErrorCode.UPLOAD_CONTENT_EMPTY));
        }
        if (mFile.getSize() > maxSize * 1024 * 1024) {
            log.warn(ServerConstants.LOGGER_PREFIX + "[文件上传]文件内容过大，上传失败！文件大小[" + mFile.getSize() + "].");
            throw new AdminException(AdminErrorCode.FILE_TOO_LARGE, getMessage(AdminErrorCode.FILE_TOO_LARGE));
        }

        // 获取后缀名
        String originalFileName = mFile.getOriginalFilename();
        String fileType = originalFileName.substring(originalFileName.lastIndexOf(".") + 1).toLowerCase();
        if (!fileType.equalsIgnoreCase("jpeg") && !fileType.equalsIgnoreCase("jpg") && !fileType.equalsIgnoreCase("png")) {
            log.warn(ServerConstants.LOGGER_PREFIX + "[文件上传]文件类型不合法，上传失败！文件类型[" + fileType + "].");
            throw new AdminException(AdminErrorCode.FILE_TYPE_INVALID, getMessage(AdminErrorCode.FILE_TYPE_INVALID));
        }

        try {
            InputStream inputStream = mFile.getInputStream();
            byte[] file_buff = null;
            if (inputStream != null) {
                int len = inputStream.available();
                file_buff = new byte[len];
                inputStream.read(file_buff);
            }

            String fileUrl = fastDFSClient.uploadFile(file_buff, fileType);
            log.info(ServerConstants.LOGGER_PREFIX + "[文件上传]文件上传成功！文件链接url[" + fileUrl + "].");
            return dfsHost + "/" + fileUrl;
        } catch (Exception e) {
            log.error(ServerConstants.LOGGER_PREFIX + "[文件上传]文件上传失败！", e);
            throw new AdminException(AdminErrorCode.REQUEST_EXCEPTION, getMessage(AdminErrorCode.REQUEST_EXCEPTION));
        }
    }

    @Override
    public String uploadFile(HttpServletRequest request) throws AdminException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile mFile = multipartRequest.getFile("file");
        if(mFile==null){
            log.warn(LOGGER_PREFIX+"上传内容为空!", "");
            throw new AdminException(AdminErrorCode.UPLOAD_CONTENT_EMPTY, getMessage(AdminErrorCode.UPLOAD_CONTENT_EMPTY));
        }
        else {
            try {
                return  uploadFile(mFile);
            } catch (AdminException e) {
                throw e;
            }
        }
    }

    @Override
    public List<String> multiImport(HttpServletRequest request) throws AdminException {

        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        if(files==null||files.size()==0){
            log.warn(LOGGER_PREFIX+"上传内容为空!", "");
            throw new AdminException(AdminErrorCode.UPLOAD_CONTENT_EMPTY, getMessage(AdminErrorCode.UPLOAD_CONTENT_EMPTY));
        }
        else {
            List<String> result = new ArrayList<>();
            ResponseEntity<List<String>> responseEntity = new ResponseEntity<>(true);
            for (MultipartFile singleFile:files) {
                try {
                    String url = uploadFile(singleFile);
                    result.add(url);
                } catch (AdminException e) {
                    throw e;
                }
            }
            return result;
        }
    }

    @Override
    public void sendMQ() throws AdminException {
        for (int i = 0; i < 10 ; i++) {
            MsgProducer.sendMsg("TEST");

        }
    }
}
