package dqyy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hr {
    private Integer id;

    private String username;

    private String password;

    private Integer accountinfoid;
}