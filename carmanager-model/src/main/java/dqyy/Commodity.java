package dqyy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commodity {
    private Integer id;

    private String number;

    private String name;

    private Integer price;

    private Integer stock;

    private String image;
}