package kr.co._29cm.service;

import kr.co._29cm.model.ProductInfo;
import kr.co._29cm.model.Receipt;
import kr.co._29cm.repository.ReceiptRepsitory;

import java.util.List;
import java.util.Map;

public class PayService {

    private final ReceiptRepsitory receiptRepsitory = ReceiptRepsitory.getInstance();

    public synchronized void payCart(Receipt receipt) {
        receiptRepsitory.insertItem(receipt);
    }

    public Receipt generateReceipt(Map<ProductInfo, Integer> cart, Long userId) {
        Receipt receipt = new Receipt();
        int amount = 0;

        // Step 1. 재고 검사
        for (ProductInfo item : cart.keySet())
            amount += (item.getPrice() * cart.get(item));

        // Step 2. 배송비 설정
        if (0 < amount && amount < 50000)
            receipt.setDelivery(2500);

        receipt.setUserid(userId);
        receipt.setTime(System.currentTimeMillis());
        receipt.setCart(cart);
        receipt.setAmount(amount);

        return receipt;
    }

    public List<Receipt> getAllList() {
        return receiptRepsitory.findAll();
    }

}
