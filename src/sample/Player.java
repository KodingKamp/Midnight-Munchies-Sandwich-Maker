package sample;

import java.util.ArrayList;

public class Player {

  private ArrayList<Card> deck = new ArrayList();
  private String name;

  public Player(String name) {
    this.name = name;
  }

  public void addCard(Card card) {
    deck.add(card);
  }

  public void removeCard() {
    deck.remove(0);
  }

  public Card getCard() {
    return deck.get(0);
  }

  public int getDeckSize() {
    return deck.size();
  }

  public String getName() {
    return name;
  }

  public void clearDeck(){
    deck.clear();
  }

  // Prints name and suit of each card in player's deck
  public void printDeck() {
    System.out.print("[");
    // For each loop to print each card
    for (Card c : deck) {
      System.out.print(c.getName() + " of " + c.getSuit());
      // Add a comma and space if not the last card
      if (c != deck.get(deck.size() - 1)) {
        System.out.print(", ");
      }
    }
    System.out.println("]");
  }
}

