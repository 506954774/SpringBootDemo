package com.ilinklink.spring_boot.utils;

import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.ByteArrayInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * WordUtil
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2021/1/12  14:31
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
public class WordUtil {

    public static void exportWord(HttpServletRequest request, HttpServletResponse response, String content, String fileName) throws Exception {

      byte gbks[] = content.getBytes("GBK"); //这里是必须要设置编码的，不然导出中文就会乱码。

      ByteArrayInputStream bais = new ByteArrayInputStream(gbks);//将字节数组包装到流中

      POIFSFileSystem poifs = new POIFSFileSystem();

      DirectoryEntry directory = poifs.getRoot();

      DocumentEntry documentEntry = directory.createDocument("WordDocument", bais); //该步骤不可省略，否则会出现乱码。

      //输出文件

      request.setCharacterEncoding("utf-8");

      response.setContentType("application/msword");//导出word格式

      response.addHeader("Content-Disposition", "attachment;filename=" +

              new String(fileName.getBytes("GB2312"), "iso8859-1") + ".doc");

      ServletOutputStream ostream = response.getOutputStream();

      poifs.writeFilesystem(ostream);

      bais.close();

      ostream.close();

      poifs.close();

    }


}
