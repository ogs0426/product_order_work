package kr.co._29cm.repository;

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

    private final List<Receipt> receiptStorage = new ArrayList<>();

    public List<Receipt> findAll() {
        return receiptStorage;
    }

    public void insertItem(Receipt item) {
        receiptStorage.add(item);
    }
}
