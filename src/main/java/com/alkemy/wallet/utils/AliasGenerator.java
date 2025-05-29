package com.alkemy.wallet.utils;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AliasGenerator {

    private static final List<String> WORDS = new ArrayList<>(Arrays.asList(
            "cuenta", "alky", "wallet", "banco", "dinero", "ahorro", "futuro", "meta",
            "digital", "segura", "clave", "virtual", "alk", "alkywallet", "movil",
            "moneda", "net", "fast", "nube", "cash", "click", "simple", "pro", "global",
            "argent", "solidez", "flow", "pago", "transfer", "online"));

    private static final Random RANDOM = new Random();

    public static String generateRandomAlias() {
        StringBuilder alias = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            int index = RANDOM.nextInt(WORDS.size());
            alias.append(WORDS.get(index));
            if (i < 2) {
                alias.append(".");
            }
        }
        return alias.toString();
    }
}
