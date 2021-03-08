package poole.Mike.MyMastermind.gamePlay;

import javafx.scene.paint.Color;

public class ColorManager {

    //List of all colours
    private static final Color[] colors = {Color.GOLD, Color.GREEN, Color.BLUE,Color.RED, Color.PINK,
            Color.PURPLE};

    public static Color getAColour(int index){
        return colors[index];
    }
}
