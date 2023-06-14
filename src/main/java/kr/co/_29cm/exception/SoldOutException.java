package kr.co._29cm.exception;

import kr.co._29cm.model.ProductInfo;

public class SoldOutException extends Exception {
    private ProductInfo item;

    public SoldOutException(ProductInfo item) {
        super("주문한 상품량이 재고량보다 큼니다.");
        this.item = item;
    }

}