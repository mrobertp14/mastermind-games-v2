package poole.Mike.MyMastermind.gamePlay;

import javafx.scene.paint.Color;

import java.util.Arrays;


/*
A Row holds pins that are guessed or are secret code to be guessed. In most Mastermind versions this is 4.
 */
public class Row {

    private Color[] code;
    //Map<Row, Feedback> feedbackComparedToPossibleRows = new HashMap<>();

    public Row(Color [] code) {
       this.code = code;
    }

    public Color[] getCode() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Row row = (Row) o;
        return Arrays.equals(code, row.code);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(code);
    }
}

