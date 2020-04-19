package de.studiocode.antibackdoor.utils;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class StringUtils {

    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String ALPHABET_AND_NUMBERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    
    public static String generateRandomString(int length, String characters) {
        StringBuilder sb = new StringBuilder();
        final int clength = characters.length();
        IntStream.range(0, length).forEach(i -> sb.append(characters.charAt(MathUtils.randomInteger(0, clength))));
        return sb.toString();
    }
    
    public static String generateRandomString(int length) {
        return generateRandomString(length, ALPHABET);
    }
    
    public static Predicate<String> createStringFilterPredicate(boolean ignoreCase, String... strings) {
        return s -> Arrays.stream(strings).anyMatch(s1 -> ignoreCase ? s1.equalsIgnoreCase(s) : s1.equals(s));
    }

    public static boolean containsOnly(char[] allowedCharacters, String test) {
        for (int index = 0; index != test.length(); index++) {
            char c = test.charAt(index);
            if (!containsChar(allowedCharacters, c)) return false;
        }
        return true;
    }
    
    public static boolean containsOnly(String allowedCharacters, String test) {
        return containsOnly(allowedCharacters.toCharArray(), test);
    }
    
    private static boolean containsChar(char[] check, char search) {
        for (char c : check) {
            if (c == search) return true;
        }
        return false;
    }
    
}
