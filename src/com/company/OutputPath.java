package com.company;

import java.nio.file.Path;

public class OutputPath {
    public static final String ENCODED = "encoded";
    public static final String DECODED = "decoded";


    public static Path getPath(Path inputPath, String operation) {
        String inputStr = String.valueOf(inputPath);
        String inputFileName = String.valueOf(inputPath.getFileName());
        if (inputFileName.contains(ENCODED)) {
            String replaced = inputStr.replace(ENCODED, DECODED);
            return Path.of(replaced);
        }
        if (inputFileName.contains(DECODED)) {
            String replaced = inputStr.replace(DECODED, ENCODED);
            return Path.of(replaced);
        }
        String[] splitInputStr = inputStr.split("\\.");
        return Path.of(splitInputStr[0] + "(" + operation + ")." + splitInputStr[1]);
    }

    public static Path getPath(Path inputPath, int key) {
        String inputStr = String.valueOf(inputPath);
        String inputFileName = String.valueOf(inputPath.getFileName());
        if (inputFileName.contains(ENCODED)) {
            String newName = "decoded_key-" + key;
            String replaced = inputStr.replace(ENCODED, newName);
            return Path.of(replaced);
        }
        String[] splitInputStr = inputStr.split("\\.");
        return Path.of(splitInputStr[0] + "(decoded_key-" + key + ")." + splitInputStr[1]);

    }

}
