package poole.Mike.MyMastermind.gui;
import javafx.scene.paint.Color;
import poole.Mike.MyMastermind.gamePlay.Game;

public class Model {
    private Game game;
    protected Controller controller;

    private final String HUMAN_PLAYER = "HUMAN_PLAYER";
    private final String COMPUTER_PLAYER = "COMPUTER_PLAYER";
    private String activePlayer = HUMAN_PLAYER;

    public Model(Controller cntrl) {
        controller = cntrl;
    }

    public void startGame() {
        game = new Game();
        game.startGame();
    }



    public void makeGuess(Color[] guess) {
        if (validGuess(guess)) {
            boolean gameOver = game.makeGuess(guess);
            sendFeedback();
            if (gameOver) {
                sendFinalScore();
                swapActivePlayer();
            }
        }
    }

    private void swapActivePlayer() {
        if (activePlayer.equals(HUMAN_PLAYER)) {
            activePlayer = COMPUTER_PLAYER;
            game.numberOfGuesses = 0;
        } else {
            activePlayer = HUMAN_PLAYER;
        }
    }

    private void sendFeedback() {
        int[] feedback = game.getFeedbackScore();
        controller.setFeedbackResults(feedback[0], feedback[1]);
    }

    private void sendFinalScore() {
        if (activePlayer.equals(HUMAN_PLAYER)) {
            if (game.numberOfGuesses == 9) {
                controller.printHumanFeedback("You failed to find the code");
            } else {
                controller.printHumanFeedback("Your score: " + game.numberOfGuesses);
            }
        } else {
            if (game.numberOfGuesses == 9) {
                controller.printComputerFeedback("The computer couldn't solve the code");
            } else {
                controller.printComputerFeedback("Computer score: " + game.numberOfGuesses);
            }
        }
    }

    private boolean validGuess(Color[] guess) {
        boolean noBlackPins = true;
        for (Color pin : guess) {
            if (pin.equals(Color.BLACK)) {
                noBlackPins = false;
                break;
            }
        }
        return noBlackPins;
    }
}
