package kr.co._29cm.service;

import kr.co._29cm.exception.SoldOutException;
import kr.co._29cm.model.ProductInfo;
import kr.co._29cm.repository.ProductRepsitory;

import java.util.HashMap;
import java.util.Map;

public class PayService {

    private final ProductRepsitory productRepsitory = ProductRepsitory.getInstance();

    public synchronized Map<ProductInfo, Integer> takeOutCart(Map<ProductInfo, Integer> cart) throws SoldOutException {

        Map<ProductInfo, Integer> stocks = new HashMap<ProductInfo, Integer>(cart);

        // Step 1. 재고 검사
        for (ProductInfo item : cart.keySet()) {
            Integer count = cart.get(item);
            Integer stock = productRepsitory.findById(item.getId()).getStock();

            if(count > stock)
                throw new SoldOutException(item);

            stocks.put(item, (stock - count));
        }

        // Step 2. 재고 갱신
        for (ProductInfo item : stocks.keySet()) {
            productRepsitory.updateStock(item, stocks.get(item));
        }

        return cart;
    }

}
