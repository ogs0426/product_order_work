package kr.co._29cm.service;

import kr.co._29cm.model.Inventory;
import kr.co._29cm.model.Product;
import kr.co._29cm.repository.InventoryRepsitory;
import kr.co._29cm.util.CSVReader;

import java.util.List;

public class ProductService {

    private final InventoryRepsitory inventoryRepsitory = InventoryRepsitory.getInstance();

    public void initReadProduct() {
        List<List<String>> readCSV = CSVReader.readCSV("src/main/resources/product.csv");

        for (List<String> proCsv : readCSV) {
            inventoryRepsitory.insertItem(new Inventory(proCsv));
        }
    }

    public List<Inventory> getAllList() {
        return inventoryRepsitory.findAll();
    }

    public Inventory getInventory(Integer id) {
        return inventoryRepsitory.findById(id);
    }

    public Product getProduct(Integer id) {
        return inventoryRepsitory.findProductById(id);
    }

    public void showProductsList() {
        System.out.println("상품번호    상품명 판매가격    재고수량");

        for (Inventory inven : inventoryRepsitory.findAll()) {
            Product item = inven.getProduct();
            System.out.printf("%d   %s  %d  %d %n", item.getId(), item.getName(), item.getPrice(), inven.getStock());
        }
    }
}
