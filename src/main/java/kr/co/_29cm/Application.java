package kr.co._29cm;

import kr.co._29cm.controller.OrderController;
import kr.co._29cm.enums.MainRouterType;
import kr.co._29cm.model.Receipt;
import kr.co._29cm.service.PayService;
import kr.co._29cm.service.ProductService;
import kr.co._29cm.util.Common;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        ProductService productService = new ProductService();
        PayService payService = new PayService();
        Scanner sc = new Scanner(System.in);

        // 유저 ID는 랜덤 하게..
        Long userId = Common.genderRandom(50);

        productService.initReadProductInfo();

        while (true) {

            MainRouterType optParam = null;

            while (optParam == null) {
                System.out.print("입력(o[order]: 주문, q[quit]: 종료) : ");
                optParam = MainRouterType.from(sc.nextLine());
            }

            switch (optParam) {

                case ORDER -> {
                    OrderController orderController = new OrderController(sc, productService, payService);
                    orderController.runOrder(userId);
                }

                case RECEIPT -> {
                    System.out.println("");

                    for (Receipt receipt : payService.getAllList()) {
                        System.out.printf("User : %d, Time : %d, Cart : %s, Amount : %d, Delivery : %d \n", receipt.getUserid(), receipt.getTime(), receipt.getCart().toString(), receipt.getAmount(), receipt.getDelivery());
                    }
                }

                case QUIT -> {
                    System.out.println("고객님의 주문 감사합니다.");

                    sc.close();
                    System.exit(0);
                }

                default -> {
                }
            }
        }
    }

    // 영수증 처리 끝
    
    // Unit Test 작성

    // test 가능한 단위로 기능 메소드 분리

    // README.md 작성

    // 동기화 관리 부분에 한하여 수정 가능 할 경우 할 것
}
