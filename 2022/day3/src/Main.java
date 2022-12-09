import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<RuckSack> rs = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("data/input.txt", StandardCharsets.UTF_8));

        String line;
        while ((line = reader.readLine()) != null) {
            rs.add(new RuckSack().fill(line));
        }

        var score = rs.stream().mapToInt(RuckSack::getScore).sum();
        System.out.printf("Score: %d%n", score);
    }
}
