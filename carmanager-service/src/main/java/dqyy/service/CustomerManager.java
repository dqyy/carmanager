package dqyy.service;

import dqyy.AccountInfo;
import dqyy.AccountInfoMapper;
import dqyy.MailConstants;
import dqyy.Mailsendlog;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("CustomerManager")


public class CustomerManager {
    @Autowired
    AccountInfoMapper mapper;
    @Autowired
    MailSendLogService ms;
    @Autowired
    RabbitTemplate rb;

    //客户显示
    public List<AccountInfo> findAllCustomer() {
        List<AccountInfo> accountInfos = mapper.selectAll();
        return accountInfos;

    }

    //客户查询
    public AccountInfo findById(Integer id) {
        AccountInfo accountInfo = mapper.selectByPrimaryKey(id);
        return accountInfo;
    }

    //增加客户2
    public int insertCustomer(AccountInfo info) {
        int i = mapper.insertSelective(info);
        return i;
    }

    ;

    //增加客户

    public int insertCustomer(String name, String phone, Byte sex, String wechat, String addr, String email) {
        AccountInfo ac = new AccountInfo();
        ac.setName(name);
        ac.setPhone(phone);
        ac.setSex(sex);
        ac.setWechat(wechat);
        ac.setAddr(addr);
        ac.setEmail(email);
        ac.getId();
        int result = mapper.insertSelective(ac);
        if (result == 1) {
            AccountInfo accountInfo = mapper.selectByPrimaryKey(ac.getId());
        }
        //生成消息的唯一id
        String msgId = UUID.randomUUID().toString();
        Mailsendlog mailSendLog = new Mailsendlog();
        mailSendLog.setMsgid(msgId);
        mailSendLog.setCreatetime(new Date());
        mailSendLog.setExchange(MailConstants.MAIL_EXCHANGE_NAME);
        mailSendLog.setRoutekey(MailConstants.MAIL_ROUTING_KEY_NAME);
        mailSendLog.setEmpid(new Integer(ac.getId()));
        mailSendLog.setTrytime(new Date(System.currentTimeMillis() + 1000 * 60 * MailConstants.MSG_TIMEOUT));
        ms.insert(mailSendLog);
        rb.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME, MailConstants.MAIL_ROUTING_KEY_NAME, ac, new CorrelationData(msgId));
        return result;
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

//    public AccountInfo findById(String msgId) {
//        AccountInfo accountInfo = mapper.selectByPrimaryKey(msgId);
//        return accountInfo;
//    }
}
