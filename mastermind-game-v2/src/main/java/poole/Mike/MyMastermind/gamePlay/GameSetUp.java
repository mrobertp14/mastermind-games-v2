package poole.Mike.MyMastermind.gamePlay;
import javafx.scene.paint.Color;
import java.util.Random;

public class GameSetUp {
    public static final int numberOfPinsPerRow = 4;
    public static final int numberOfColours = 6;
    public static final int numberOfRows = 8;
    public static final int maxNumberOfGuesses =8;
    private static Color[] secretCode = {Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK};

    public static void generateSecretCode(){
        Random rand = new Random();
        for(int i =0; i< secretCode.length; i++){
            secretCode [i] = ColorManager.getAColour(rand.nextInt(numberOfColours));
        }
    }

    public static void printSecretCode(){
        for(Color pin: secretCode){
            System.out.print(pin.toString() + ",");
        }
        System.out.println();
    }

    public static Color[] getSecretCode() {
        return secretCode;
    }

    public static void generateCodeForTest(){
        secretCode = new Color[]{Color.GREEN, Color.GOLD, Color.GREEN, Color.RED};
    }
}
