package com.alkemy.wallet.utils;

import java.util.Random;

public class CbuGenerator {

    private static final Random RANDOM = new Random();

    public static String generateRandomCBU() {
        StringBuilder cbu = new StringBuilder();
        for (int i = 0; i < 22; i++) {
            int digit = RANDOM.nextInt(10); // Genera un número entre 0 y 9
            cbu.append(digit);
        }
        return cbu.toString();
    }
}
