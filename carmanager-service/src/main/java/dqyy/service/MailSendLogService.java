package dqyy.service;

import dqyy.Mailsendlog;
import dqyy.MailsendlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MailSendLogService {
    @Autowired
    MailsendlogMapper mailSendLogMapper;

    //更新消息发送状态
    public Integer updateMailSendLogStatus(String msgId, Integer status) {
        return mailSendLogMapper.updateMailSendLogStatus(msgId, status);
    }

    //插入消息
    public Integer insert(Mailsendlog mailSendLog) {
        return mailSendLogMapper.insert(mailSendLog);
    }

    //获取现在时间段所有的消息
    public List<Mailsendlog> getMailSendLogsByStatus() {
        return mailSendLogMapper.getMailSendLogsByStatus();
    }

    //更新条数
    public Integer updateCount(String msgId, Date date) {
        return mailSendLogMapper.updateCount(msgId, date);
    }
}
