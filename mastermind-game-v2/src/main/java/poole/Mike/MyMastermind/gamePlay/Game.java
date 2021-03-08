
package poole.Mike.MyMastermind.gamePlay;

import javafx.scene.paint.Color;
import poole.Mike.MyMastermind.player.HumanPlayer;
//import poole.Mike.MyMastermind.computerPlayer.AllPossibleCode;
//import poole.Mike.MyMastermind.computerPlayer.MastermindLookupTable;


public class Game {

    private final String HUMAN_PLAYER = "HUMAN_PLAYER";
    private final String COMPUTER_PLAYER = "COMPUTER_PLAYER";
    private String activePlayer = HUMAN_PLAYER;
    public int numberOfGuesses = 0;
    private Feedback feedbackObj;


    public Game() {
        feedbackObj = new Feedback();
    }

    public void startGame(){
        GameSetUp.generateSecretCode();
        GameSetUp.printSecretCode();
    }

    public boolean makeGuess(Color[] theGuess) {
        numberOfGuesses++;
        feedbackObj.generateFeedback(GameSetUp.getSecretCode(), theGuess);
        return isGameOver(feedbackObj);
    }



        /*
         * Game is over once player has guessed the secretCode or has reached max number of guesses(rows)
         */
        private boolean isGameOver(Feedback feedback) {
            boolean isGameOver = false;
            if (isCodeGuessedCorrectly(feedback)) {
                isGameOver = true;
            }
            else if(maxGuessReached()){
                numberOfGuesses ++;
                isGameOver = true;
            }
            return isGameOver;
        }

        private boolean isCodeGuessedCorrectly(Feedback feedback) {
            return feedback.getTheFeedback()[0] == GameSetUp.numberOfPinsPerRow;
        }

        public void startComputer(){
            //ComputerPlayer computerPlayer = new ComputerPlayer();
            numberOfGuesses = 0;
        }

        private boolean maxGuessReached() {
            return numberOfGuesses == GameSetUp.maxNumberOfGuesses;
        }


        public int[] getFeedbackScore() {
            return feedbackObj.getTheFeedback();
        }


        public int getNumberOfGuesses() {
            return numberOfGuesses;
    }

}

