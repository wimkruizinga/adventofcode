import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Main  {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("data/input.txt", StandardCharsets.UTF_8));
        ElvishCraneDriver inputReader = new ElvishCraneDriver(reader);
        inputReader.readFile();
        inputReader.applyMoves();
    }
}