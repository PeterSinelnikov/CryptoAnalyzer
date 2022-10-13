package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Language {

    static final List<Character> ALPHABET_EN_BIG = new ArrayList<>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'));
    static final List<Character> ALPHABET_EN_SMALL = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));
    static final List<Character> ALPHABET_RU_BIG = new ArrayList<>(Arrays.asList('А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я'));
    static final List<Character> ALPHABET_RU_SMALL = new ArrayList<>(Arrays.asList('а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я'));


    public static boolean isEnglishLetter(char c) {
        return ALPHABET_EN_SMALL.contains(c) || ALPHABET_EN_BIG.contains(c);
    }

    public static boolean isRussianLetter(char c) {
        return ALPHABET_RU_SMALL.contains(c) || ALPHABET_RU_BIG.contains(c);
    }

    static List<Character> getLanguageAlphabet(String referenceString) {
        List<Character> currentLanguage = ALPHABET_EN_BIG;
        String substring = referenceString.substring(0, 16);
        for (char c : substring.toCharArray()) {
            if (isRussianLetter(c)){
                currentLanguage = ALPHABET_RU_BIG;
            }
        }
        return currentLanguage;
    }
}
