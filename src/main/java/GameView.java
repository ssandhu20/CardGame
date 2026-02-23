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

        g.setColor(new Color(0, 100, 0));
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        drawHands(g);
        drawDiscardPile(g);
    }

    private void drawHands(Graphics g) {

        Player p1 = backend.deck != null ? backend.player1 : null;
        Player p2 = backend.deck != null ? backend.player2 : null;

        if (p1 == null || p2 == null) return;

        ArrayList<Card> hand1 = p1.getHand();
        ArrayList<Card> hand2 = p2.getHand();

        int xStart = 100;
        int yBottom = 500;
        int spacing = 80;

        // Draw Player 1 (bottom)
        for (int i = 0; i < hand1.size(); i++) {
            Card c = hand1.get(i);
            g.drawImage(c.getImage(), xStart + i * spacing, yBottom, 70, 100, null);
        }

        // Draw Player 2 (top)
        int yTop = 80;
        for (int i = 0; i < hand2.size(); i++) {
            Card c = hand2.get(i);
            g.drawImage(c.getImage(), xStart + i * spacing, yTop, 70, 100, null);
        }
    }

    private void drawDiscardPile(Graphics g) {

        if (backend.deck == null) return;

        if (backend.discardPile.size() > 0) {
            Card test = new Card("Ace", "Spades", 1);
            Card testTwo = new Card("Back", "Card", 0);
//            Card top = backend.discardPile.get(backend.discardPile.size() - 1);
            g.drawImage(test.getImage(), 350, 300, 70, 100, null);
            g.drawImage(testTwo.getImage(), 450, 300, 70, 100, null);
            g.drawImage(testTwo.getImage(), 375, 100, 70, 100, null);
            g.drawImage(testTwo.getImage(), 385, 100, 70, 100, null);
            g.drawImage(testTwo.getImage(), 395, 100, 70, 100, null);
            g.drawImage(testTwo.getImage(), 405, 100, 70, 100, null);
            g.drawImage(testTwo.getImage(), 415, 100, 70, 100, null);
            g.drawImage(testTwo.getImage(), 425, 100, 70, 100, null);
            g.drawImage(testTwo.getImage(), 435, 100, 70, 100, null);


            System.out.println();
        }
    }
}
