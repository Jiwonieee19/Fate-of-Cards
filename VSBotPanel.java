import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class VSBotPanel extends JPanel implements ActionListener, MouseListener {

    int width = 1200, height = 800, pixel = 20;

    PreparationPhase preparationPhaseObject;
    BattlePhase battlePhaseObject;

    DefaultFont defaultFont;

    Timer drawFPS;

    int fps = 24, timeCountHolder;
    int roundPreparingTimer = 8000;

    Boolean initialDraw, preparing, battling;

    Point mouseClickCoordinatesPoint;

    VSBotPanel() {
        setBounds(0, 0, width, height);
        setFocusable(true);
        setBackground(Color.BLACK);
        addMouseListener(this);

        preparationPhaseObject = new PreparationPhase();
        battlePhaseObject = new BattlePhase();
        defaultFont = new DefaultFont();

        timeCountHolder = 0;
        initialDraw = true;
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
        g.setFont(defaultFont.getBoldFontCustomSize(25));
        if (battling)
            g.drawString("BATTLE PHASE", 20, 40);

        // PREPARATION
        if (preparing) {
            g.drawString("PREPARATION PHASE", 20, 40);
            preparationPhaseObject.draw(g);
            g.setColor(Color.RED);
            g.setFont(defaultFont.getLightFontCustomSize(20));
            g.drawString("Preparation Phase ENDS in " + (roundPreparingTimer - timeCountHolder) / 1000, 20, 70);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (preparing) {
            timeCountHolder += fps;
        }
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (preparing) {
            mouseClickCoordinatesPoint = e.getPoint();
            preparationPhaseObject.PreparationMouseClick(mouseClickCoordinatesPoint);
            // PARA NAA NA SA DESIGNATED CLASS ANG PAG HANDLE, DRI SA PANEL, IGO RA PASA
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
}