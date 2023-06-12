package kr.co._29cm;

import kr.co._29cm.controller.OrderController;

public class Application {

    public static void main(String[] args) {
        OrderController.runOrder();
    }

    // -------------

    // sudo 코드 정리 (완)

    // 터미널로 명령 받아야 함 (완)

    // -------------

    // H2 데이터 베이스 설정 이후 트랜젹션 설정 해야 할 것

    // SoldOutException 섫정 할 것 // 상품 주문 완료의 시점에 발생 하여야 함 (결제 완료 시점)

    // 단위 테스트 작성 (멀티 스레드 엑션이 가능하여야 함)

    // https://books.spotvnow.co.kr/bookstack/books/spotv-service-next-generation/page/f770b 해당 상태 구현 주문 구매 라이프 사이클

}
