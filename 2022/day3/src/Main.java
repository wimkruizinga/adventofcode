import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<RuckSack> rs = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("data/input.txt", StandardCharsets.UTF_8));

        ArrayList<String> lines = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        var score = 0;
        for (int i = 0; i < lines.size(); i++) {
            if (i % 3 == 0) {
                for (Character ch: lines.get(i).toCharArray()) {
                    if (lines.get(i + 1).contains(String.valueOf(ch)) && (lines.get(i + 2).contains(String.valueOf(ch)))) {
                        var prio = getPriority(ch);
                        System.out.printf("%c is een badge with priority %d%n", ch, prio);
                        score += prio;
                        break;
                    }
                }
            }
        }
        System.out.printf("Total score: %d%n", score);
    }

    private static int getPriority(Character ch) {
        if (Character.isLowerCase(ch)) {
            return (int) ch - 96;
        } else {
            return (int) ch - (65 - 27);
        }
    }
}
