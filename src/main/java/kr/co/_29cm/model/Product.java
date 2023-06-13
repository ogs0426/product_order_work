package kr.co._29cm.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {

    private Integer id;
    private String name;
    private Integer price;
}
