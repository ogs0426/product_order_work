package kr.co._29cm.service;

import kr.co._29cm.model.Product;
import kr.co._29cm.model.ProductInfo;
import kr.co._29cm.repository.ProductRepsitory;
import kr.co._29cm.util.CSVReader;

import java.util.List;

public class ProductService {

    private final ProductRepsitory inventoryRepsitory = ProductRepsitory.getInstance();

    public void initReadProductInfo() {
        List<List<String>> readCSV = CSVReader.readCSV("src/main/resources/product.csv");

        for (List<String> proCsv : readCSV) {
            inventoryRepsitory.insertItem(new Product(proCsv));
        }
    }

    public List<Product> getAllList() {
        return inventoryRepsitory.findAll();
    }

    public Product getProduct(Integer id) {
        return inventoryRepsitory.findById(id);
    }

    public ProductInfo getProductInfo(Integer id) {
        return inventoryRepsitory.findProductInfoById(id);
    }
}
