package kr.co._29cm.repository;

import kr.co._29cm.model.Inventory;
import kr.co._29cm.model.Product;
import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
public class InventoryRepsitory {
    private static final InventoryRepsitory inventoryRepsitory = new InventoryRepsitory();

    public static InventoryRepsitory getInstance() {
        return inventoryRepsitory;
    }

    private final Map<Integer, Inventory> ProductList = new HashMap<>();
    private final Map<Integer, Inventory> Locking = new HashMap<>();

    public Inventory findById(Integer id) {
        return ProductList.get(id);
    }

    public Product findProductById(Integer id) {
        Inventory inventory =  ProductList.get(id);

        if(inventory == null)
            return null;

        return inventory.getProduct();
    }

    public List<Inventory> findAll() {
        return new ArrayList<Inventory>(ProductList.values());
    }

    public void insertItem(Inventory inventory) {
        ProductList.put(inventory.getProduct().getId(), inventory);
    }

    public Inventory deleteItem(Integer id) {
        return ProductList.remove(id);
    }

    // 구매 API 필요 트랜젹션을 통해서 관리 되어야 한다.(mutex) id 별로 관리 되어야 한다.
}
