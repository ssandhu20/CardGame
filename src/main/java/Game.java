import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private final String [] suits = {"Hearts", "Clubs", "Diamonds", "Spades"};
    private final String [] rank = {"Ace", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    private final int[] value = {1,2,3,4,5,6,7,8,9,10,11};

    public Deck deck;
    private ArrayList<Card> discardPile;
    private Player player1;
    private Player player2;
    private Scanner scanner;
    private int maxScore;
    ArrayList<Card> hand = player1.getHand();

    // initializes all aspects of a game into the current game they are playing
    public Game(String player1, String player2, int maxScore) {
        this.player1 = new Player(player2);
        this.player2 = new Player(player1);
        this.discardPile = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.maxScore = maxScore;
        this.deck = new Deck(rank, suits, value);
    }

    // this is a method to deal the cards to each player
    public void dealCards() {
        for (int i = 0; i < 7; i++) {
            player1.addCard(deck.deal());
            player2.addCard(deck.deal());
        }
        discardPile.add(deck.deal());
    }

    //when this is called it will display each card in a players hand
    public void displayHand(Player player1) {
        ArrayList<Card> hand = player1.getHand();
        for (int i = 0; i < hand.size(); i++) {
            System.out.println(i + ": " + hand.get(i));
        }
    }

    // this will take the point value of each card in a hand and add them together to return the value
    public int calculateHandPoints(ArrayList<Card> hand) {
        int points = 0;
        for (Card card : hand) {
            points += card.getValue();
        }
        return points;
    }


    // I know these methods do not work...nor make sense... but they are a current place holder to keep my thoughts on how this should be done... I still need to create some methods and variables for this I think and not use a nested 4 loop
   /* public boolean checkForThreeOfAKind()
        {
            for (int i = 0; i < hand.size(); i++)
            {
                for (int j = 0; j < hand.size(0); j++)
                {
                    if (card[i][j]== card[i][j-1] && card[i][j]== card[i][j-2])
                        return true;
                    }
                }
            }
            return false;

        }
  /*  public boolean checkForFourOfAKind()
    {
        for (int i = 0; i < hand.size(); i++)
        {
            for (int j = 0; j < hand.size(0); j++)
            {
                if (card[i][j]== card[i][j-1] && card[i][j]== card[i][j-2] && card[i][j]== card[i][j-3])
                    return true;
            }
        }
        return false;
    }**/
    public static void main(String[] args) {
        Game g = new Game( "sachin", "pete", 50);
    }
}

