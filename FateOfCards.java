import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FateOfCards extends JPanel implements ActionListener, MouseListener {

    int width = 700;
    int height = 900;
    int pixel = 20;

    Timer gameLoop;
    Timer roundPrepTimer;
    Timer roundBattleTimer;

    PreparationPhase prepPhaseObj;

    int timeCountHolder;
    boolean preparingPhaseBool;

    Point mouseClickedCoordinates;

    FateOfCards() {
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        setBackground(Color.BLACK);
        addMouseListener(this);

        prepPhaseObj = new PreparationPhase();

        timeCountHolder = 0;
        preparingPhaseBool = true;
        gameLoop = new Timer(100, this); // 10 fps (1000ms / 100ms = 10 frame per 1000ms)
        gameLoop.start();

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
        // g.drawImage(starCard.getImg(), starCard.getX(), starCard.getY(),
        // starCard.getWidth(), starCard.getHeight(),
        // null);
        if (preparingPhaseBool) {
            prepPhaseObj.draw(g); // TAE PEDE RA DAAY NIII MOGANA DAAY NI HAHAAHA
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("10fps");
        if (timeCountHolder == 6000) {
            timeCountHolder = 0;
            preparingPhaseBool = false;
            System.out.println("reset");
            move();
            repaint();
        } else {
            // 6secs magsulod siyas prep until time is up then back to original
            if (preparingPhaseBool) {
                timeCountHolder += 100; // pra magcount ni pa 6secs, 100ms x 60 = 6000ms = 6secs, after 6secs, done na
                                        // preparation
            }
            prep();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mouseClickedCoordinates = e.getPoint();
        if (mouseClickedCoordinates.y > prepPhaseObj.starCard.getY()) {
            System.out.println("try nakasulod ba greater than y"); // works perfectly
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    // SEGEH MESSY CODEZ NA NI, AS LONG AS MA BUHAT NAKO IN MY WAY
}
