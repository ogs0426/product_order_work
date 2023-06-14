package kr.co._29cm.service;

import kr.co._29cm.exception.SoldOutException;
import kr.co._29cm.model.ProductInfo;
import kr.co._29cm.model.Receipt;
import kr.co._29cm.repository.ProductRepsitory;
import kr.co._29cm.repository.ReceiptRepsitory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PayService {

    private final ProductRepsitory productRepsitory = ProductRepsitory.getInstance();
    private final ReceiptRepsitory receiptRepsitory = ReceiptRepsitory.getInstance();

    public synchronized Receipt takeOutCart(Map<ProductInfo, Integer> cart) throws SoldOutException {

        Map<ProductInfo, Integer> stocks = new HashMap<ProductInfo, Integer>(cart);
        Receipt receipt = new Receipt();
        int amount = 0;

        // Step 1. 재고 검사
        for (ProductInfo item : cart.keySet()) {
            Integer count = cart.get(item);
            Integer stock = productRepsitory.findById(item.getId()).getStock();

            if(count > stock)
                throw new SoldOutException(item);

            stocks.put(item, (stock - count));
            amount += (item.getPrice() * cart.get(item));
        }

        // Step 2. 재고 갱신
        for (ProductInfo item : stocks.keySet()) {
            productRepsitory.updateStock(item, stocks.get(item));
        }

        // Step 3. 배송비 설정
        if (0 < amount && amount < 50000) {
            receipt.setDelivery(2500);
        }

        receipt.setCart(cart);
        receipt.setAmount(amount);

        return receipt;
    }

    public synchronized void payCart(Receipt receipt) {
        receiptRepsitory.insertItem(receipt);

    }

    public List<Receipt> getAllList() {
        return receiptRepsitory.findAll();
    }

}
