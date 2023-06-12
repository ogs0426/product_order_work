package kr.co._29cm.service;

import kr.co._29cm.model.Product;
import kr.co._29cm.repository.ProductRepsitory;
import kr.co._29cm.util.CSVReader;

import java.util.List;

public class ProductService {

    private final ProductRepsitory productRepsitory = ProductRepsitory.getInstance();
    
    public void initReadProduct() {
        List<List<String>> readCSV = CSVReader.readCSV("src/main/resources/product.csv");

        for (List<String> proCsv : readCSV) {
            productRepsitory.insertItem(new Product(proCsv));
        }
    }


}
