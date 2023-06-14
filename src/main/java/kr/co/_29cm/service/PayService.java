package kr.co._29cm.service;

import kr.co._29cm.exception.SoldOutException;
import kr.co._29cm.model.ProductInfo;
import kr.co._29cm.repository.ProductRepsitory;

import java.util.HashMap;
import java.util.Map;

public class PayService {

    private final ProductRepsitory inventoryRepsitory = ProductRepsitory.getInstance();

    public synchronized Map<ProductInfo, Integer> buyCart(Map<ProductInfo, Integer> cart) throws SoldOutException {

        Map<ProductInfo, Integer> buyCart = new HashMap<ProductInfo, Integer>(cart);

        for (ProductInfo item : cart.keySet()) {
            Integer count = cart.get(item);
            Integer stock = inventoryRepsitory.findById(item.getId()).getStock();

            if(count > stock)
                throw new SoldOutException(item);

            buyCart.put(item, (stock - count));
        }

        // Step 2. 실 구매
        for (ProductInfo item : buyCart.keySet()) {
            inventoryRepsitory.updateStock(item, buyCart.get(item));
        }

        // Step 3. 결제..

        return cart;
    }
}
