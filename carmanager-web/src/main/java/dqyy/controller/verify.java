//package dqyy.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Controller
//public class verify {
//    @Autowired
//    dqyy.utils.yanzhengma yanzhengma;
////code
//@GetMapping("/verify")
//@ResponseBody
//    public void createcode(HttpServletRequest request, HttpServletResponse response){
//        Object[] image = yanzhengma.createImage();
//        Cookie co = new Cookie("verify",image[0].toString());
//        response.addCookie(co);
////        return (byte[])image[0];
//    }
//
////pic
//    @GetMapping("/verify")
//    public byte[] createpic(){
//        Object[] image = yanzhengma.createImage();
//        return (byte[])image[1];
//    }
//}
