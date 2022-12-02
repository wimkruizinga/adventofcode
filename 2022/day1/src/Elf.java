import java.util.ArrayList;
import java.util.List;

public class Elf {
    private final List<Item> items;

    public Elf() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item i) {
        this.items.add(i);
    }

    public int getTotalCalories() {
        return items.stream().mapToInt(Item::getCalories).sum();
    }
}
