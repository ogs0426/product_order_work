package kr.co._29cm.service;

import kr.co._29cm.exception.SoldOutException;
import kr.co._29cm.model.Product;
import kr.co._29cm.repository.InventoryRepsitory;

import java.util.HashMap;
import java.util.Map;

public class PayService {
    private static final PayService payService = new PayService();

    public static PayService getInstance() {
        return payService;
    }

    private final InventoryRepsitory inventoryRepsitory = InventoryRepsitory.getInstance();

    public synchronized Boolean buyCart(Map<Product, Integer> cart) throws SoldOutException {

        Map<Product, Integer> buyCart = new HashMap<Product, Integer>(cart);

        // Step 0. 존재 여부 확인

        // Step 1. 재고 확인
        for (Product item : cart.keySet()) {
            Integer count = cart.get(item);
            Integer stock = inventoryRepsitory.findById(item.getId()).getStock();

            if(count > stock)
                throw new SoldOutException(item);

            buyCart.put(item, (stock - count));
        }

        // Step 2. 실 구매
        for (Product item : buyCart.keySet()) {
            inventoryRepsitory.updateStock(item, buyCart.get(item));
        }

        return Boolean.TRUE;
    }
}
