package dqyy;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface MailsendlogMapper {
    int deleteByPrimaryKey(String msgid);

    int insert1(Mailsendlog record);

    int insertSelective(Mailsendlog record);

    Mailsendlog selectByPrimaryKey(String msgid);

    int updateByPrimaryKeySelective(Mailsendlog record);

    int updateByPrimaryKey(Mailsendlog record);

    Integer updateMailSendLogStatus(@Param("msgId") String msgId, @Param("status") Integer status);

    Integer insert(Mailsendlog mailSendLog);

    List<Mailsendlog> getMailSendLogsByStatus();

    Integer updateCount(@Param("msgId") String msgId, @Param("date") Date date);

}