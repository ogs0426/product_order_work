package kr.co._29cm;

import kr.co._29cm.exception.SoldOutException;
import kr.co._29cm.model.Product;
import kr.co._29cm.model.ProductInfo;
import kr.co._29cm.model.Receipt;
import kr.co._29cm.service.PayService;
import kr.co._29cm.service.ProductService;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.List;
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
    public void updateStock() {
        System.out.println("update Stock Count 30");
        int count = 30;
        ProductService productService = new ProductService();

        Product bItem = productService.getProduct(648418);
        bItem.setStock(count);

        productService.saveProduct(bItem);

        Product aItem = productService.getProduct(648418);

        assertEquals(count, aItem.getStock());
    }

    @Test
    public void newProduct() {
        System.out.println("new Product");
        int count = 30;
        ProductService productService = new ProductService();
        Product bitem = new Product(ProductInfo.builder().id(999999).name("test").price(100).build(), 0);
        productService.saveProduct(bitem);

        Product aItem = productService.getProduct(999999);

        assertEquals(bitem, aItem);
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

        ProductService productService = new ProductService();
        PayService payService = new PayService();

        ProductInfo item = productService.getProductInfo(782858);
        cart.put(item, 10);

        for (int i = 0; i < numberOfThreads; i++) {
            new Thread(() -> {

                try {
                    if(productService.takeOutCart(cart)) {
                        System.out.println("Thread " + Thread.currentThread().getId() + " is Order");
                        Receipt receipt = payService.generateReceipt(cart, Thread.currentThread().getId());

                        sCount.addAndGet(1);
                        payService.payCart(receipt);
                    }

                } catch (SoldOutException e) {
                    System.out.println("Thread " + Thread.currentThread().getId() + " is SoldOut");
                    fCount.getAndIncrement();
                }

                latch.countDown();
            }).start();

        }

        latch.await();

        for (Receipt receipt : payService.getAllList()) {
            System.out.printf("User : %d, Time : %d, Cart : %s, Amount : %d, Delivery : %d \n", receipt.getUserid(), receipt.getTime(), receipt.getCart().toString(), receipt.getAmount(), receipt.getDelivery());
        }

        assertEquals(5, sCount.get());
        assertEquals(5, fCount.get());
    }
}