package kr.co._29cm.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Receipt {
    private Map<ProductInfo, Integer> cart;
    private Integer amount = 0;
    private Integer delivery = 0;
}
