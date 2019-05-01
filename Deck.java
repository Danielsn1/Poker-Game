/*
 * This class represents a deck of cards.  It has an array of 52 cards
 */


/**
 *
 * @author Put your name here
 */
public class Deck 
{
    GenericLinkedStack<Card> drawPile;
    GenericLinkedStack<Card> discardPile;
    Card[] setOfCards;
    
    public Deck() 
    {
        drawPile = new GenericLinkedStack();
        discardPile = new GenericLinkedStack();
        setOfCards = new Card[52];
        String suit = ""; 
        String[] suits = {"hearts", "diamonds", "spades", "clubs"};
        for (int i=0; i<4; i++) {
            for (int j=2; j<=14; j++) {
                if (suits[i].equals("hearts"))
                  suit = "H";
                else if (suits[i].equals("diamonds"))
                  suit = "D";
                else if (suits[i].equals("spades"))
                  suit = "S";
                else if (suits[i].equals("clubs"))
                  suit = "C";
                  
                int value = j;
                String val = Integer.toString(value);
                if (value == 11)
                  val = "J";
                else if (value == 12)
                  val = "Q";
                else if (value == 13)
                  val = "K";
                else if (value == 14)
                  val = "A";
                  
                String filename = val + suit + ".png";
                Card c = new Card(j, suits[i], filename);
                setOfCards[(i*13)+ (j-2)] = c;
            }
        }
    }
     //This method should shuffle the array of cards and then push all the cards
    // in the stack of cards to deal.  You do not need to remove the card from the 
    //array
    public void shuffle() 
    {
        int rand = (int)(52*Math.random());
        for(int i = 0; i < 52; i++)
        {
            while(setOfCards[rand] == null)
            {
               rand = (int)(52*Math.random());
            }
            drawPile.push(setOfCards[rand]);
            setOfCards[rand] = null;
        }
            
    }
    
    //pop one card from the draw stack and return it
    public Card deal() 
    {
      //change this to work with the stack of cards to deal not the array of cards
      return drawPile.pop();
    }
    
    //pop the top of the discard stack and return it
    public Card pickup() 
    {
      return discardPile.pop();
    }
    
    //push the parameter onto the discard stack
    public void discard(Card c) 
    {
        discardPile.push(c);
    }
    
    //return the card on the top of the stack without poping it
    public Card topOfDiscard() 
    {
      return discardPile.peek();    
    }
    
    //returns true if the stack of draw cards is empty
    //false if it still has cards
    public boolean drawPileIsEmpty() 
    {
      if(drawPile.isEmpty()) return true;
      else return false;
    }
    
    //returns true if the discard stack is empty, false if it is not empty
    public boolean discardPileIsEmpty() 
    {
      if(discardPile.isEmpty()) return true;
      else return false;
    }
}
