package kr.co._29cm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationTest {

    @Test
    public void testGet() {
        System.out.println("test : 1");

        assertEquals("Hello JUnit 5", "Hello JUnit 5");
    }

    @Test
    public void testCounter() {
        System.out.println("test : 2");

        int count = 0;

        for (int i = 0; i < 500; i++) {
            count++;
        }

        assertEquals(500, count);
    }

    @Test
    public void testUnit() {
        System.out.println("test : 3");

        int count = 0;

        assertEquals(0, count);
    }

    // H2 트랜젹션 설정
    // SoldOutException 섫정 할 것
    // 상품 주문 완료 확인
    // 단위 테스트 작성
}