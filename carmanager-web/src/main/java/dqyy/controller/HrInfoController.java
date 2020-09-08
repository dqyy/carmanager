package dqyy.controller;

import com.alibaba.fastjson.JSON;
import dqyy.AccountInfo;
import dqyy.Hr;
import dqyy.RespBean;
import dqyy.service.CustomerManager;
import dqyy.service.Detai;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @作者 江南一点雨
 * @公众号 江南一点雨
 * @微信号 a_java_boy
 * @GitHub https://github.com/lenve
 * @博客 http://wangsong.blog.csdn.net
 * @网站 http://www.javaboy.org
 * @时间 2020-03-01 13:07
 */
@Controller
public class HrInfoController {

    @Autowired
    Detai hr;
    @Autowired
    CustomerManager cus;

    @Value("${fastdfs.nginx.host}")
    String nginxHost;

    //    @GetMapping("/hr/info")
//    public Hr getCurrentHr(Authentication authentication) {
//        return ((Hr) authentication.getPrincipal());
//    }

    //首页跳转
    @RequestMapping(value = "/", produces = "text/plain;charset=utf-8")
    public String no1() {

        return "redirect:http://127.0.0.1:8082/carm/login.html";

    }

    //登录验证
    @RequestMapping(value = "carm/startlogin", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String login(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String msg = null;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String verifycode = request.getParameter("verifycode");
        String verify_code = session.getAttribute("verify_code").toString();
//        System.out.println(verifycode);
        if (verify_code.equalsIgnoreCase(verifycode)) {
            Hr hr = this.hr.cheackUserAndPasswd(username, password);

            if (hr != null) {

                session.setAttribute("username", hr.getUsername());
                session.setAttribute("password", password);
                session.setAttribute("accountid", hr.getAccountinfoid());
                System.out.println(hr.getAccountinfoid());
                session.setMaxInactiveInterval(3360);
                msg = "ok";

            } else {
                msg = "no";

            }


        } else {
            msg = "verifycode";
        }
        System.out.println(msg);
        return msg;
    }

    //判断session
    @RequestMapping(value = "/panduan", produces = "text/plain;charset=utf-8")
    @ResponseBody

    public String panduan(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session != null) {
            if (session.getAttribute("username") != null) {
                System.out.println("可以登录");

                return "ok";
            }

            return "no";

        }
        System.out.println("不能登录");
        return "no";
    }

    //获取用户名和ID
    @RequestMapping(value = "/getname", produces = "text/plain;charset=utf-8")
    @ResponseBody

    public String getName(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {
            if (session.getAttribute("username") == null) {
                return "no";
            }
            return session.getAttribute("username").toString();

        }
        return "no";
    }


    //退出
    @RequestMapping(value = "/quit", produces = "text/plain;charset=utf-8")
    @ResponseBody

    public String quit(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session == null) {
            return "ok";
        } else {
            session.invalidate();
            return "ok";
        }

    }


    //    @PutMapping("/hr/info")
//    public RespBean updateHr(@RequestBody Hr hr, Authentication authentication) {
//        if (hr.updateHr(hr) == 1) {
//            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(hr, authentication.getCredentials(), authentication.getAuthorities()));
//            return RespBean.ok("更新成功!");
//        }
//        return RespBean.error("更新失败!");
//    }
    //注册
    @PostMapping(value = "/hr/register", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String insertHr(HttpServletRequest request) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String s = hr.cheackUser(username);
        if (s.equals("ok")) {
            String addr = request.getParameter("addr");
            String email = request.getParameter("email");
            String wechat = request.getParameter("wechat");
            String sex = request.getParameter("sex");
            String name = request.getParameter("name");
            Hr hrr = new Hr();
            hrr.setPassword(password);
            hrr.setUsername(username);
            AccountInfo accountInfo = new AccountInfo();
            accountInfo.setAddr(addr);
            accountInfo.setEmail(email);
            accountInfo.setName(name);
            accountInfo.setSex(Byte.valueOf(sex));
            accountInfo.setWechat(wechat);
            int i = cus.insertCustomer(accountInfo);
            Integer id = accountInfo.getId();
            hrr.setAccountinfoid(id);
            int x = hr.insertHr(hrr);
            if (i == 0 && x == 0) {
                RespBean.error("更新失败!");
                return "no";
            }
            RespBean.ok("更新成功!");
            System.out.println("ok");
            HttpSession session = request.getSession(false);
            session.setAttribute("newusername", username);
            return "ok";
        }
        System.out.println("no");
        return "no";
    }


    @PostMapping("/hr/newuser")
    @ResponseBody
    public String newuser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            System.out.println("没有新用户注册");
            return null;
        }
        Object newusername = session.getAttribute("newusername");
        String s = JSON.toJSONString(newusername);
        return s;
    }


    //修改密码
    @PutMapping("/hr/pass")
    public RespBean updateHrPasswd(@RequestBody Map<String, Object> info) {
        String oldpass = (String) info.get("oldpass");
        String pass = (String) info.get("pass");
        Integer hrid = (Integer) info.get("hrid");
        if (hr.UpdataPassword(hrid, oldpass, pass)) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

//    @PostMapping("/hr/userface")
//    public RespBean updateHrUserface(MultipartFile file, Integer id, Authentication authentication) {
//        String fileId = FastDFSUtils.upload(file);
//        String url = nginxHost + fileId;
//        if (hrService.updateUserface(url, id) == 1) {
//            Hr hr = (Hr) authentication.getPrincipal();
//            hr.setUserface(url);
//            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(hr, authentication.getCredentials(), authentication.getAuthorities()));
//            return RespBean.ok("更新成功!", url);
//        }
//        return RespBean.error("更新失败!");
//    }

    //查询个人信息


}