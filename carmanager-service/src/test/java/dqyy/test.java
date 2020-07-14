package dqyy;

import dqyy.service.CustomerManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class test {
    @Autowired
    CustomerManager cus;
    @Autowired
    CarinfoMapper car;

    @Test
    public void dddd() {
        cus.insertCustomer("李四", "123123213", new Byte("1"), "weixin333", "dasdwwad", "4545@qq.com");
    }

}
