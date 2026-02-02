import javax.swing.JPanel;
import java.awt.*;

public class FateOfCards extends JPanel {

    int width = 700;
    int height = 900;

    FateOfCards() {
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        setBackground(Color.BLACK);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        // cc cc cc
    }
}
