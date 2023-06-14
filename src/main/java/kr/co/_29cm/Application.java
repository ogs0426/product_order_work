package kr.co._29cm;

import kr.co._29cm.controller.OrderController;
import kr.co._29cm.enums.MainRouterType;
import kr.co._29cm.service.PayService;
import kr.co._29cm.service.ProductService;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        ProductService productService = new ProductService();
        PayService payService = new PayService();

        Scanner sc = new Scanner(System.in);

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
                    orderController.runOrder();
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

    // -------------

    // sudo 코드 정리 (완)

    // 터미널로 명령 받아야 함 (완)

    // -------------

    // H2 데이터 베이스 설정 이후 트랜젹션 설정 해야 할 것

    // SoldOutException 섫정 할 것 // 상품 주문 완료의 시점에 발생 하여야 함 (결제 완료 시점)

    // 단위 테스트 작성 (멀티 스레드 엑션이 가능하여야 함)

    // https://books.spotvnow.co.kr/bookstack/books/spotv-service-next-generation/page/f770b 해당 상태 구현 주문 구매 라이프 사이클
    
    // -------------
    
    // PayService 멀티스레드 Test

    // Unit Test 작성

    // MVC 분리 View 영역은 모두 Controller로 분리 할것
    
    // Contreoll 영역에 분기 및 기능만을 탑재

    // test 가능한 단위로 기능 메소드 분리
}
