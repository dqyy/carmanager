package dqyy.utils;


import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URLEncoder;

/**
 * 车牌识别
 */
@Configuration
public class baiduCar {

    public String licensePlate(String addr) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/license_plate";

        try {
            // 本地文件路径
            byte[] imgData = FileUtil.readFileByBytes(addr);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam;
            String auth = AuthService.getAuth();


            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = auth;

            String result = HttpUtil.post(url, accessToken, param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static void main(String[] args) {
//        String s = baiduCar.licensePlate("/Users/dqyy/Desktop/timg.jpg");
//        JsonElement number = new JsonParser().parse(s).getAsJsonObject().get("words_result").getAsJsonObject().get("number");
//        System.out.println(number);
//    }
}