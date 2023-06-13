package kr.co._29cm.model;

import lombok.Data;

import java.util.List;

@Data
public class Inventory {
    private Product product;
    private Integer stock;

    public Inventory(List<String> list) {

        this.product = Product.builder()
                .id(Integer.valueOf(list.get(0)))
                .name(list.get(1))
                .price(Integer.valueOf(list.get(2)))
                .build();

        this.stock = Integer.valueOf(list.get(3));
    }
}
