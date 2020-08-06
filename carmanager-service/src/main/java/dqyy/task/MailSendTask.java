package dqyy.task;

import dqyy.AccountInfo;
import dqyy.MailConstants;
import dqyy.Mailsendlog;
import dqyy.service.CustomerManager;
import dqyy.service.MailSendLogService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class MailSendTask {
    @Autowired
    MailSendLogService mailSendLogService;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    CustomerManager cus;

    @Scheduled(cron = "0/10 * * * * ?")
    public void mailResendTask() {
        List<Mailsendlog> logs = mailSendLogService.getMailSendLogsByStatus();
        if (logs == null || logs.size() == 0) {
            return;
        }
        logs.forEach(mailSendLog -> {
            if (mailSendLog.getCount() >= 3) {
                mailSendLogService.updateMailSendLogStatus(mailSendLog.getMsgid(), 2);//直接设置该条消息发送失败
            } else {
                mailSendLogService.updateCount(mailSendLog.getMsgid(), new Date());
                AccountInfo byId = cus.findById(new Byte(String.valueOf(mailSendLog.getEmpid())));
                rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME, MailConstants.MAIL_ROUTING_KEY_NAME, byId, new CorrelationData(mailSendLog.getMsgid()));
            }
        });
    }
}
