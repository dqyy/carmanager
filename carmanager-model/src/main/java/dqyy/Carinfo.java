package dqyy;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Carinfo {
    private Integer id;

    private String car;

    private Date buydate;

    private Integer mileage;

    private String enginecode;

    private String license;

    private Integer accountid;
    //临时增加属性
    private String name;


}