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
        boolean p1IsPlaying = p1 == backend.getCurrentPlayer();
        boolean p2IsPlaying = p2 == backend.getCurrentPlayer();

        if (p1 == null || p2 == null) return;

        ArrayList<Card> hand1 = p1.getHand();
        ArrayList<Card> hand2 = p2.getHand();

        int xStart = 100;
        int yBottom = 550;
        int yTop = 50;
        int spacing = 80;

        // Draw Player 1 (bottom) with label
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString(p1.getName() + " - " + p1.getPoints() + " points", xStart, yBottom - 10);

        for (int i = 0; i < hand1.size(); i++) {
            Card c = hand1.get(i);
            c.draw(g, xStart + i * spacing, yBottom, p1IsPlaying);
        }

        // Draw Player 2 (top) with label
        g.drawString(p2.getName() + " - " + p2.getPoints() + " points", xStart, yTop - 10);

        for (int i = 0; i < hand2.size(); i++) {
            Card c = hand2.get(i);
            c.draw(g, xStart + i * spacing, yTop, p2IsPlaying);
        }
    }

    private void drawDiscardPile(Graphics g) {
        if (backend.discardPile == null || backend.discardPile.size() == 0) return;

        int xDiscard = 350;
        int yDiscard = 300;

        // Draw label
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("Discard Pile", xDiscard, yDiscard - 10);

        // Draw top card of discard pile
        Card top = backend.discardPile.getLast();
        if (top != null)
            top.draw(g, xDiscard, yDiscard, true);

    }

    private void drawDeck(Graphics g) {
        if (backend.deck == null) return;

        int xDeck = 500;
        int yDeck = 300;

        // Draw label
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("Deck (" + backend.deck.getCardsLeft() + " cards)", xDeck, yDeck - 10);
        // Draw the deck
        backend.deck.draw(g);
    }




}