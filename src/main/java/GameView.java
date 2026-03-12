import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameView extends JFrame {

    public static final int WINDOW_WIDTH = 900;
    public static final int WINDOW_HEIGHT = 700;

    private Game backend;
    private JPanel gamePanel;

    public GameView(Game backend) {
        this.backend = backend;

        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setTitle("Rummy Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // --- NEW: Custom Panel for Drawing ---
        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); // Always call this first

                // Draw green felt background
                g.setColor(new Color(0, 100, 0));
                g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

                drawHands(g);
                drawDiscardPile(g);
                drawDeck(g);

                Card testTwo = new Card("Back", "Card", 0);
                if (testTwo.getImage() != null) {
                    g.drawImage(testTwo.getImage(), 525, 300, 70, 100, null);
                }
            }
        };

        gamePanel.setLayout(null); // Allows absolute positioning of the button

        JButton restartButton = new JButton("Restart Game");
        restartButton.setBounds(375, 10, 150, 40);
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ensure you have added the resetGame() method to Game.java as discussed!
                backend.resetGame();
                gamePanel.repaint();
            }
        });

        gamePanel.add(restartButton);
        this.add(gamePanel);

        this.setVisible(true);
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
            }
        }

        // Draw Player 2 (top) with label
        g.drawString(p2.getName() + " - " + p2.getPoints() + " points", xStart, yTop - 10);

        for (int i = 0; i < hand2.size(); i++) {
            Card c = hand2.get(i);
            if (c.getImage() != null && c.getImage().getWidth(null) > 0) {
                g.drawImage(c.getImage(), xStart + i * spacing, yTop, cardWidth, cardHeight, null);
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
        }
    }

    private void drawDeck(Graphics g) {
        if (backend.deck == null) return;

        int xDeck = 500;
        int yDeck = 300;

        // Draw label
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("Deck (" + backend.deck.getCardsLeft() + " cards)", xDeck, yDeck - 10);
    }
}