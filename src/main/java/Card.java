import javax.swing.*;
import java.awt.*;

public class Card {
    public static final Image backImage = new ImageIcon("src/main/resources/back_of_card.png").getImage();
    public static final int width = 70;
    public static final int height = 100;
    private String suit;
    private String rank;
    private int value;
    private Image image;
    private GameView view;

    public Card(String rank, String suit, int value, GameView view)
    {
        this.view = view;
        this.rank = rank;
        this.suit = suit;
        this.value = value;

        loadImage();
    }

    private void loadImage() {
        String fileName = "src/main/resources/" +
                rank.toLowerCase() + "_of_" +
                suit.toLowerCase() + ".png";

        ImageIcon icon = new ImageIcon(fileName);
        image = icon.getImage();
    }

    public Image getImage() {
        return image;
    }

    public String getSuit() { return suit; }
    public String getRank() { return rank; }
    public int getValue() { return value; }

    // Draw the card image
    public void draw(Graphics g, int x, int y, boolean isVisible) {
        g.drawImage(isVisible ? image : backImage, x, y, width, height, view);
    }

    public String toString() {
        return rank + " of " + suit;
    }
}
