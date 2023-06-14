package kr.co._29cm.repository;

import kr.co._29cm.model.Product;
import kr.co._29cm.model.ProductInfo;
import kr.co._29cm.model.Receipt;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class ReceiptRepsitory {
    private static final ReceiptRepsitory receiptRepsitory = new ReceiptRepsitory();

    public static ReceiptRepsitory getInstance() {
        return receiptRepsitory;
    }

    private final Map<Integer, Receipt> receiptStorage = new HashMap<>();

    public Receipt findById(Integer id) {
        return receiptStorage.get(id);
    }

    public List<Receipt> findAll() {
        return new ArrayList<Receipt>(receiptStorage.values());
    }

    public Receipt insertItem(Receipt item) {
        return receiptStorage.put((receiptStorage.size() + 1), item);
    }
}
