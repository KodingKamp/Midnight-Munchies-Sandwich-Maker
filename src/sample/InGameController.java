package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class InGameController {

  private int cardIndexCounter = 0;
  @FXML
  private Label deckSize0;
  @FXML
  private Label deckSize1;
  @FXML
  private Label cardFace0;
  @FXML
  private Label cardSuit0;
  @FXML
  private Label cardFace1;
  @FXML
  private Label cardSuit1;
  @FXML
  private Label cardOf0;
  @FXML
  private Label cardOf1;
  @FXML
  private Button dealBtn;
  @FXML
  private Button startBtn;

  public void start() {
    startBtn.setDisable(true);
    startBtn.setVisible(false);
    dealBtn.setDisable(false);
    dealBtn.setVisible(true);
  }

  public void deal() {
    Card yourCard = MainController.getYou().getCard(cardIndexCounter);
    Card opponentsCard = MainController.getOpponent().getCard(cardIndexCounter);

    cardFace0.setText(yourCard.getName());
    cardSuit0.setText(yourCard.getSuit());
    cardFace1.setText(opponentsCard.getName());
    cardSuit1.setText(opponentsCard.getSuit());
    cardOf0.setText("of");
    cardOf1.setText("of");

    if (yourCard.getValue() == opponentsCard.getValue()) {
      cardIndexCounter += 4;
      // Check if cards remain in decks

      // delay()
      //deal(); // HASHTAG RECURSION!!!!!!
    } else if (yourCard.getValue() > opponentsCard.getValue()) {
      cardExchange(MainController.getYou(), MainController.getOpponent());
      cardIndexCounter = 0;
    } else if (yourCard.getValue() < opponentsCard.getValue()) {
      cardExchange(MainController.getOpponent(), MainController.getYou());
      cardIndexCounter = 0;
    } else {
      // ??
    }

    // Update Deck Size Label
    setDeckSize(MainController.getYou().getDeckSize(), deckSize0);
    setDeckSize(MainController.getOpponent().getDeckSize(), deckSize1);

    // Check if cards remain in decks
    if (MainController.getYou().getDeckSize() == 0) {
      // you lose
      dealBtn.setDisable(true);

    } else if (MainController.getOpponent().getDeckSize() == 0) {
      // you win
      dealBtn.setDisable(true);

    }


  }

  public void cardExchange(Player pWin, Player pLose) {
    // add cards to winning player's deck
    for (int i = 0; i <= cardIndexCounter; i++) {
      pWin.addCard(pWin.getCard(i));
      pWin.addCard(pLose.getCard(i));
    }
    // remove cards from both players deck
    for (int i = 0; i <= cardIndexCounter; i++) {
      pWin.removeCard();
      pLose.removeCard();
    }
  }

  @FXML
  public void setDeckSize(int size, Label label) {
    label.setText(Integer.toString(size));
  }

}
