import java.util.ArrayList;

public class Deck {
    private final String [] suits = {"Hearts", "Clubs", "Diamonds", "Spades"};
    private final String [] rank = {"Ace", "1", "2", "3", "4", "5", "6" "7", "8", "9", "10", "Jack", "Queen", "King"};
    private final int[] value = {1,2,3,4,5,6,7,8,9,10,11}
    private ArrayList<Card> cards;
    private int cardsLeft;

    public Deck(ArrayList<Card> cards, int cardsLeft) {
        this.cards = cards;
        this.cardsLeft = cardsLeft;
    }
}


