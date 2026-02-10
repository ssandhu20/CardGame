import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {

    public static final int WINDOW_WIDTH = 500;
    public static final int WINDOW_HEIGHT = 500;

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

        if (backend.getState() == Game.STATE_INSTR) {
            g.setColor(Color.GRAY);
        } else if (backend.getState() == Game.STATE_MAIN) {
            g.setColor(Color.RED);
        } else if (backend.getState() == Game.STATE_END) {
            g.setColor(Color.GREEN);
        }
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
    }
}