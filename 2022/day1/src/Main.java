import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("data/input.txt"));
        List<Elf> elves = process(reader);

        for (Elf elf : elves) {
            System.out.println(elf.getTotalCalories());
        }

        Elf theBest = Collections.max(elves, Comparator.comparingInt(Elf::getTotalCalories));
        System.out.printf("Elf number %d carries the most calories: %d\n", elves.indexOf(theBest) + 1, theBest.getTotalCalories());

        elves.sort(Comparator.comparingInt(Elf::getTotalCalories).reversed());
        int total = 0;
        for (int i = 0; i < 3; i++) {
            total += elves.get(i).getTotalCalories();
        }
        System.out.printf("The top 3 elves carry a total of %d calories.\n", total);
    }

    public static List<Elf> process(BufferedReader reader) throws IOException {
        List<Elf> elves = new ArrayList<>();
        Elf elf = new Elf();
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.isBlank()) {
                elves.add(elf);
                elf = new Elf();
                continue;
            }
            elf.addItem(new Item(Integer.parseInt(line)));
        }
        return elves;
    }
}