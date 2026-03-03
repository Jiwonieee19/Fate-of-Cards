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

    public void bridgeToBattle() {
        timeCountHolder = 0;
        preparingPhaseBool = false;
        if (prepPhaseObj.starCard.getX() < prepPhaseObj.starCardX) {
            System.out.println("star ang active"); // it works
        }
        System.out.println("reset");
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Fate of Cards", width / 2 - 70, 20);

        if (preparingPhaseBool) {
            prepPhaseObj.draw(g); // TAE PEDE RA DAAY NIII MOGANA DAAY NI HAHAAHA
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Preparation ends in " + (8000 - timeCountHolder) / 1000 + " seconds", width / 2 - 160, 400);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (timeCountHolder == 8000) {
            bridgeToBattle();
            // move();
        } else {
            // 6secs magsulod siyas prep until time is up then back to original
            if (preparingPhaseBool) {
                timeCountHolder += 100; // pra magcount ni pa 8secs, 100ms x 80 = 8000ms = 8secs, after 8secs, done na
                                        // preparation
            }
            // prep();
        }
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        mouseClickedCoordinates = e.getPoint();
        prepPhaseObj.PreparationMouseClick(mouseClickedCoordinates); // HAHAHAH BASIC HIDING SKILLS

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
