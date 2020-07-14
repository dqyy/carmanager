package dqyy;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Records {
    private Integer id;

    private Integer accountid;

    private String info;

    private Date date;

    private Integer money;

    private String license;

    private String carname;

    private Integer phone;
}