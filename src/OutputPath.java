import java.nio.file.Path;

public class OutputPath {

    public static Path getPath(Path inputPath, String operation) {
        String inputStr = String.valueOf(inputPath);
        String inputFileName = String.valueOf(inputPath.getFileName());
        if (inputFileName.contains("(encoded)")) {
            String replaced = inputStr.replace("encoded", "decoded");
            return Path.of(replaced);
        } else if (inputFileName.contains("(decoded)")) {
            String replaced = inputStr.replace("decoded","encoded");
            return Path.of(replaced);
        } else {
            String[] splitInputStr = inputStr.split("\\.");
            return Path.of(splitInputStr[0] + "(" + operation + ")." + splitInputStr[1]);
        }
    }

    public static Path getPath(Path inputPath, int key) {
        String inputStr = String.valueOf(inputPath);
        String inputFileName = String.valueOf(inputPath.getFileName());
        if (inputFileName.contains("(encoded)")) {
            String newName = "decoded key-" + key;
            String replaced = inputStr.replace("encoded", newName);
            return Path.of(replaced);
        } else {
            String[] splitInputStr = inputStr.split("\\.");
            return Path.of(splitInputStr[0] + "(decoded key-" + key + ")." + splitInputStr[1]);
        }
    }

}
