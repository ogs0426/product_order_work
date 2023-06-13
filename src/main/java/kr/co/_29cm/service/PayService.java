package kr.co._29cm.service;

import kr.co._29cm.exception.SoldOutException;
import kr.co._29cm.model.Product;
import kr.co._29cm.repository.InventoryRepsitory;

import java.util.Map;

public class PayService {

    private final InventoryRepsitory productRepsitory = InventoryRepsitory.getInstance();

    public Boolean buyCart(Map<Product, Integer> cart) throws SoldOutException {

        for (Product item : cart.keySet()) {

            if(item != null)
                throw new SoldOutException(item);
        }

        return Boolean.TRUE;
    }
}
