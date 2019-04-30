/*
 * Class that plays the poker game
 */
import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

/**
 *
 * @author Put your name here
 */
public class PokerGame {
    Deck cards;
    Card[] hand1 = new Card[5];
    Card[] hand2 = new Card[5];
    
    //creates the deck and has it shuffle and then deals 5 cards to each hand
    public PokerGame() 
    {
        cards = new Deck();
        cards.shuffle();
        for(int i = 0; i < 5; i++)
        {
            hand1[i] = cards.deal();
            hand2[i] = cards.deal();
        }
    }
    
    //Plays rounds until all the cards have been dealt
    public void playGame() 
    {
      while(!cards.drawPileIsEmpty())
      {
         hand1 = turn(hand1,1);
         if(cards.drawPileIsEmpty()) break;
         hand2 = turn(hand2,2);
      }
    }
    
    public Card[] turn(Card[] hand,int num)
    {
         display(hand,num);
         if(!cards.discardPileIsEmpty())
         {
            displayCard(cards.topOfDiscard(), "Discard",550,300);
         }
         else
         {
            System.out.println("Discard Pile is Empty");
         }
         Scanner sc = new Scanner(System.in);
         Card temp = null;
         if(cards.discardPileIsEmpty())
         {
            hand = draw(num,hand);
         }
         else
         {
            System.out.print("Enter 1 to draw a card or Enter 2 to take from the top of the discard\n");
            if(sc.nextInt() == 1)
            {
               hand = draw(num,hand);
            }
            else
            {
               temp = cards.pickup();
               display(temp, "Pickedup Card",num);
               displayCard(cards.topOfDiscard(), "Discard",550,300);
               System.out.print("Enter card from the left you want to swap with the drawn card\n");
               swap(temp,sc.nextInt(),num,hand);
            }
         }
         display(hand,num);
         return hand;
    }
    public void display(Card temp, String purpose,int num)
    {
      if(num == 1) displayCard(temp,purpose,100,300);
      if(num == 2) displayCard(temp,purpose,1005,300);
    }
    public void display(Card[] hand,int num)
    {
      if(num == 1) displayHand(hand,num,100,100);
      if(num == 2) displayHand(hand,num,775,100);
    }
    public Card[] draw(int num,Card[] hand)
    {
      Scanner sc = new Scanner(System.in);
      Card temp = cards.deal();
      display(temp, "Drawn Card",num);
      System.out.print("Enter a 1 to discard the drawn card \nEnter a 2 replace a card in your hand with the drawn card\n");
      if(sc.nextInt() == 1)
      {
         cards.discard(temp);
      }
      else
      {
         System.out.print("Enter position of card in your hand from the left that you want to swap with the drawn card\n");
         swap(temp,sc.nextInt(),num,hand);
      }
      return hand;
   }

      
    public Card[] swap(Card c, int pos, int num,Card[] hand)
    {
         cards.discard(hand[pos-1]);
         hand[pos-1] = c;
         return hand;
    }
         
    
    //looks at the hands and determines the winner.  Returns a 1 or a 2 depending
    //on which hand was the best
    public int determineWinner() 
    {
         hand1 = sortHands(hand1);
         hand2 = sortHands(hand2);
         
         /*Card[] hnd1 = {new Card(14,"Hearts",""), new Card(13,"Hearts",""),new Card(12,"Hearts",""),new Card(11,"Hearts",""),new Card(10,"Hearts","")};
         Card[] hnd2 = {new Card(2,"Hearts",""),new Card(3,"Hearts",""),new Card(4,"Hearts",""),new Card(5,"Hearts",""),new Card(6,"Hearts","")};
         Card[] hnd3 = {new Card(2,"Hearts",""),new Card(2,"",""),new Card(2,"",""),new Card(2,"",""),new Card(6,"","")};
         Card[] hnd4 = {new Card(2,"Hearts",""),new Card(4,"Hearts",""),new Card(6,"Hearts",""),new Card(8,"Hearts",""),new Card(10,"Hearts","")};
         Card[] hnd5 = {new Card(2,"Spades",""),new Card(3,"Hearts",""),new Card(4,"Hearts",""),new Card(5,"Hearts",""),new Card(6,"Hearts","")};
         Card[] hnd6 = {new Card(2,"Spades",""),new Card(2,"Hearts",""),new Card(6,"Hearts",""),new Card(8,"Hearts",""),new Card(10,"Hearts","")};
         
         System.out.println(determineScore(hnd1));
         System.out.println(determineScore(hnd2));
         System.out.println(determineScore(hnd3));
         System.out.println(determineScore(hnd4));
         System.out.println(determineScore(hnd5));
         System.out.println(determineScore(hnd6));
         */
         
         if(determineScore(hand1) < determineScore(hand2)) return 1;
         else if(determineScore(hand1) > determineScore(hand2)) return 2;
         else return 0;
                
    }         
    
