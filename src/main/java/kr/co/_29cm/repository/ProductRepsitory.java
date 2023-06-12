package kr.co._29cm.repository;

import kr.co._29cm.model.Product;
import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
public class ProductRepsitory {

    private final Map<Integer, Product> ProductList = new HashMap<>();

    public Product findById(Integer id) {
        return ProductList.get(id);
    }

    public List<Product> findAll() {
        return new ArrayList<Product>(ProductList.values());
    }

    public void insertItem(Product product) {
        ProductList.put(product.getId(), product);
    }

    public Product deleteItem(Integer id) {
        return ProductList.remove(id);
    }

}
