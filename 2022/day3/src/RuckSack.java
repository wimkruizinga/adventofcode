import java.util.ArrayList;
import java.util.stream.Stream;

public class RuckSack {
    private final ArrayList<Character> pocketA;
    private final ArrayList<Character> pocketB;

    public RuckSack() {
        this.pocketA = new ArrayList<>();
        this.pocketB = new ArrayList<>();
    }

    public RuckSack fill(String line) {
        var sep = line.length() / 2;
        fillPocket(this.pocketA, line.substring(0, sep));
        fillPocket(this.pocketB, line.substring(sep));
        return this;
    }

    private void fillPocket(ArrayList<Character> pocket, String contents) {
        for (int i = 0; i < contents.length(); i++) {
            pocket.add(contents.charAt(i));
        }
    }

    public int getScore() {
        Character duplicate = getDuplicate();
        return getPriority(duplicate);
    }

    private Character getDuplicate() {
        for (Character character : pocketA) {
            if (pocketB.contains(character)) {
                return character;
            }
        }
        return null;
    }

    private int getPriority(Character duplicate) {
        if (Character.isLowerCase(duplicate)) {
            return (int) duplicate - 96;
        } else {
            return (int) duplicate - (65 - 27);
        }
    }

    @Override
    public String toString() {
        return String.format("Pocket A: %s%nPocket B: %s%n", pocketA, pocketB);
    }
}