    public int determineScore(Card[] hand)
    {
         int score = 100;
         if(isFlush(hand))
         {
            if(isRoyal(hand) && score > 1) score = 1;
            if(isStraight(hand) && score > 2) score = 2;
            if(score > 5) score = 5;
         }
         else
         {
            if(isFourOfKind(hand) && score > 3) score = 3;
            if(isStraight(hand) && score > 6) score = 6;
            if(isPair(hand) && score > 9) score = 9;
         }
         return score;
    }
    
    public boolean isStraight(Card[] hand)
    {
         for(int i = 1; i < hand.length; i++)
         {
            if(hand[i-1].getValue() != (hand[i].getValue()-1)) return false;
         }
         return true;
    }
    
    public boolean isRoyal(Card[] hand)
    {
         for(int i = 0; i < hand.length; i++)
         {
            if(hand[i].getValue() < 10) return false;
         }
         return true;
    }
    
    public boolean isFourOfKind(Card[] hand)
    {
         if(hand[0].getValue() != hand[1].getValue())
         {
            for(int i = 2; i < hand.length; i++)
            {
               if(hand[i-1].getValue() != hand[i].getValue()) return false;
            }
            return true;
         }
         else
         {
         for(int i = 1; i < hand.length-1; i++)
            {
               if(hand[i-1].getValue() != hand[i].getValue()) return false;
            }
            return true;
         }
    }
    
    public boolean isPair(Card[] hand)
    {
         for(int i =1; i < hand.length; i++)
         {
            if(hand[i-1].getValue() == hand[i].getValue()) return true;
         }
         return false;
    }
         
    
    public boolean isFlush(Card[] hand)
    {
         for(int i = 1; i < hand.length; i++)
         {
            if(!(hand[0].getSuit().equals(hand[i].getSuit())))return false;
         }
         return true;
    }
    
    public Card[] sortHands(Card[] hand)
    {
      for (int i=1; i < hand.length; i++) 
      {
         Card key = hand[i];
         int j = i - 1;
         while  ( (j >= 0) && (key.getValue() < hand[j].getValue()) ) 
         {
            hand[j+1] = hand[j];
            j--;
         }
         hand[j+1] = key;
      }
      return hand;
    }
    
    public void displayHand(Card[] hand,int num,int placementX,int placementY) {
      JFrame cardFrame = new JFrame("Hand "+num);
      cardFrame.setLayout(new FlowLayout());
      JPanel cardPanel = new JPanel();
      cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.X_AXIS));
      for (Card c : hand) {
        String fn = c.getFileName();
        String fullname = "cards/" + fn;
        ImageIcon cardIcon = new ImageIcon(new ImageIcon(fullname).getImage().getScaledInstance(100, 125, Image.SCALE_DEFAULT));
        JLabel cardLabel = new JLabel(cardIcon);
        cardPanel.add(cardLabel);
      }
      
      cardFrame.add(cardPanel);
      cardFrame.setSize(550,175);
      cardFrame.setLocation(placementX,placementY);
      cardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      cardFrame.setVisible(true);
  
    }

      
  public void displayCard(Card c, String purpose,int placementX,int placementY) {
    JFrame cardFrame = new JFrame(purpose);
    cardFrame.setLayout(new FlowLayout());
    String fullname = "cards/" + c.getFileName();
    ImageIcon cardIcon = new ImageIcon(new ImageIcon(fullname).getImage().getScaledInstance(100, 125, Image.SCALE_DEFAULT));
    JLabel cardLabel = new JLabel(cardIcon);
    cardFrame.add(cardLabel);
    cardFrame.setSize(320,175);
    cardFrame.setLocation(placementX,placementY);
    cardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    cardFrame.setVisible(true);

  }
}
