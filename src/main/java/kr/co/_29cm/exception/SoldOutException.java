package kr.co._29cm.exception;

import kr.co._29cm.model.Product;

public class SoldOutException extends Exception {
    private Product item;

    public SoldOutException(Product item) {
        super("주문한 상품량이 재고량보다 큼니다.");
        this.item = item;
    }

}