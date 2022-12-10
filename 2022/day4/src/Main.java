import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("data/input.txt", StandardCharsets.UTF_8));
        ArrayList<ElfPair> elfPairs = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            elfPairs.add(ElfPair.fromLine(line));
        }

        List<ElfPair> pairsWithOverlappingSecitons = elfPairs.stream()
                .filter(ElfPair::assignmentsOverlap)
                .collect(Collectors.toList());

        System.out.printf("Pairs with overlapping assignments: %d%n", pairsWithOverlappingSecitons.size());
    }
}