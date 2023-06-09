package kr.co._29cm.service;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@RequiredArgsConstructor
public class OrderService {

    private final Scanner sc;

    public Map<Integer, Integer> runOrder() {

        // Step0. 상품 출력
        showProductsList();

        final String OUT_PRODUCT = "상품 번호 : ";
        final String OUT_COUNT = "수량 : ";

        Map<Integer, Integer> cart = new HashMap<>();

        while (true) {
            try {
                Integer itemProduct = parseIntUserScan(OUT_PRODUCT, 10);

                if (itemProduct == -1) {
                    // Step1-2. 결제 (공백 일 경우)
                    // 해당 시점에서 수량 트랜젹션을 확인 하여 (뮤텍스 처럼 동작 하여야함)
                    // 수량을 계산하여 처리
                    showCartList(cart);
                    return cart;
                }

                // Step2. 주문
                Integer itemCount = parseIntUserScan(OUT_COUNT, 5);

                // Step2-1. 카트 주문
                if (itemCount == -1) {
                    System.out.println("주문이 취소 되었습니다.");
                }

                if (cart.containsKey(itemProduct))
                    itemCount += cart.get(itemProduct);

                cart.put(itemProduct, itemCount);

            } catch (Exception e) {
                return null;
            }

            System.out.println("-------");
        }
    }

    private void showCartList(Map<Integer, Integer> cart) {
        System.out.println("주문 내역 :");
        System.out.println("-------");

        for (Integer selectKey : cart.keySet()) {
            System.out.printf("%s, %s%n", selectKey, cart.get(selectKey));
        }
    }

    private void showProductsList() {
        System.out.println("상품 리스트 대강 123444 ");
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
