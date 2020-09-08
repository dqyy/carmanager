package dqyy.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import dqyy.Carinfo;
import dqyy.Hr;
import dqyy.service.CarManager;
import dqyy.service.Detai;
import dqyy.utils.GsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class CarController {
    @Autowired
    CarManager carManager;

    @GetMapping(value = "/cars", produces = {"application/json;charset=UTF-8"})
    public String getAllHrs() {

        List<Carinfo> all = carManager.findAll();
        int count = all.size();
        String code = "{\"code\":0,\"msg\":\"\",\"count\":" + count + ",\"data\":";
        String s = JSON.toJSONString(all);
        return code + s + "}";
    }


    @GetMapping(value = "/findcar", produces = {"application/json;charset=UTF-8"})
    public List<Carinfo> getByliscen(String lis) {
        return carManager.findLikeByLiscen(lis);
    }

    @PostMapping(value = "/add", produces = {"application/json;charset=UTF-8"})
    public void addcar(String addr, String lic, Integer mileage, Date buydate, String car, String code, Integer accountid) {
        carManager.insertCar(addr, lic, mileage, buydate, car, code, accountid);
    }


    @GetMapping(value = "/del", produces = {"application/json;charset=UTF-8"})
    public void delete(String addr) {
        carManager.deleteCar(addr);
    }


    //    @RequestMapping(value = "/accarinfo/{accid}",produces = "text/plain;charset=utf-8")
    @GetMapping("/accarinfo")
    @ResponseBody
//   @PathVariable("accid" )
    public String findByacc(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Object id = session.getAttribute("accountid");
        Object name = session.getAttribute("username");
//       System.out.println(id);
        System.out.println(name);
        List<Carinfo> carinfos = carManager.selectByAccountId((Integer) id);
        List<Carinfo> c = new ArrayList<Carinfo>();
        for (Carinfo carinfo : carinfos) {
            Integer accountid = carinfo.getAccountid();
            if (id == accountid) {
                c.add(carinfo);
            }
        }
        int count = c.size();
        String code = "{\"code\":0,\"msg\":\"\",\"count\":" + count + ",\"data\":";
        String s = JSON.toJSONString(c);
        return code + s + "}";
    }
}
