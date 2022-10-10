import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CryptoAnalyzer {
    private static final String ENCODE = "encode";
    private static final String DECODE = "decode";
    private static final String BRUTE_FORCE = "bruteForce";

    public static void main(String[] args) throws IOException {
        String operation = args[0];
        Path input = Path.of(args[1]);
        String key = args[2];
        if (!Files.isRegularFile(input)) {
            throw new IllegalArgumentException("provide valid input file path");
        }

        if (ENCODE.equalsIgnoreCase(operation)) {
            int encodingKey = Integer.parseInt(key);
            Path output = OutputPath.getPath(input,"encoded");
            Encoder.encode(input,output,encodingKey);
        } else if (DECODE.equalsIgnoreCase(operation)) {
            int decodingKey = Integer.parseInt(key);
            Path output = OutputPath.getPath(input,"decoded");
            Encoder.decode(input,output,decodingKey);
        } else if (BRUTE_FORCE.equalsIgnoreCase(operation)) {
            Path referenceFile = Path.of(key);
            BruteForce.bruteForce(input,referenceFile);
        } else {
            throw new IllegalArgumentException("Provide following arguments: operation filePath key/referencePath");
        }
    }
}
