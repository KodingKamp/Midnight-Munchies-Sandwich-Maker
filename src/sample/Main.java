package sample;

import java.util.ArrayList;
import java.util.Collections;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        primaryStage.setTitle("");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }


    public static void main(String[] args) {
        ArrayList<Integer> preGameDeck = new ArrayList<>();
        // Nested loop to add 4 sets of cards 1 - 13
        for (int i = 0; i < 13; i++){
            for (int j = 0; j < 4; j++){
                preGameDeck.add(new Integer(i));
            }
        }
        // Shuffle preGameDeck to be distributed to each player's deck
        Collections.shuffle(preGameDeck);

        Player you = new Player("You");
        Player opponent = new Player("Opponent");

        // Loop to add half of preGameDeck to one player and half to the other
        for(int i = 0; i < 52; i++){
            if (i < 26){
                you.addCard(preGameDeck.get(i));
            }
            else{
                opponent.addCard(preGameDeck.get(i));
            }
        }
        you.printDeck();
        System.out.println(you.getDeckSize());
        opponent.printDeck();
        System.out.println(opponent.getDeckSize());


        launch(args);
    }
}
