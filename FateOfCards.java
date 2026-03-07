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
    BattlePhase batPhaseObj;

    int timeCountHolder;
    boolean startGameBool;
    boolean preparingPhaseBool;
    boolean battlingPhaseBool;

    Point mouseClickedCoordinates;

    MainCards a, b;

    FateOfCards() {
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        setBackground(Color.BLACK);
        addMouseListener(this);

        prepPhaseObj = new PreparationPhase();
        batPhaseObj = new BattlePhase();

        timeCountHolder = 0;
        startGameBool = false;
        preparingPhaseBool = false;
        battlingPhaseBool = false;
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
        battlingPhaseBool = true;

        b = prepPhaseObj.BotChoice(); // pede rasad ani, pra pick daan for bot

        if (prepPhaseObj.starCard.getX() < prepPhaseObj.starCardX) { // if card nga na move ang x since active siya
            System.out.println("star ang active"); // it works
            a = prepPhaseObj.starCard;
            // batPhaseObj.BattleCards(prepPhaseObj.starCard, null); // pasok sa "star
            // yarn", checker ra ni
            // batPhaseObj.BattleCards(prepPhaseObj.starCard, prepPhaseObj.devilCard);
            // devil wins (WORKING: NEED NLNG SHUFFLER SA BOT)
            batPhaseObj.BattleCards(a, b);

        } else if (prepPhaseObj.towerCard.getX() < prepPhaseObj.towerCardX) {
            System.out.println("tower ang active");
            a = prepPhaseObj.towerCard;
            batPhaseObj.BattleCards(a, b);
        } else if (prepPhaseObj.devilCard.getX() < prepPhaseObj.devilCardX) {
            System.out.println("devil ang active");
            a = prepPhaseObj.devilCard;
            batPhaseObj.BattleCards(a, b);

        } else {
            System.out.println("ERROR, WALAY CARD GI CLICK");
        }
        System.out.println("reset");
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {

        // HEADER IN ALL PHASE
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Fate of Cards", width / 2 - 60, 20);

        // START
        if (!startGameBool) {
            g.drawString("Press/Hold your mouse click to START", width / 2 - 180, height / 2);
        }

        // PREPARATION
        if (preparingPhaseBool) {
            prepPhaseObj.draw(g); // TAE PEDE RA DAAY NIII MOGANA DAAY NI HAHAAHA
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Preparation ends in " + (8000 - timeCountHolder) / 1000 + " seconds", 20, 200);

            // BATTLE
        } else if (battlingPhaseBool) {
            batPhaseObj.draw(g, a, b);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Card Battle", 20, 200);

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
                timeCountHolder += 100; // pra magcount ni pa 8secs, 100ms x 80 = 8000ms = 8secs, done na preparation
            }
            // prep();
        }
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (preparingPhaseBool) {
            mouseClickedCoordinates = e.getPoint();
            prepPhaseObj.PreparationMouseClick(mouseClickedCoordinates); // HAHAHAH BASIC HIDING SKILLS
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!startGameBool) {
            startGameBool = true;
            preparingPhaseBool = true;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    // SEGEH MESSY CODEZ NA NI, AS LONG AS MA BUHAT NAKO IN MY WAY
}
