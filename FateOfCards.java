import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FateOfCards extends JPanel implements ActionListener {

    int width = 700;
    int height = 900;
    int pixel = 20;
    Timer fpsTimer;
    Timer roundPrepTimer;
    Timer roundBattleTimer;
    private Image starImg;
    private Image towerImg;
    private Image devilImg;
    int timeCountHolder;
    boolean prepAgain;

    FateOfCards() {
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        setBackground(Color.BLACK);

        starImg = new ImageIcon(getClass().getResource("assets/star.jpg")).getImage();
        towerImg = new ImageIcon(getClass().getResource("assets/tower.jpg")).getImage();
        devilImg = new ImageIcon(getClass().getResource("assets/devil.jpg")).getImage();

        timeCountHolder = 0;
        prepAgain = true;
        fpsTimer = new Timer(100, this); // 10 fps (1000ms / 100ms = 10 frame per 1000ms)
        fpsTimer.start();

    }

    public void prep() {
        System.out.println(timeCountHolder);
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
        if (timeCountHolder == 6000) {
            timeCountHolder = 0;
            prepAgain = false;
            System.out.println("reset");
            move();
            repaint();
        } else {
            // 6secs magsulod siyas prep until time is up then back to original
            if (prepAgain) {
                timeCountHolder += 100; // pra magcount ni pa 6secs, 100ms x 60 = 6000ms = 6secs
            }
            prep();
        }
    }

    // SEGEH MESSY CODEZ NA NI, AS LONG AS MA BUHAT NAKO IN MY WAY
}
