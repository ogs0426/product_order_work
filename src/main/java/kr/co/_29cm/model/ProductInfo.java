package kr.co._29cm.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductInfo {
    private Integer id;
    private String name;
    private Integer price;

    public ProductInfo(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
