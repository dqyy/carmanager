package dqyy;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mailsendlog {
    private String msgid;

    private Integer empid;

    private Integer status;

    private String routekey;

    private String exchange;

    private Integer count;

    private Date trytime;

    private Date createtime;

    private Date updatetime;
}