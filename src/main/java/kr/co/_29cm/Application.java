package kr.co._29cm;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력 설명 문구
        System.out.print("입력(o[order]: 주문, q[quit]: 종료) : ");
        String optParam = sc.next();

        switch (optParam) {
            case "o":
                break;
            case "q":
                break;
            default:
                break;
        }
        // Step 0. 상품 정보 출력



        // String input = sc.nextLine();
        System.out.println(optParam);
    }

    // Step 1

    // 터미널로 명령 받아야 함

    // 인메모리 db 받아야 함

    // 멀티 스레드 엑션이 가능하여야 함

    // https://books.spotvnow.co.kr/bookstack/books/spotv-service-next-generation/page/f770b 해당 상태 구현 주문 구매 라이프 사이클

    // 유닛 테스트 작성
}
