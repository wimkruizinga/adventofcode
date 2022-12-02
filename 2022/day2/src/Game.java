public class Game {
    public enum Outcome {
        WIN(6),
        DRAW(3),
        LOSE(0);

        public final int score;

        Outcome(int score) {
            this.score = score;
        }
    }

    public enum Shape {
        ROCK(1, "Rock"),
        PAPER(2, "Paper"),
        SCISSORS(3, "Scissors");

        public final int score;
        public final String text;

        Shape(int score, String text) {
            this.score = score;
            this.text = text;
        }
    }

    private Shape playerShape;
    private Shape opponentShape;
    private Outcome outcome;

    private Game() {}

    public static Game play(char playerShape, char opponentShape) throws Exception {
        Game game = new Game()
                .withPlayerShape(playerShape)
                .withOpponentShape(opponentShape);
        game.outcome = game.play();
        return game;
    }

    private Game withPlayerShape(char playerShape) throws Exception {
        this.playerShape = getShapeEnum(playerShape);
        return this;
    }

    private Game withOpponentShape(char opponentShape) throws Exception {
        this.opponentShape = getShapeEnum(opponentShape);
        return this;
    }

    private Shape getShapeEnum(char shape) throws Exception {
        switch (shape) {
            case 'A':
            case 'X':
                return Shape.ROCK;
            case 'B':
            case 'Y':
                return Shape.PAPER;
            case 'C':
            case 'Z':
                return Shape.SCISSORS;
            default:
                throw new Exception("Invalid shape");
        }
    }

    private Outcome play() {
        switch (playerShape) {
            case ROCK:
                switch (opponentShape) {
                    case ROCK:
                        return Outcome.DRAW;
                    case PAPER:
                        return Outcome.LOSE;
                    case SCISSORS:
                        return Outcome.WIN;
                }
            case PAPER:
                switch (opponentShape) {
                    case ROCK:
                        return Outcome.WIN;
                    case PAPER:
                        return Outcome.DRAW;
                    case SCISSORS:
                        return Outcome.LOSE;
                }
            case SCISSORS:
                switch (opponentShape) {
                    case ROCK:
                        return Outcome.LOSE;
                    case PAPER:
                        return Outcome.WIN;
                    case SCISSORS:
                        return Outcome.DRAW;
                }
        }
        return null;
    }

    @Override
    public String toString() {
        int points = getPoints();
        switch (this.outcome) {
            case DRAW:
                return String.format("It's a draw! %d points.", points);
            case WIN:
                return String.format("%s beats %s. You win! %d points.", playerShape.text, opponentShape.text, points);
            case LOSE:
                return String.format("%s beats %s. You lose! %d points.", opponentShape.text, playerShape.text, points);
            default:
                return super.toString();
        }
    }

    public int getPoints() {
        return playerShape.score + outcome.score;
    }
}
