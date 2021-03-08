
package poole.Mike.MyMastermind;


import static org.junit.Assert.assertTrue;

import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;
import poole.Mike.MyMastermind.gamePlay.Game;
import poole.Mike.MyMastermind.gamePlay.GameSetUp;
import java.util.Arrays;


public class GameAndFeedback
{
    private static Game game;

    //Secret Code for Testing = {Color.GREEN, Color.GOLD, Color.GREEN, Color.RED}
    @Before
    public void setUp(){
        game = new Game();
        GameSetUp.generateCodeForTest();
    }

    @Test
    public void fourWhitePins(){
        Color[] testCode = {Color.RED, Color.GREEN, Color.GOLD, Color.GREEN};
        game.makeGuess(testCode);
        int [] expectedFeedback = {0,4};
        int [] actualFeedback = game.getFeedbackScore();
        assertTrue(Arrays.equals(actualFeedback, expectedFeedback));
    }

    @Test
    public void fourRedPins()
    {
        Color[] testCode = {Color.GREEN, Color.GOLD, Color.GREEN, Color.RED};
        game.makeGuess(testCode);
        int[] expectedFeedback = {4,0};
        int[] actualFeedback = game.getFeedbackScore();
        assertTrue(Arrays.equals(expectedFeedback,actualFeedback));
    }
    @Test
    public void oneRedAndOneWhite(){
        Color[] testCode = {Color.GREEN, Color.GREEN, Color.BLACK, Color.GREEN};
        game.makeGuess(testCode);
        int[] expectedFeedback = {1,1};
        int[] actualFeedback = game.getFeedbackScore();
        assertTrue(Arrays.equals(expectedFeedback,actualFeedback));
    }

    @Test
    public void oneRedNoWhite(){
        Color[] testCode = {Color.BLACK, Color.GOLD, Color.GOLD, Color.BLACK};
        game.makeGuess(testCode);
        int[] expectedFeedback = {1,0};
        int[] actualFeedback = game.getFeedbackScore();
        assertTrue(Arrays.equals(expectedFeedback,actualFeedback));
    }
}
