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


}
