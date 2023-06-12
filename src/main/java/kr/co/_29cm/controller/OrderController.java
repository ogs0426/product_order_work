package kr.co._29cm.controller;

import kr.co._29cm.enums.MainRouterType;
import kr.co._29cm.service.OrderService;
import kr.co._29cm.service.PayService;
import kr.co._29cm.service.ProductService;

import java.util.Map;
import java.util.Scanner;

public class OrderController {

    public static void runOrder() {
        ProductService productService = new ProductService();
        productService.initReadProduct();

        Scanner sc = new Scanner(System.in);

        while (true) {

            MainRouterType optParam = null;

            while (optParam == null) {
                System.out.print("입력(o[order]: 주문, q[quit]: 종료) : ");
                optParam = MainRouterType.from(sc.nextLine());
            }

            switch (optParam) {

                case ORDER -> {
                    // Step 1. 주문
                    OrderService orderService = new OrderService(sc);

                    Map<Integer, Integer> cart = orderService.getOrderCartList();

                    // Step 2. 구매
                    PayService payService = new PayService();

                    Boolean checkPay = payService.buyCart(cart);
                }

                case QUIT -> {
                    System.out.println("고객님의 주문 감사합니다.");

                    sc.close();

                    System.exit(0);
                }

                default -> {
                }
            }

            System.out.println();
        }

    }
}
