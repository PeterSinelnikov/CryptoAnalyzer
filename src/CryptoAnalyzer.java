import java.io.IOException;
import java.nio.file.Path;

public class CryptoAnalyzer {
    public static void main(String[] args) throws IOException {
        Path input = Path.of(args[1]);
        if (args[0].equals("encode")) {
            int key = Integer.parseInt(args[2]);
            Path output = OutputPath.getPath(input,"encoded");
            Encoder.encode(input,output,key);
        } else if (args[0].equals("decode")) {
            int key = Integer.parseInt(args[2]);
            Path output = OutputPath.getPath(input,"decoded");
            Encoder.decode(input,output,key);
        } else if (args[0].equals("bruteForce")) {
            Path referenceFile = Path.of(args[2]);
            Encoder.bruteForce(input,referenceFile);
        } else {
            throw new IllegalArgumentException("Provide following arguments: operation filePath key/referencePath");
        }
    }
}
