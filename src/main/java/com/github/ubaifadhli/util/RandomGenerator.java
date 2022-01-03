package com.github.ubaifadhli.util;

import java.util.Random;

public class RandomGenerator {
    public static String generateString() {
        int lowerBound = 97; // letter 'a'
        int upperBound = 122; // letter 'z'
        int stringLength = 10;

        Random random = new Random();

        return random.ints(lowerBound, upperBound + 1)
                .limit(stringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}