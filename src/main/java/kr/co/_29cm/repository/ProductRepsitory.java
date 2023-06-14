package kr.co._29cm.repository;

import kr.co._29cm.model.Product;
import kr.co._29cm.model.ProductInfo;
import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
public class ProductRepsitory {
    private static final ProductRepsitory productRepsitory = new ProductRepsitory();

    public static ProductRepsitory getInstance() {
        return productRepsitory;
    }

    private final Map<Integer, Product> productInfoStorage = new HashMap<>();

    public Product findById(Integer id) {
        return productInfoStorage.get(id);
    }

    public ProductInfo findProductInfoById(Integer id) {
        Product product =  productInfoStorage.get(id);

        if(product == null)
            return null;

        return product.getProductInfo();
    }

    public List<Product> findAll() {
        return new ArrayList<Product>(productInfoStorage.values());
    }

    public void insertItem(Product product) {
        productInfoStorage.put(product.getProductInfo().getId(), product);
    }

    public void updateStock(ProductInfo item, Integer stock) {
        productInfoStorage.put(item.getId(), new Product(item, stock));
    }

    public Product deleteItem(Integer id) {
        return productInfoStorage.remove(id);
    }

    // 구매 API 필요 트랜젹션을 통해서 관리 되어야 한다.(mutex) id 별로 관리 되어야 한다.
}
