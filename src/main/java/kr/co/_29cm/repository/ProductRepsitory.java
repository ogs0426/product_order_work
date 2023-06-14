package kr.co._29cm.repository;

import kr.co._29cm.model.Product;
import kr.co._29cm.model.ProductInfo;
import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
public class ProductRepsitory {
    private static final ProductRepsitory inventoryRepsitory = new ProductRepsitory();

    public static ProductRepsitory getInstance() {
        return inventoryRepsitory;
    }

    private final Map<Integer, Product> ProductInfoStorage = new HashMap<>();

    public Product findById(Integer id) {
        return ProductInfoStorage.get(id);
    }

    public ProductInfo findProductInfoById(Integer id) {
        Product inventory =  ProductInfoStorage.get(id);

        if(inventory == null)
            return null;

        return inventory.getProductInfo();
    }

    public List<Product> findAll() {
        return new ArrayList<Product>(ProductInfoStorage.values());
    }

    public void insertItem(Product inventory) {
        ProductInfoStorage.put(inventory.getProductInfo().getId(), inventory);
    }

    public void updateStock(ProductInfo item, Integer stock) {
        ProductInfoStorage.put(item.getId(), new Product(item, stock));
    }

    public Product deleteItem(Integer id) {
        return ProductInfoStorage.remove(id);
    }

    // 구매 API 필요 트랜젹션을 통해서 관리 되어야 한다.(mutex) id 별로 관리 되어야 한다.
}
