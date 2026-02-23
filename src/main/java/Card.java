import javax.swing.*;
import java.awt.*;

public class Card {
    private String suit;
    private String rank;
    private int value;
    private Image image;

    public Card(String rank, String suit, int value)
    {
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

    public String toString() {
        return rank + " of " + suit;
    }
}
