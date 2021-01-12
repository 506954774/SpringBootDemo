package com.ilinklink.spring_boot.action;

import com.ilinklink.spring_boot.service.MemberService;
import com.ilinklink.spring_boot.utils.WordUtil;
import com.ilinklink.spring_boot.web.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.HTML;


/**
 * Created by Jason on 2019/11/4.
 */
@RestController
@RequestMapping("/word")
public class OfficeWordAction extends ServerExceptionHandler{

    @Autowired
    private MemberService memberService;




    //@PostMapping("export")
    @GetMapping("export")
    public ResponseEntity excelPickup(
                                //@RequestBody WordContentParams param,
                                      HttpServletRequest req,
                                      HttpServletResponse res){
        try {

            /**
             * @des:
               模拟富文本生成word
             * @date 创建时间:2021/1/12
             * @version 1.0.0
             * @author Chuck
             **/
            String  content="<p style=\"text-align:center;\">\n" +
                    "\t<b>&nbsp;<span style=\"font-size:24px;\">光明-指标word演示</span></b>\n" +
                    "</p>\n" +
                    "<p style=\"text-align:left;\">\n" +
                    "\t<b><span style=\"color:#E53333;\">&nbsp; 1，第一个指标：八大行业</span></b>\n" +
                    "</p>\n" +
                    "<p>\n" +
                    "\t<b><span style=\"color:#009900;\">&nbsp; 2，第二个指标：增长率</span></b> \n" +
                    "</p>\n" +
                    "<p>\n" +
                    "\t<b><span style=\"color:#003399;\">&nbsp; 3，第三个指标：增长值</span></b> \n" +
                    "</p>";
            // content=param.getContent();



            StringBuffer sbf = new StringBuffer();
            sbf.append("<html><body>");//缺失的首标签
            sbf.append(content);//富文本内容
            sbf.append("</body></html>");//缺失的尾标签



            WordUtil.exportWord(req,res,sbf.toString(),"文档");
            System.out.println("预约取件导出成功");
            return new ResponseEntity<>("500", true, "");

        } catch (Exception e) {
            System.out.println("预约取件导出異常："+e);
            return new ResponseEntity<>("500", false, e.getMessage());
        }
    }
}
