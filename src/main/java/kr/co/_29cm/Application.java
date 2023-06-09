package kr.co._29cm;

import kr.co._29cm.enums.MainParamType;
import kr.co._29cm.service.OrderService;

import java.util.Map;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        while (true) {
            Scanner sc = new Scanner(System.in);
            MainParamType optParam = null;

            while (optParam == null) {
                System.out.print("입력(o[order]: 주문, q[quit]: 종료) : ");
                optParam = MainParamType.from(sc.nextLine());
            }

            switch (optParam) {
                case ORDER -> {
                    OrderService ors = new OrderService(sc);

                    Map<Integer, Integer> cart = ors.runOrder();
                }
                case QUIT -> {
                    // 종료 시

                    System.out.println("고객님의 주문 감사합니다.");

                    sc.close();
                    System.exit(0);
                }

                default -> {
                }
            }

            System.out.println("");
        }

    }

    // -------------

    // sudo 코드 정리 (완)

    // 터미널로 명령 받아야 함 (완)

    // -------------

    // 인메모리 db 받아야 함 (뭐 써야 하냐?)

    // 유닛 테스트 작성 (멀티 스레드 엑션이 가능하여야 함)

    // https://books.spotvnow.co.kr/bookstack/books/spotv-service-next-generation/page/f770b 해당 상태 구현 주문 구매 라이프 사이클

}
