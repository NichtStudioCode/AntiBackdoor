package de.studiocode.antibackdoor.utils;

import java.util.Random;

public class MathUtils {

    private static Random random = new Random();

    public static int randomInteger(int includeLow, int excludeHigh) {
        return random.nextInt(excludeHigh - includeLow) + includeLow;
    }

    public static double randomDouble(double includeLow, double excludeHigh) {
        return includeLow + (excludeHigh - includeLow) * random.nextDouble();
    }
    
}
