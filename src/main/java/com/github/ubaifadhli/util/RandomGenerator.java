package com.github.ubaifadhli.util;

import java.security.SecureRandom;
import java.util.Random;

public class RandomGenerator {
    public static Random random = new SecureRandom();

    public static String generateNumberString() {
        return "#" + random.nextInt((int) (Math.pow(10, 6)));
    }
}