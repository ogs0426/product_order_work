package kr.co._29cm.repository;

import kr.co._29cm.model.Product;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class ProductRepsitory {

    private final Map<Integer, Product> ProductList = new HashMap<>();

    public Product findById(Integer id) {
        return ProductList.get(id);
    }

    public Product insertItem(Product product) {
        return ProductList.put(product.getId(), product);
    }

    public Product deleteItem(Integer id) {
        return ProductList.remove(id);
    }


}
