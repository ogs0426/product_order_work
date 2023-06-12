package kr.co._29cm.service;

import kr.co._29cm.repository.ProductRepsitory;

import java.util.Map;

public class PayService {

    private final ProductRepsitory productRepsitory = ProductRepsitory.getInstance();

    public Boolean buyCart(Map<Integer, Integer> cart) {

        return Boolean.TRUE;
    }
}
