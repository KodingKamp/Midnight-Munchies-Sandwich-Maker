package sample;

public class Card {
  private String name;
  private String suit;
  private int value;

  public String getName() {
    return name;
  }

  public String getSuit() {
    return suit;
  }

  public int getValue() {
    return value;
  }

  public Card(String name, String suit, int value) {
    this.name = name;
    this.suit = suit;
    this.value = value;
  }
}
