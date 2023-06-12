package kr.co._29cm.model;

import lombok.Data;

import java.util.List;

@Data
public class Product {

    private Integer id;
    private String name;
    private Integer price;
    private Integer stock;

    public Product(List<String> list) {
        this.id = Integer.valueOf(list.get(1));
        this.name = list.get(2);
        this.price = Integer.valueOf(list.get(3));
        this.stock = Integer.valueOf(list.get(4));
    }
}
