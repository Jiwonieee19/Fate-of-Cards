import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FateOfCards extends JPanel implements ActionListener {

    int width = 700;
    int height = 900;

    class cards {
        int x, y, width, height;
        Image img;
        Color standard = Color.WHITE;

        cards(int x, int y, int width, int height, Image img) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.img = img;
        }
    }

    Timer timer;

    FateOfCards() {
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        setBackground(Color.BLACK);

        timer = new Timer(1000 / 10, this); // 10 fps
        timer.start();

    }

    public void move() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("Fate of Cards", width / 2 - 30, 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }
}
