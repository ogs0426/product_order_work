package kr.co._29cm;

import kr.co._29cm.enums.MainParamType;
import kr.co._29cm.model.Product;
import kr.co._29cm.repository.ProductRepsitory;
import kr.co._29cm.service.OrderService;
import kr.co._29cm.util.CSVReader;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Application {

    public static void readProduct(ProductRepsitory repo) {
        List<List<String>> readCSV = CSVReader.readCSV("src/main/resources/product.csv");

        for (List<String> proCsv : readCSV) {
            repo.insertItem(new Product(proCsv));
        }
    }

    public static void main(String[] args) {

        ProductRepsitory repo = new ProductRepsitory();
        Scanner sc = new Scanner(System.in);

        readProduct(repo);

        while (true) {
            MainParamType optParam = null;

            while (optParam == null) {
                System.out.print("입력(o[order]: 주문, q[quit]: 종료) : ");
                optParam = MainParamType.from(sc.nextLine());
            }

            switch (optParam) {
                case ORDER -> {
                    OrderService ors = new OrderService(sc, repo);

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

    // H2 데이터 베이스 설정 이후 트랜젹션 설정 해야 할 것

    // SoldOutException 섫정 할 것 // 상품 주문 완료의 시점에 발생 하여야 함 (결제 완료 시점)

    // 단위 테스트 작성 (멀티 스레드 엑션이 가능하여야 함)

    // https://books.spotvnow.co.kr/bookstack/books/spotv-service-next-generation/page/f770b 해당 상태 구현 주문 구매 라이프 사이클

}
