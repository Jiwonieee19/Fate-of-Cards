import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FateOfCards extends JPanel implements ActionListener {

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

    int width = 700;
    int height = 900;
    int pixel = 20;
    Timer fpsTimer;
    Timer roundPrepTimer;
    Timer roundBattleTimer;
    private Image starImg;
    private Image towerImg;
    private Image devilImg;
    boolean donePrep;

    FateOfCards() {
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        setBackground(Color.BLACK);

        starImg = new ImageIcon(getClass().getResource("assets/star.jpg")).getImage();
        towerImg = new ImageIcon(getClass().getResource("assets/tower.jpg")).getImage();
        devilImg = new ImageIcon(getClass().getResource("assets/devil.jpg")).getImage();

        donePrep = false;
        fpsTimer = new Timer(1000 / 10, this); // 10 fps
        fpsTimer.start();

    }

    public void prep() {
        System.out.println("6secs");
        donePrep = true;
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
        System.out.println("10fps");

        if (donePrep) {
            donePrep = false;
            System.out.println("hellu akoa gi stop");
            roundPrepTimer.stop();
            fpsTimer.start();
        } else {
            fpsTimer.stop();
            roundPrepTimer = new Timer(6000, this);
            prep();
        }

        move();
        repaint();
    }
}
