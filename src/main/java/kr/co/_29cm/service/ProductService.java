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

}
