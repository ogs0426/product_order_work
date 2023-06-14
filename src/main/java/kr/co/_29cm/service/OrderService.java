package kr.co._29cm.service;

import kr.co._29cm.model.Inventory;
import kr.co._29cm.model.Product;
import kr.co._29cm.repository.InventoryRepsitory;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@RequiredArgsConstructor
public class OrderService {

    private final Scanner sc;
    private final InventoryRepsitory inventoryRepsitory = InventoryRepsitory.getInstance();

    public Map<Product, Integer> getOrderCartList() {

        // Step0. 상품 출력

        final String OUT_PRODUCT = "상품 번호 : ";
        final String OUT_COUNT = "수량 : ";

        // Map<Integer, Integer> cart = new HashMap<>();
        Map<Product, Integer> productCart = new HashMap<>();

        while (true) {
            try {
                Integer itemProduct = parseIntUserScan(OUT_PRODUCT, 10);

                if (itemProduct == -1) {
                    return productCart;

                } else {
                    Product choice = inventoryRepsitory.findProductById(itemProduct);

                    if(choice == null) {
                        System.out.println("존재 하지 않는 상품 입니다.");
                    } else {

                        // Step2. 주문
                        Integer itemCount = parseIntUserScan(OUT_COUNT, 5);

                        // Step2-1. 카트 주문
                        if (itemCount == -1) {
                            System.out.println("주문이 취소 되었습니다.");
                        } else {
                            productCart.put(choice, itemCount);
                        }

                    }

                }

            } catch (Exception e) {
                return null;
            }

            System.out.println("-------");
        }
    }

    private String amountComma(int amount) {
        return String.valueOf(amount).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
    }

    /*
     * 1. 입력 된 값에 대하여 숫자일 경우 parse
     * 2. 입력 값이 ""일 경우 -1
     */
    private Integer parseIntUserScan(String outputPhrase, int limitCount) throws Exception {
        Integer itemNumberToInt = null;
        int tryCount = 0;

        while (itemNumberToInt == null) {
            try {
                System.out.print(outputPhrase);
                String itemNumber = sc.nextLine();

                if (itemNumber.trim().isEmpty())
                    return -1;

                itemNumberToInt = Integer.valueOf(itemNumber);
            } catch (Exception exception) {
                tryCount++;
                System.out.printf("옳바르지 않은 값입니다. limit : %d, try : %d \n", limitCount, tryCount);

                if (limitCount == tryCount) {
                    System.out.println("옳바르지 않은 요청이 다수 반복 되었습니다. 주문을 취소 합니다.");
                    throw new Exception("Exceeding the limit number of inputs");
                }

            }
        }

        return itemNumberToInt;
    }
}
