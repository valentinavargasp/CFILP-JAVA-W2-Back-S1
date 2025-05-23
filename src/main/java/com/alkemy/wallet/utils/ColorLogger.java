package com.alkemy.wallet.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ColorLogger {

    private static final Logger logger = LoggerFactory.getLogger(ColorLogger.class);

    // Colores ANSI
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_MAGENTA = "\u001B[35m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_CYAN = "\u001B[36m";

    // Métodos estáticos para logs personalizados
    public static void magenta(String message) {
        logger.info(ANSI_MAGENTA + message + ANSI_RESET);
    }

    public static void blue(String message) {
        logger.info(ANSI_BLUE + message + ANSI_RESET);
    }

    public static void green(String message) {
        logger.info(ANSI_GREEN + message + ANSI_RESET);
    }

    public static void cyan(String message) {
        logger.info(ANSI_CYAN + message + ANSI_RESET);
    }

}
