package poole.Mike.MyMastermind.gui;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import poole.Mike.MyMastermind.player.HumanPlayer;

import java.io.File;
import java.net.URL;

import static javafx.fxml.FXMLLoader.load;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        URL url = new File("src/main/java/poole/Mike/MyMastermind/gui/sample.fxml").toURI().toURL();
        Parent root = load((url));
        primaryStage.setTitle("Mastermind game");
        primaryStage.setScene(new Scene(root,518, 454));
        primaryStage.show();


    }


    public static void main(String[] args) {

        launch(args);
        //HumanPlayer hp = new HumanPlayer();

    }
}
