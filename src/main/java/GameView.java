import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameView extends JFrame {

    public static final int WINDOW_WIDTH = 900;
    public static final int WINDOW_HEIGHT = 700;

    private Game backend;

    public GameView(Game backend) {
        this.backend = backend;

        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setTitle("Rummy Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        super.paint(g);

        // Draw green felt background
        g.setColor(new Color(0, 100, 0));
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        drawHands(g);
        drawDiscardPile(g);
        drawDeck(g);
    }

    private void drawHands(Graphics g) {
        Player p1 = backend.player1;
        Player p2 = backend.player2;

        if (p1 == null || p2 == null) return;

        ArrayList<Card> hand1 = p1.getHand();
        ArrayList<Card> hand2 = p2.getHand();

        int xStart = 100;
        int yBottom = 550;
        int yTop = 50;
        int spacing = 80;
        int cardWidth = 70;
        int cardHeight = 100;

        // Draw Player 1 (bottom) with label
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString(p1.getName() + " - " + p1.getPoints() + " points", xStart, yBottom - 10);

        for (int i = 0; i < hand1.size(); i++) {
            Card c = hand1.get(i);
            if (c.getImage() != null && c.getImage().getWidth(null) > 0) {
                g.drawImage(c.getImage(), xStart + i * spacing, yBottom, cardWidth, cardHeight, null);
            } else {
                // Draw placeholder card
                drawPlaceholderCard(g, xStart + i * spacing, yBottom, cardWidth, cardHeight, c);
            }
        }

        // Draw Player 2 (top) with label
        g.drawString(p2.getName() + " - " + p2.getPoints() + " points", xStart, yTop - 10);

        for (int i = 0; i < hand2.size(); i++) {
            Card c = hand2.get(i);
            if (c.getImage() != null && c.getImage().getWidth(null) > 0) {
                g.drawImage(c.getImage(), xStart + i * spacing, yTop, cardWidth, cardHeight, null);
            } else {
                // Draw placeholder card
                drawPlaceholderCard(g, xStart + i * spacing, yTop, cardWidth, cardHeight, c);
            }
        }
    }

    private void drawDiscardPile(Graphics g) {
        if (backend.discardPile == null || backend.discardPile.size() == 0) return;

        int cardWidth = 70;
        int cardHeight = 100;
        int xDiscard = 350;
        int yDiscard = 300;

        // Draw label
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("Discard Pile", xDiscard, yDiscard - 10);

        // Draw top card of discard pile
        Card top = backend.discardPile.get(backend.discardPile.size() - 1);
        if (top.getImage() != null && top.getImage().getWidth(null) > 0) {
            g.drawImage(top.getImage(), xDiscard, yDiscard, cardWidth, cardHeight, null);
        } else {
            drawPlaceholderCard(g, xDiscard, yDiscard, cardWidth, cardHeight, top);
        }
    }

    private void drawDeck(Graphics g) {
        if (backend.deck == null) return;

        int cardWidth = 70;
        int cardHeight = 100;
        int xDeck = 500;
        int yDeck = 300;

        // Draw label
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("Deck (" + backend.deck.getCardsLeft() + " cards)", xDeck, yDeck - 10);

        // Draw card back (multiple overlapping to show deck)
        for (int i = 0; i < 5; i++) {
            drawCardBack(g, xDeck + i * 2, yDeck + i * 2, cardWidth, cardHeight);
        }
    }

    private void drawPlaceholderCard(Graphics g, int x, int y, int width, int height, Card card) {
        // Draw white rectangle for card
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);

        // Draw border
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);

        // Draw rank and suit text
        g.setFont(new Font("Arial", Font.BOLD, 12));
        String text = card.getRank();
        g.drawString(text, x + 5, y + 20);

        String suit = card.getSuit();
        // Color based on suit
        if (suit.equals("Hearts") || suit.equals("Diamonds")) {
            g.setColor(Color.RED);
        }
        g.setFont(new Font("Arial", Font.PLAIN, 10));
        g.drawString(suit, x + 5, y + 35);
    }

    private void drawCardBack(Graphics g, int x, int y, int width, int height) {
        // Load and draw the card back image
        ImageIcon cardBackIcon = new ImageIcon("back_of_card.png");
        Image cardBackImage = cardBackIcon.getImage();

        if (cardBackImage != null && cardBackImage.getWidth(null) > 0) {
            g.drawImage(cardBackImage, x, y, width, height, null);

        }
    }
}