import java.awt.*;
import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards;
    private int cardsLeft;
    private GameView view;

    public Deck(String[] ranks, String[] suits, int[] values, GameView view) {
        this.cards = new ArrayList<>();
        this.view = view;

        for (String suit : suits) {
            for (int i = 0; i < ranks.length; i++) {
                cards.add(new Card(ranks[i], suit, values[i], view));
            }
        }

        this.cardsLeft = cards.size();
        shuffle();
    }

    public boolean isEmpty() {
        return cardsLeft == 0;
    }

    public int getCardsLeft() {
        return cardsLeft;
    }

    public Card deal() {
        if (isEmpty()) {
            return null;
        }
        cardsLeft--;
        return cards.get(cardsLeft);
    }

    public void shuffle() {
        for (int i = cards.size() - 1; i > 0; i--) {
            int r = (int) (Math.random() * (i + 1));

            // Swap cards at index i and r
            Card temp = cards.get(i);
            cards.set(i, cards.get(r));
            cards.set(r, temp);
        }
        cardsLeft = cards.size();
    }

    public void draw(Graphics g) {
        g.drawImage(Card.backImage, 525, 300, Card.width, Card.height, view);
    }
}


