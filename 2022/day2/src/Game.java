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
    private Outcome desiredOutcome;

    private Game() {}

    public static Game getPlayerShape(char opponentShape, char desiredOutcome) throws Exception {
        Game game = new Game()
                .withOpponentShape(opponentShape)
                .withDesiredOutcome(desiredOutcome);
        game.playerShape = game.getPlayerShape();
        return game;
    }

    private Game withOpponentShape(char opponentShape) throws Exception {
        this.opponentShape = getShapeEnum(opponentShape);
        return this;
    }

    private Game withDesiredOutcome(char gameOutcome) throws Exception {
        this.desiredOutcome = getOutcomeEnum(gameOutcome);
        return this;
    }

    private Shape getShapeEnum(char shape) throws Exception {
        switch (shape) {
            case 'A':
                return Shape.ROCK;
            case 'B':
                return Shape.PAPER;
            case 'C':
                return Shape.SCISSORS;
            default:
                throw new Exception("Invalid shape");
        }
    }

    private Outcome getOutcomeEnum(char outcome) throws Exception {
        switch (outcome) {
            case 'X':
                return Outcome.LOSE;
            case 'Y':
                return Outcome.DRAW;
            case 'Z':
                return Outcome.WIN;
            default:
                throw new Exception("Invalid outcome");
        }
    }

    private Shape getPlayerShape() {
        switch (opponentShape) {
            case ROCK:
                switch (desiredOutcome) {
                    case WIN:
                        return Shape.PAPER;
                    case DRAW:
                        return Shape.ROCK;
                    case LOSE:
                        return Shape.SCISSORS;
                }
            case PAPER:
                switch (desiredOutcome) {
                    case WIN:
                        return Shape.SCISSORS;
                    case DRAW:
                        return Shape.PAPER;
                    case LOSE:
                        return Shape.ROCK;
                }
            case SCISSORS:
                switch (desiredOutcome) {
                    case WIN:
                        return Shape.ROCK;
                    case DRAW:
                        return Shape.SCISSORS;
                    case LOSE:
                        return Shape.PAPER;
                }
        }
        return null;
    }

    @Override
    public String toString() {
        int points = getPoints();
        switch (this.desiredOutcome) {
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
        return playerShape.score + desiredOutcome.score;
    }
}
