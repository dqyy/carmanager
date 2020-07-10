package dqyy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountInfo {
    private static final long serialVersionUID = 1594170358227L;
    private Byte id;

    private String name;

    private Byte sex;

    private String phone;

    private String addr;

    private String email;

    private String wechat;
}