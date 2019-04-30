/*
 * Drives the poker game
 */


/**
 *
 * @author Stephen Fyfe
 */
public class PokerPairedProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      PokerGame pg = new PokerGame();
      pg.playGame();
      int winner =  pg.determineWinner();
      if(winner == 0) System.out.println("The game is a tie");
      else System.out.println("Player " + winner + " is the winner");
      //put the rest of the code here
    }
    
}
