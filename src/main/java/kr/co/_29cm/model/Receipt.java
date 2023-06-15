package kr.co._29cm.model;

import lombok.Data;

import java.util.Map;

@Data
public class Receipt {
    private Long userid;
    private Long time;

    private Map<ProductInfo, Integer> cart;
    private Integer amount = 0;
    private Integer delivery = 0;
}
