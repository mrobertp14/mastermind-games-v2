package poole.Mike.MyMastermind.gamePlay;

import javafx.scene.paint.Color;

import java.util.*;

public class Feedback {

    final private int[] theFeedback;
    private Set<Integer> pinGuessCorrectly;
    private List<Color> theSecretCode;
    public Feedback() {
        theFeedback = new int[2];
        pinGuessCorrectly = new HashSet<>();
    }

    /*
    *Calculate number of correctly guessed colours and correctly guessed colours in right position.
     */
    public void generateFeedback(Color[] secretCode, Color[] theGuess) {
        theSecretCode = new ArrayList<Color>(Arrays.asList(secretCode));
        theFeedback[0] = numberOfCorrectPositions(secretCode, theGuess);
        theFeedback[1] = numberOfCorrectColours(secretCode, theGuess);
    }

        /**
         * Calculate the number of Correct Colour Pins Placed in the correct positions.
         * In mastermind these are denoted by a red colour pin. Iterate through the guessed code and the secret code.
         * If at any point they equal the same then add one to count.
         *
         * @return
         */

    private int numberOfCorrectPositions(Color[] secretCode, Color[] theGuess) {
        int count = 0;

        for (int i = 0; i < secretCode.length; i++) {
            if (secretCode[i].toString().equals(theGuess[i].toString()) ) {
                pinGuessCorrectly.add(i);
                theSecretCode.remove(theGuess[i]);
                count++;
            }
        }
        return count;
    }


    /*
    *Compare guessed code to secret code. If a colour is in the secret code but has been placed in wrong position
    * then add one to count. In Mastermind this is denoted by a white pin.
    * A player may use the same colour twice in their guess. A pin(one guess) can only give one set of feedback
     */
    private int numberOfCorrectColours(Color[] secretCode, Color[] theGuess) {

        int count = 0;
        for (int i = 0; i < secretCode.length; i++) {
            if(theSecretCode.toString().contains(theGuess[i].toString()) && !pinGuessCorrectly.contains(i)){
                pinGuessCorrectly.add(i);
                theSecretCode.remove(theGuess[i]);
                count++;
            }
        }
        return count;
    }

    public int[] getTheFeedback() {
        return theFeedback;
    }
}


