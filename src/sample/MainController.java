package sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {
  private int cardIndexCounter = 0;
  private Player you = new Player("You");
  private Player opponent = new Player("Opponent");
  private ArrayList<Card> preGameDeck;

  public Player getYou() {
    return you;
  }

  public Player getOpponent() {
    return opponent;
  }

  public void continueGame(){
    // while (tableYourDeck.hasNectCard)
    // preGameDeck = tableYourDeck.nextCard
    for (Card c : preGameDeck){
      you.addCard(c);
    }
    // preGameDeck.clear();
    // while (tableOpponentDeck.hasNextCard)
    // preGameDeck = tableOpponentDeck.nextCard
    for (Card c : preGameDeck){
      opponent.addCard(c);
    }
  }

  @FXML
  public void newGame() throws IOException {
    you.clearDeck();
    opponent.clearDeck();
    String[] cardNames = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
    String[] suits = {"Spades", "Clubs", "Diamonds", "Hearts"};

    preGameDeck = new ArrayList<>();
    // Nested loop to add 4 sets of cards 1 - 13
    for (int i = 0; i < 13; i++){
      for (int j = 0; j < 4; j++){
        preGameDeck.add(new Card(cardNames[i], suits[j], i));
      }
    }
    // Shuffle preGameDeck to be distributed to each player's deck
    Collections.shuffle(preGameDeck);

    // Loop to add half of preGameDeck to one player and half to the other
    for(int i = 0; i < 52; i++){
      if (i < 26){
        you.addCard(preGameDeck.get(i));
      }
      else{
        opponent.addCard(preGameDeck.get(i));
      }
    }

    // Testing shuffling of deck.
    you.printDeck();
    System.out.println(you.getDeckSize());
    opponent.printDeck();
    System.out.println(opponent.getDeckSize());

    Stage stage = Main.getPrimaryStage();
    Parent root = FXMLLoader.load(getClass().getResource("InGame.fxml"));
    stage.setScene(new Scene(root, 600, 400));
    stage.show();
  }


}
