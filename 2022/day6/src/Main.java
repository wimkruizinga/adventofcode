import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Main {
    static final int START_OF_PACKET_MARKER_LENGTH = 4;
    static final int START_OF_MESSAGE_MARKER_LENGTH = 14;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("data/input.txt", StandardCharsets.UTF_8));

        int intChar;
        StringBuilder signal = new StringBuilder();
        String markerCandidate;
        int currentPos = 0;
        while ((intChar = reader.read()) != -1) {
            currentPos++;
            signal.append((char) intChar);
            if (signal.length() < START_OF_PACKET_MARKER_LENGTH) {
                System.out.println(signal + " is too short");
                continue;
            }

            markerCandidate = signal.substring(currentPos - START_OF_PACKET_MARKER_LENGTH, currentPos);
            if (isMarker(markerCandidate)) {
                System.out.printf("%s is a start of packet marker. Pos = %d%n", markerCandidate, currentPos);
            }

            if (signal.length() >= START_OF_MESSAGE_MARKER_LENGTH) {
                String messageMarkerCandidate = signal.substring(currentPos - START_OF_MESSAGE_MARKER_LENGTH, currentPos);
                if (isStartOfMessage(messageMarkerCandidate)) {
                    System.out.printf("%s is a start of message marker. Pos = %d%n", messageMarkerCandidate, currentPos);
                    break;
                }
            }
        }
    }

    private static boolean isMarker(String frame) {
        return frame.chars().distinct().count() == 4;
    }

    private static boolean isStartOfMessage(String frame) {
        return frame.chars().distinct().count() == 14;
    }
}