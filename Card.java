/*
 * This class represents a single playing card
*  author: Stephen Fyfe
 */


public class Card {
    private int value;  // 11=Jack, 12=Queen, 13=King, 14=Ace
    private String suit;
    private String fileName;
    
    public Card(int v, String s, String f) {
        value = v;
        suit = s;
        fileName = f;
    }
    
    public int getValue() {
        return value;
    }
    
    public String getSuit() {
        return suit;
    }
    
    public String toString() {
        String[] values = {"","","2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"};
        return values[value] + " of " + suit;
    }
    
    public String getFileName() {
      return fileName;
    }
}
