package com.github.ubaifadhli.util;

import lombok.SneakyThrows;

import java.security.SecureRandom;
import java.util.Random;

public class RandomGenerator {
    public static Random random = new SecureRandom();

    public static String generateNumberString() {
        int randomNumber = random.nextInt((int) (Math.pow(10, 4)));
        return String.valueOf(randomNumber);
    }

    @SneakyThrows
    public static String generateStringByLength(int stringLength) {
        StringBuilder generatedString = new StringBuilder();

        for (int i = 0; i < stringLength; i++) {
            int selectedCharacter = 97 + random.nextInt(26);
            generatedString.append((char) selectedCharacter);
        }

        return generatedString.toString();
    }
}
