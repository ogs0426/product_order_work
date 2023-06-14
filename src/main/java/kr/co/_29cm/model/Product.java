package kr.co._29cm.model;

import lombok.Data;

import java.util.List;

@Data
public class Product {
    private ProductInfo productInfo;
    private Integer stock;

    public Product(List<String> list) {
        
        this.productInfo = ProductInfo.builder()
                .id(Integer.valueOf(list.get(0)))
                .name(list.get(1))
                .price(Integer.valueOf(list.get(2)))
                .build();

        this.stock = Integer.valueOf(list.get(3));
    }

    public Product(ProductInfo productInfo, Integer stock) {
        this.productInfo = productInfo;
        this.stock = stock;
    }
}
