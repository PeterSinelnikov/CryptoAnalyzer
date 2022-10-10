
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
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

    public static void bruteForce(Path inputPath, Path referenceFile) throws IOException {
        String referenceString = Files.readString(referenceFile);
        List<Character> alphabet = Language.getLanguageAlphabet(referenceString);
        Integer[] referenceHistogram = getHistogram(referenceString,alphabet);
        normalizeHistogram(referenceHistogram);

        String textToDecode = Files.readString(inputPath);
        Integer[] encodedTextHistogram = getHistogram(textToDecode, alphabet);
        normalizeHistogram(encodedTextHistogram);

        int minDelta = Integer.MAX_VALUE;
        int key = 0;
        String result = "";
        for (int i = 1; i < Language.ALPHABET_EN_BIG.size(); i++) {
            String bruteForcedText = replaceLetters(textToDecode, -i);
            encodedTextHistogram = getHistogram(bruteForcedText,alphabet);
            normalizeHistogram(encodedTextHistogram);
            int delta = calculateDelta(referenceHistogram,encodedTextHistogram);
            if (delta < minDelta) {
                minDelta = delta;
                key = i;
                result = bruteForcedText;
            }
        }
        Path outputPath = OutputPath.getPath(inputPath, key);
        Files.writeString(outputPath, result);
    }

    private static String replaceLetters(String text, int key) {
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

    private static Integer[] getHistogram(String text, List<Character> alphabet) {
        char[] chars = text.toCharArray();
        int alphabetLength = alphabet.size();
        Integer[] histogram = new Integer[alphabetLength];
        Arrays.fill(histogram,0);
        for (char letter : chars) {
            if (alphabet.contains(letter)) {
                char character = Character.toUpperCase(letter);
                int letterIndex = alphabet.indexOf(character);
                histogram[letterIndex]++;
            }
        }
        return histogram;
    }

    private static void normalizeHistogram(Integer[] histogram) {
        Integer max = 0;
        for (Integer integer : histogram) {
            if (integer > max) {
                max = integer;
            }
        }
        for (int i = 0; i < histogram.length; i++) {
            histogram[i] = histogram[i] * 100 / max;
        }
    }

    private static int calculateDelta(Integer[] referenceHistogram, Integer[] encodedTextHistogram) {
        int delta = 0;
        for (int i = 0; i < referenceHistogram.length; i++) {
            delta = delta + Math.abs(referenceHistogram[i] - encodedTextHistogram[i]);
        }
        return delta;
    }
}
