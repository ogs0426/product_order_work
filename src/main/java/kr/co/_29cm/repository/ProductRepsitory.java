package kr.co._29cm.repository;

import kr.co._29cm.model.Product;
import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
public class ProductRepsitory {
    private static ProductRepsitory productRepsitory = null;

    public static ProductRepsitory getInstance() {
        if(productRepsitory == null) {
            productRepsitory = new ProductRepsitory();
        }

        return productRepsitory;
    }

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

    // 구매 API 필요 트랜젹션을 통해서 관리 되어야 한다.(mutex) id 별로 관리 되어야 한다.
}
