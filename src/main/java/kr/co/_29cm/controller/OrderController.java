package kr.co._29cm.controller;

import kr.co._29cm.enums.MainRouterType;
import kr.co._29cm.exception.SoldOutException;
import kr.co._29cm.model.Product;
import kr.co._29cm.service.OrderService;
import kr.co._29cm.service.PayService;
import kr.co._29cm.service.ProductService;

import java.util.Map;
import java.util.Scanner;

public class OrderController {

    public static void runOrder() {
        ProductService productService = new ProductService();
        PayService payService = PayService.getInstance();
        Scanner sc = new Scanner(System.in);

        productService.initReadProduct();

        while (true) {

            MainRouterType optParam = null;

            while (optParam == null) {
                System.out.print("입력(o[order]: 주문, q[quit]: 종료) : ");
                optParam = MainRouterType.from(sc.nextLine());
            }

            switch (optParam) {

                case ORDER -> {
                    OrderService orderService = new OrderService(sc);

                    productService.showProductsList();
                    Map<Product, Integer> cart = orderService.getOrderCartList();

                    try {
                        Boolean checkPay = payService.buyCart(cart);

                        if (checkPay) {
                            showCartList(cart);
                        }

                        System.out.println("");

                    } catch (SoldOutException e) {
                        System.out.println("SoldOutException 발생. 주문한 상품량이 재고량보다 큼니다.");
                    } catch (Exception e) {
                        e.printStackTrace();
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

            System.out.println();
        }

    }

    // SHOW
    private static void showCartList(Map<Product, Integer> cart) {
        System.out.println("-------");
        System.out.println("주문 내역 :");
        System.out.println("-------");

        int amount = 0;

        for (Product item : cart.keySet()) {
            System.out.printf("%s - %d 개 %n", item.getName(), cart.get(item));

            amount += (item.getPrice() * cart.get(item));
        }

        System.out.println("-------");
        System.out.printf("주문 금액: %s 원 %n", amountComma(amount));

        if(amount < 50000) {
            System.out.printf("배송비: 2,500 원 %n");
            amount += 2500;
        }

        System.out.println("-------");
        System.out.printf("지불 금액: %s 원 %n", amountComma(amount));
    }

    private static String amountComma(int amount) {
        return String.valueOf(amount).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
    }

}
