import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private final String[] suits = {"Hearts", "Clubs", "Diamonds", "Spades"};
    private final String[] rank = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    private final int[] value = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

    public Deck deck;
    private ArrayList<Card> discardPile;
    private Player player1;
    private Player player2;
    private Scanner scanner;
    private int maxScore;


    public Game(String player1Name, String player2Name, int maxScore) {
        this.player1 = new Player(player1Name);
        this.player2 = new Player(player2Name);
        this.discardPile = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.maxScore = maxScore;
        this.deck = new Deck(rank, suits, value);
    }


    public void dealCards() {
        for (int i = 0; i < 7; i++) {
            player1.addCard(deck.deal());
            player2.addCard(deck.deal());
        }
        discardPile.add(deck.deal());
    }


    public void displayHand(Player player) {
        ArrayList<Card> hand = player.getHand();
        for (int i = 0; i < hand.size(); i++) {
            System.out.println(i + ": " + hand.get(i));
        }
    }


    public int calculateHandPoints(ArrayList<Card> hand) {
        int points = 0;
        for (Card card : hand) {
            points += card.getValue();
        }
        return points;
    }


    public boolean checkForThreeOfAKind(ArrayList<Card> hand) {
        for (int i = 0; i < hand.size(); i++) {
            int count = 0;
            for (int j = 0; j < hand.size(); j++) {
                if (hand.get(i).equals(hand.get(j))) {
                    count++;
                }
            }
            if (count >= 3) {
                return true;
            }
        }
        return false;
    }


    public boolean checkForFourOfAKind(ArrayList<Card> hand) {
        for (int i = 0; i < hand.size(); i++) {
            int count = 0;
            for (int j = 0; j < hand.size(); j++) {
                if (hand.get(i).equals(hand.get(j))) {
                    count++;
                }
            }
            if (count >= 4) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Game g = new Game("sachin", "pete", 50);
    }
}