package sample;

import java.util.ArrayList;

public class Player {

  private ArrayList<Integer> deck = new ArrayList();
  private String name;

  public Player(String name){
    this.name = name;
  }

  public void addCard(int card){
    deck.add(card);
  }
  public void removeCard(){
    deck.remove(0);
  }
  public int getCard(){
    return deck.get(0);
  }
  public int getDeckSize(){
    return deck.size();
  }
  public void printDeck(){
    System.out.println(deck);
  }
}

