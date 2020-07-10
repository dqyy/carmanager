package dqyy.service;

import com.google.gson.JsonParser;
import dqyy.Carinfo;
import dqyy.CarinfoMapper;
import dqyy.utils.baiduCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@CacheConfig(cacheNames = "selectCar")
public class CarManager {
    @Autowired
    CarinfoMapper carinfoMapper;
    @Autowired
    baiduCar b;


    String s = null;
    String chepai = null;


    //查询车辆
    @Cacheable(value = "seleceCar", key = "#{addr}")
    public Carinfo findCar(String addr) {
        s = b.licensePlate(addr);
        chepai = new JsonParser().parse(s).getAsJsonObject().get("words_result").getAsJsonObject().get("number").toString();
        return carinfoMapper.selectByLicense(chepai);
    }

    //车辆录入 判断是否有重复车牌
    public int insertCar(String addr, String lic, Integer mileage, Date buydate, String car, String code) {
        s = b.licensePlate(addr);
        chepai = new JsonParser().parse(s).getAsJsonObject().get("words_result").getAsJsonObject().get("number").toString();

        Carinfo carinfo = new Carinfo();
        if (addr == null || lic != null) {

            carinfo.setLicense(lic);
            carinfo.setMileage(mileage);
            carinfo.setBuydate(buydate);
            carinfo.setCar(car);
            carinfo.setEnginecode(code);
            Carinfo fla = carinfoMapper.selectByLicense(lic);
            if (fla.getLicense() != null) {
                if (carinfoMapper.insertSelective(carinfo) != 0) {
                    return 1;
                }
                return 0;
            }
            return 0;
        }
        if (chepai != null) {
            carinfo.setLicense(chepai);
            carinfo.setMileage(mileage);
            carinfo.setBuydate(buydate);
            carinfo.setCar(car);
            carinfo.setEnginecode(code);
            Carinfo fla = carinfoMapper.selectByLicense(chepai);
            if (fla.getLicense() != null) {
                if (carinfoMapper.insertSelective(carinfo) != 0) {
                    return 1;
                }
                return 0;
            }
            return 0;
        }
        return 0;
    }

    //车辆信息修改
    public int updataCar(String addr, Integer mileage, String car, String code) {
        Carinfo car1 = findCar(addr);
        car1.setMileage(mileage);
        car1.setCar(car);
        car1.setEnginecode(code);
        int i = carinfoMapper.updateByPrimaryKeySelective(car1);
        if (i != 0) {
            return 1;
        }
        return 0;
    }

    //删除车辆
    @Transactional
    public int deleteCar(String addr) {
        Integer id = findCar(addr).getId();
        int i = carinfoMapper.deleteByPrimaryKey(id);
        if (i != 0) {
            return 1;
        }
        return 0;
    }


}
