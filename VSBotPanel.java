import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VSBotPanel extends JPanel implements ActionListener {

    int width = 1200, height = 800, pixel = 20;

    PreparationPhase preparationPhaseObject;
    BattlePhase battlePhaseObject;

    DefaultFont defaultFont;

    Timer drawFPS;

    int fps = 24, timeCountHolder;
    int roundPreparingTimer = 8000;

    Boolean preparing, battling;

    Point mouseClickCoordinatesPoint;

    VSBotPanel() {
        setBounds(0, 0, width, height);
        setFocusable(true);
        setBackground(Color.BLACK);

        preparationPhaseObject = new PreparationPhase();
        battlePhaseObject = new BattlePhase();
        defaultFont = new DefaultFont();

        timeCountHolder = 0;
        preparing = true;
        battling = false;

        drawFPS = new Timer(1000 / fps, this); // 1000ms/24 means 24 frame per sec
        drawFPS.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        // HEADER IN ALL PHASE
        g.setColor(Color.WHITE);
        g.setFont(defaultFont.getBoldFont());
        if (battling)
            g.drawString("BATTLE PHASE", 20, 40);

        // PREPARATION
        if (preparing) {
            g.drawString("PREPARATION PHASE", 20, 40);
            preparationPhaseObject.draw(g);
            g.setColor(Color.RED);
            g.setFont(defaultFont.getLightFont());
            g.drawString("Preparation Phase ENDS in " + (roundPreparingTimer - timeCountHolder) / 1000, 20, 100);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (preparing) {
            timeCountHolder += fps;
        }
        repaint();
    }
}