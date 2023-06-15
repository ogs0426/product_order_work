package kr.co._29cm.util;

import java.util.Random;

public class Common {
    public static Long genderRandom(long max) {
        Random rand = new Random();

        return rand.nextLong(max);
    }
}
