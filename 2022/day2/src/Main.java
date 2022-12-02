import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("data/input.txt"));

        List<Game> games = new ArrayList<>();
        String line;
        while((line = reader.readLine()) != null) {
            Game game = Game.play(line.charAt(2), line.charAt(0));
            System.out.println(game);
            games.add(game);
        }

        System.out.printf("%d total points", games.stream().mapToInt(Game::getPoints).sum());
    }
}