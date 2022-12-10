import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.regex.Pattern;

public class ElvishCraneDriver {
    private BufferedReader reader;
    public ArrayList<Move> moves;
    private HashMap<Integer, Stack<Character>> stacks;
    private int colCount;


    public ElvishCraneDriver(BufferedReader reader) {
        this.reader = reader;
        moves = new ArrayList<>();
        stacks = new HashMap<>();
    }

    public void readFile() throws IOException {
        Stack<String> cargoLines = new Stack<>();
        String line;
        while ((line = reader.readLine()) != null) {
            if (isRow(line)) {
                cargoLines.push(line);
            } else if (isMove(line)) {
                addMove(line);
            } else if (isAxis(line)) {
                setColCount(line);
            }
        }
        processCargoLines(cargoLines);
    }

    public void addMove(String line) {
        String[] parts = line.trim().split("\\s+");
        int amount = Integer.parseInt(parts[1]);
        int from = Integer.parseInt(parts[3]);
        int to = Integer.parseInt(parts[5]);
        moves.add(new Move(from, to, amount));
    }

    public boolean isRow(String line) {
        return line.trim().startsWith("[");
    }

    public boolean isMove(String line) {
        return line.startsWith("move");
    }

    public boolean isAxis(String line) {
        return Pattern.matches("[0-9 ]+", line);
    }

    public void setColCount(String line) {
        String[] cols = line.trim().split("\\s+");
        colCount = cols.length;
    }

    public void processCargoLines(Stack<String> cargoLines) {
        while (!cargoLines.isEmpty()) {
            processCargoLine(cargoLines.pop());
        }
    }

    public void processCargoLine(String cargoLine) {
        for (int i = 0; i < colCount; i++) {
            int colNo = i + 1;
            int charPos = i * 4 + 1;
            if (charPos >= cargoLine.length()) {
                continue;
            }
            Character ch = cargoLine.charAt(charPos);
            if (ch.charValue() != ' ') {
                if (!stacks.containsKey(colNo)) {
                    stacks.put(colNo, new Stack<>());
                }
                stacks.get(colNo).push(ch);
            }
        }
    }

    public void applyMoves() {
        System.out.printf("Top items: [%s]%n", getTopItems());
        for (Move move : moves) {
            applyMove(move);
            System.out.printf("Top items: [%s]%n", getTopItems());
        }
    }

    private String getTopItems() {
        String items = "";
        for (int i = 0; i < colCount; i++) {
            if (stacks.get(i + 1).isEmpty()) {
                items = items + " ";
            } else {
                items = items + stacks.get(i + 1).peek();
            }
        }

        return items;
    }

    public void applyMove(Move move) {
        Stack<Character> chars = new Stack<>();
        for (int i = 0; i < move.getAmount(); i++) {
            chars.push(stacks.get(move.getFrom()).pop());
        }

        while (!chars.isEmpty()) {
            stacks.get(move.getTo()).push(chars.pop());
        }
    }

    public int getColCount() {
        return colCount;
    }
}
