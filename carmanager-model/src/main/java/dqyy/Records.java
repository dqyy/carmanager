package dqyy;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Records implements Serializable {
    private static final long serialVersionUID = 1594170358227L;

    private Integer id;

    private Integer accountid;

    private String info;

    private Date date;

    private Integer money;

    private String license;
}