package kr.co._29cm.controller;

import kr.co._29cm.exception.SoldOutException;
import kr.co._29cm.model.Product;
import kr.co._29cm.model.ProductInfo;
import kr.co._29cm.model.Receipt;
import kr.co._29cm.service.PayService;
import kr.co._29cm.service.ProductService;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@RequiredArgsConstructor
public class OrderController {

    private final Scanner sc;
    private final ProductService productService;
    private final PayService payService;

    public void runOrder(Long userId) {
        try {
            showProductInfosList();
            Map<ProductInfo, Integer> cart = getOrderCartList();

            if(productService.takeOutCart(cart)) {
                Receipt receipt = payService.generateReceipt(cart, userId);

                payService.payCart(receipt);
                showReceipt(receipt);
            }

            System.out.println("");

        } catch (SoldOutException e) {
            System.out.println("SoldOutException 발생. 주문한 상품량이 재고량보다 큼니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Map<ProductInfo, Integer> getOrderCartList() {
        final String OUT_PRODUCT = "상품 번호 : ";
        final String OUT_COUNT = "수량 : ";

        Map<ProductInfo, Integer> productInfoCart = new HashMap<>();

        while (true) {
            try {
                Integer itemProductInfo = parseIntUserScan(OUT_PRODUCT, 10);

                if (itemProductInfo == -1)
                    return productInfoCart;

                ProductInfo choice = productService.getProductInfo(itemProductInfo);

                if(choice == null) {
                    System.out.println("존재 하지 않는 상품 입니다.");
                } else {
                    Integer itemCount = parseIntUserScan(OUT_COUNT, 5);

                    if (itemCount == -1) {
                        System.out.println("주문이 취소 되었습니다.");
                    } else {
                        productInfoCart.put(choice, itemCount);
                    }

                }
            } catch (Exception e) {
                return null;
            }

            System.out.println("-------");
        }
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

    private void showProductInfosList() {
        System.out.println("상품번호    상품명 판매가격    재고수량");

        for (Product product : productService.getAllList()) {
            ProductInfo item = product.getProductInfo();
            System.out.printf("%d   %s  %d  %d %n", item.getId(), item.getName(), item.getPrice(), product.getStock());
        }
    }

    private void showReceipt(Receipt receipt) {
        System.out.println("-------");
        System.out.println("주문 내역 :");

        for (ProductInfo item : receipt.getCart().keySet()) {
            System.out.printf("%s - %d 개 %n", item.getName(), receipt.getCart().get(item));
        }

        System.out.println("-------");
        System.out.printf("주문 금액 : %s 원 %n", amountComma(receipt.getAmount()));

        if(receipt.getDelivery() != 0)
            System.out.printf("배송비 : %s 원 %n", amountComma(receipt.getDelivery()));

        System.out.println("-------");
        System.out.printf("지불 금액 : %s 원 %n", amountComma(receipt.getAmount() + receipt.getDelivery()));
    }

    private String amountComma(int amount) {
        return String.valueOf(amount).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
    }
}
