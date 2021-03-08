package poole.Mike.MyMastermind.gui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import poole.Mike.MyMastermind.gamePlay.ColorManager;
import poole.Mike.MyMastermind.gamePlay.Game;
//import poole.Mike.MyMastermind.computerPlayer.ComputerPlayer;
import poole.Mike.MyMastermind.gamePlay.GameSetUp;

public class Controller {

    private int activeRow = 7; // Start at 7 as this is the bottom row in ui - works back to 0
    private Color brushColour = Color.BLACK;
    private  Circle[]  colorPins = new Circle[6];
    protected Model model;

    private  AnchorPane rowGuessArr[];
    private  AnchorPane feedbackArea[];

    @FXML
    public  Button guess;
    @FXML
    public  Button start;
    @FXML
    public  AnchorPane colorBox1;
    @FXML
    public  AnchorPane colorBox2;
    @FXML
    public  VBox guessArea;
    @FXML
    public  VBox feedbackBox;
    @FXML
    public Text yourScore;
    @FXML
    public Text computerScore;


    public Controller() {
        this.model = new Model(this);
    }

    public void start(){
            newGame();
            setMainRows();
            setRowListeners();

            setPinColors(colorBox1, 0);
            setPinColors(colorBox2, 4);
            setPinColorEvent();
            setFeedbackPane();
            start.setDisable(true);
            guess.setDisable(false);
            yourScore.setVisible(false);
            setAllPinsToBlack();
            activeRow = 7;
    }



    private void newGame() {
        model.startGame();
    }

    /*The guess area is the eight rows for o a player to make their guess.
        Create an Array of Rows from within the guess area.
     */
    private void setMainRows() {
        rowGuessArr = new AnchorPane[guessArea.getChildren().size()];
        for (int i = 0; i < guessArea.getChildren().size(); i++){
            rowGuessArr[i] = (AnchorPane) guessArea.getChildren().get(i);
        }
    }

    //Set all the pins to black in the feedback and guess area
    private void setAllPinsToBlack() {
        for (int i = 0; i < guessArea.getChildren().size(); i++){
            rowGuessArr[i] = (AnchorPane) guessArea.getChildren().get(i);
            for(int j=0; j < 4; j++){
                Circle c = (Circle) rowGuessArr[i].getChildren().get(j);
                Circle f = (Circle) feedbackArea[i].getChildren().get(j);
                c.setFill(Color.BLACK);
                c.setStroke(Color.BLACK);
                f.setFill(Color.BLACK);
                f.setStroke(Color.BLACK);
            }
        }
    }

    //Add a click event to every pin the guess area
    private void setRowListeners() {
        for(AnchorPane ap: rowGuessArr){
            for(int i =0; i< ap.getChildren().size(); i++) {
                ap.getChildren().get(i).setOnMouseClicked(event -> setColour(event));
            }
        }
    }
    //Add the colour to all the dummy pins - colour palette area. A user can click on one of these colour pins
    // to set their 'brush' to that colour. This allows them to click on a guess pin - making
    // it the 'brush' colour.
    private void setPinColors(AnchorPane colorBox, int start) {

        for (int i=start; i < colorBox.getChildren().size()+start; i++){
            colorPins[i] = (Circle) colorBox.getChildren().get(i-start);
            Color brushStroke = ColorManager.getAColour(i);
            colorPins[i].setStroke(brushStroke);
            colorPins[i].setFill(brushStroke);
        }

    }
    //Set Colour of 'brush' by clicking on color palette board
    private void setColour(MouseEvent event){
        Circle c = (Circle) event.getSource();
        if( rowGuessArr[activeRow].getChildren().contains(c)){
            c.setStroke(brushColour);
            c.setFill(brushColour);
        }
    }

    //If pin is clicked set to current 'brush' color
    private void setPinColorEvent() {
        for(Circle c: colorPins){
            c.setOnMouseClicked(event -> setCurrentColor(event));
        }
    }

    private void setCurrentColor(MouseEvent event) {
        Circle currentPin = (Circle) event.getSource();
        brushColour = (Color) currentPin.getFill();
    }

    /**
     * The feedback area is to the right of each guess row. A player can see
     * the feedback from a given guess. The below method creates an area of feedback areas.
     */
    private void setFeedbackPane() {
        feedbackArea = new AnchorPane[feedbackBox.getChildren().size()];
        for(int i = 0; i< feedbackBox.getChildren().size(); i++){
            feedbackArea[i] = (AnchorPane) feedbackBox.getChildren().get(i);
        }
    }

    //User has clicked guess
    public void makeGuess(){
        Color guess[] = getGuess();
        model.makeGuess(guess);
        if (activeRow >= 1) {
            activeRow--;
        }
    }

    //Result of Computer Guess
    public void printComputerFeedback(String feedbackString) {
        computerScore.setText("Computer Score: " + feedbackString);
        computerScore.setVisible(true);
    }

    //Feedback once user has guessed colour combination
    public void printHumanFeedback(String feedbackString) {
        yourScore.setText(feedbackString);
        yourScore.setVisible(true);
        guess.setDisable(true);
        start.setDisable(false);
    }

    //A guess is only valid if no pins are black
    private Color[] getGuess(){
        Color[] arr ={Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK};
        ObservableList<Node> aRow = rowGuessArr[activeRow].getChildren();
        for(int i = 0; i < aRow.size(); i++){
            Circle c = (Circle) aRow.get(i);
            arr[i] = (Color) c.getFill();
        }
        return arr;
    }

    //Give user feedback for their last guess
    public void setFeedbackResults(int correctPositions, int correctColours) {
        Circle[] feedbackPins = new Circle[feedbackArea[activeRow].getChildren().size()];
        for (int i =0; i < feedbackPins.length; i++){
            feedbackPins[i] = (Circle) feedbackArea[activeRow].getChildren().get(i);
            if(correctColours > 0){
                setfeedbackColor(feedbackPins[i], Color.WHITE);
                correctColours--;
            }
            else if(correctPositions > 0){
                setfeedbackColor(feedbackPins[i], Color.RED);
                correctPositions--;
            }
        }
    }
    //Set feedback pin to white red or black depending on how 'correct' guess is
    private void setfeedbackColor(Circle circle, Color color) {
        circle.setFill(color);
    }


















}
