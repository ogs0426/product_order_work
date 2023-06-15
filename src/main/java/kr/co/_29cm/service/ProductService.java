package kr.co._29cm.service;

import kr.co._29cm.exception.SoldOutException;
import kr.co._29cm.model.Product;
import kr.co._29cm.model.ProductInfo;
import kr.co._29cm.model.Receipt;
import kr.co._29cm.repository.ProductRepsitory;
import kr.co._29cm.util.CSVReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductService {

    private final ProductRepsitory productRepsitory = ProductRepsitory.getInstance();

    public void initReadProductInfo() {
        List<List<String>> readCSV = CSVReader.readCSV("src/main/resources/product.csv");
        readCSV.remove(0);

        for (List<String> proCsv : readCSV) {
            productRepsitory.insertItem(new Product(proCsv));
        }
    }

    public synchronized Boolean takeOutCart(Map<ProductInfo, Integer> cart) throws SoldOutException {

        if(cart == null) {
            return false;
        }

        List<Product> stocks = new ArrayList<>();

        // Step 1. 재고 검사
        for (ProductInfo item : cart.keySet()) {
            Integer count = cart.get(item);
            Integer stock = productRepsitory.findById(item.getId()).getStock();

            if(count > stock)
                throw new SoldOutException(item);

            stocks.add(new Product(item, (stock - count)));
        }

        // Step 2. 재고 갱신
        for (Product item : stocks) {
            saveProduct(item);
        }

        return true;
    }

    public List<Product> getAllList() {
        return productRepsitory.findAll();
    }

    public Product getProduct(Integer id) {
        return productRepsitory.findById(id);
    }

    public ProductInfo getProductInfo(Integer id) {
        return productRepsitory.findProductInfoById(id);
    }

    public synchronized void saveProduct(Product item) {
        productRepsitory.insertItem(item);
    }
}
