package dqyy.controller;

import com.alibaba.fastjson.JSON;
import dqyy.AccountInfo;
import dqyy.service.CarManager;
import dqyy.service.CustomerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/cus")

public class CustomController {
    @Autowired
    CustomerManager cus;

    //查找信息
    @PostMapping("/accinfo")
    public String acinfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        } else {
            Object id = session.getAttribute("accountid");
            AccountInfo byId = cus.findById((Integer) id);
            return JSON.toJSONString(byId);
        }
    }

    //修改信息
    @PostMapping("/accxgxx")
    public String accxgxx(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        } else {
            Object accountid = session.getAttribute("accountid");
            AccountInfo a1 = new AccountInfo();
            a1.setId((Integer) accountid);
            a1.setName(request.getParameter("name"));
            a1.setAddr(request.getParameter("addr"));
            a1.setPhone(request.getParameter("phone"));
            a1.setSex(Byte.valueOf(request.getParameter("sex")));
            a1.setWechat(request.getParameter("wechat"));
            int i = cus.updataByid(a1);
            System.out.println(a1);
            if (i != 0) {
                return "ok";
            }
            return "no";

        }
    }

}
