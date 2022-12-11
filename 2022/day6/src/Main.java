import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Main {
    static final int MARKER_LENGTH = 4;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("data/input.txt", StandardCharsets.UTF_8));

        int intChar;
        StringBuilder signal = new StringBuilder();
        String markerCandidate;
        int currentPos = 0;
        while ((intChar = reader.read()) != -1) {
            currentPos++;
            signal.append((char) intChar);
            if (signal.length() < MARKER_LENGTH) {
                System.out.println(signal.toString() + " is too short");
                continue;
            }

            markerCandidate = signal.substring(currentPos - MARKER_LENGTH, currentPos);
            if (isMarker(markerCandidate)) {
                System.out.printf("%s is a marker. Pos = %d%n", markerCandidate, currentPos);
                break;
            } else {
                System.out.printf("%s is not a marker%n", markerCandidate);
            }
        }
    }

    private static boolean isMarker(String frame) {
        return frame.chars().distinct().count() == 4;
    }
}