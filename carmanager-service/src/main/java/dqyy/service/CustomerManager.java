package dqyy.service;

import dqyy.AccountInfo;
import dqyy.AccountInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("CustomerManager")
public class CustomerManager {
    @Autowired
    AccountInfoMapper mapper;

    //客户显示
    public List<AccountInfo> findAllCustomer() {
        List<AccountInfo> accountInfos = mapper.selectAll();
        return accountInfos;

    }

    //客户查询
    public AccountInfo findById(byte id) {
        AccountInfo accountInfo = mapper.selectByPrimaryKey(id);
        return accountInfo;
    }

    //增加客户
    public int insertCustomer(String name, String phone, Byte sex, String wechat, String addr, String email) {
        AccountInfo ac = new AccountInfo();
        ac.setName(name);
        ac.setPhone(phone);
        ac.setSex(sex);
        ac.setWechat(wechat);
        ac.setAddr(addr);
        ac.setEmail(email);
        return mapper.insertSelective(ac);
    }

    //客户信息修改
    public int updataByid(AccountInfo act) {
        return mapper.updateByPrimaryKeySelective(act);
    }

    //删除客户(慎)
    @Transactional
    public int deleteByid(Byte id) {
        return mapper.deleteByPrimaryKey(id);
    }

}
