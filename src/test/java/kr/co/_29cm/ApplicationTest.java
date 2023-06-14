package kr.co._29cm;

import kr.co._29cm.exception.SoldOutException;
import kr.co._29cm.model.Product;
import kr.co._29cm.model.ProductInfo;
import kr.co._29cm.service.PayService;
import kr.co._29cm.service.ProductService;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationTest {

    @AfterEach
    public void afterTreatment() {
        System.out.println("-----");
    }

    @BeforeEach
    public void initialize() {
        ProductService productService = new ProductService();
        productService.initReadProductInfo();
    }

    /*
    @BeforeAll
    public static void initializeOnce() {
        ProductService productService = new ProductService();
        productService.initReadProductInfo();
    }
     */

    @Test
    public void initProductCheck() {
        System.out.println("initReadProductInfo Check 768848, 744775, 648418, 782858");
        ProductService productService = new ProductService();

        Product item = null;

        System.out.println("Check 768848");
        item = productService.getProduct(768848);
        assertEquals(45, item.getStock());

        System.out.println("Check 744775");
        item = productService.getProduct(744775);
        assertEquals(35, item.getStock());

        System.out.println("Check 648418");
        item = productService.getProduct(648418);
        assertEquals(5, item.getStock());

        System.out.println("Check 782858");
        item = productService.getProduct(782858);
        assertEquals(50, item.getStock());
    }

    @Test
    public void initProductInfoCheck() {
        System.out.println("initReadProductInfo Check 768848, 744775, 648418, 782858");
        ProductService productService = new ProductService();

        ProductInfo item = null;

        System.out.println("Check 768848");
        item = productService.getProductInfo(768848);
        assertEquals("[STANLEY] GO CERAMIVAC 진공 텀블러/보틀 3종", item.getName());
        assertEquals(21000, item.getPrice());

        System.out.println("Check 744775");
        item = productService.getProductInfo(744775);
        assertEquals("SHUT UP [TK00112]", item.getName());
        assertEquals(28000, item.getPrice());

        System.out.println("Check 648418");
        item = productService.getProductInfo(648418);
        assertEquals("BS 02-2A DAYPACK 26 (BLACK)", item.getName());
        assertEquals(238000, item.getPrice());

        System.out.println("Check 782858");
        item = productService.getProductInfo(782858);
        assertEquals("폴로 랄프로렌 남성 수영복반바지 컬렉션 (51color)", item.getName());
        assertEquals(39500, item.getPrice());
    }

    @Test
    public void orderController() {
        Scanner sc = new Scanner(System.in);
        // OrderService orderService = new OrderService(sc);

        // Map<ProductInfo, Integer> cart = orderService.getOrderCartList();
    }

    @Test
    public void takeOutMultiThread() throws InterruptedException {
        System.out.println("payServiceTestMultiThread : 10");

        int numberOfThreads = 10;

        AtomicInteger sCount = new AtomicInteger();
        AtomicInteger fCount = new AtomicInteger();

        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        Map<ProductInfo, Integer> cart = new HashMap<>();

        ProductService productInfoService = new ProductService();
        PayService payService = new PayService();

        ProductInfo item = productInfoService.getProductInfo(782858);
        cart.put(item, 10);

        for (int i = 0; i < numberOfThreads; i++) {
            new Thread(() -> {

                try {
                    if(payService.takeOutCart(cart) != null) {
                        System.out.println("Thread " + Thread.currentThread().getId() + " is Order");
                        sCount.addAndGet(1);
                    }
                } catch (SoldOutException e) {
                    System.out.println("Thread " + Thread.currentThread().getId() + " is SoldOut");
                    fCount.getAndIncrement();
                }

                latch.countDown();
            }).start();

        }

        latch.await();

        assertEquals(5, sCount.get());
        assertEquals(5, fCount.get());
    }
}