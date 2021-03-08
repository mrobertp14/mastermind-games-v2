
package poole.Mike.MyMastermind.player;

import javafx.scene.paint.Color;
import poole.Mike.MyMastermind.gamePlay.Game;
import poole.Mike.MyMastermind.gamePlay.GameSetUp;

import java.util.Scanner;

public class HumanPlayer {

    private final Game gb;

    public HumanPlayer() {
        gb = new Game();
        gb.startGame();
    }

    public boolean makeGuess(){
        Color[] guess = new Color[GameSetUp.numberOfPinsPerRow];
        Scanner scan = createScanner();
        for (int i =0; i < GameSetUp.numberOfPinsPerRow; i++){
            System.out.print("Box " + i +": ");
            guess[i] = Color.valueOf(scan.nextLine());
        }
        return checkGameOver(guess, scan);

    }

    private boolean checkGameOver(Color[]guess, Scanner scan) {
        if (gb.makeGuess(guess)){
            closeScanner(scan);
            return true;
        }
        return false;
    }

    private void closeScanner(Scanner scan) {
        scan.close();
    }

    private Scanner createScanner() {
        return new Scanner(System.in);
    }


}

