package kr.co._29cm;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationTest {

    @Test
    public void testGet() {
        assertEquals("Hello JUnit 5", "Hello JUnit 5");
    }

    @Test
    public void testCounter() {
        int count = 0;

        for (int i = 0; i < 500; i++) {
            count++;
        }
        assertEquals(500, count);
    }
}