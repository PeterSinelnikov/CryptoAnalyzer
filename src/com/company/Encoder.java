package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Encoder {

    public static void encode(Path inputPath, Path outputPath, int key) {
        try {
            String string = Files.readString(inputPath);
            String encodedText = replaceLetters(string, key);
            Files.writeString(outputPath, encodedText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void decode(Path inputPath, Path outputPath, int key) {
        encode(inputPath, outputPath, -key);
    }

    static String replaceLetters(String text, int key) {
        char[] stringToChars = text.toCharArray();
        for (int i = 0; i < stringToChars.length; i++) {
            char letter = stringToChars[i];
            if (Character.isAlphabetic(letter)) {
                if (Language.isEnglishLetter(letter)) {
                    stringToChars[i] = replaceCurrentLetter(Language.ALPHABET_EN_BIG, Language.ALPHABET_EN_SMALL, letter, key);
                } else if (Language.isRussianLetter(letter)) {
                    stringToChars[i] = replaceCurrentLetter(Language.ALPHABET_RU_BIG, Language.ALPHABET_RU_SMALL, letter, key);
                }
            }
        }
        return new String(stringToChars);
    }

    private static char replaceCurrentLetter(List<Character> alphabetBig, List<Character> alphabetSmall, char letter, int key) {
        if (Character.isUpperCase(letter)) {
            int index = alphabetBig.indexOf(letter);
            letter = getEncryptedAlphabet(alphabetBig, key).get(index);
        } else {
            int index = alphabetSmall.indexOf(letter);
            letter = getEncryptedAlphabet(alphabetSmall, key).get(index);
        }
        return letter;
    }

    private static List<Character> getEncryptedAlphabet(List<Character> alphabet, int key) {
        List<Character> copy = new ArrayList<>(alphabet);
        Collections.rotate(copy, -key);
        return copy;
    }

}
