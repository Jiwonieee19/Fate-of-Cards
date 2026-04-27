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

    DrawPhase drawPhaseObject;
    PreparationPhase preparationPhaseObject;
    BattlePhase battlePhaseObject;

    CardsEffects cardsEffectsObject;

    DefaultFont defaultFont;

    Timer drawFPS;

    int fps = 24, timeCountHolder;
    int roundPreparingTimer = 5000;
    int roundIncrement;

    Boolean drawing, preparing, battling;

    Point mouseClickCoordinatesPoint;

    Boolean botPicking;

    VSBotPanel() {
        setBounds(0, 0, width, height);
        setFocusable(true);
        setBackground(Color.BLACK);
        addMouseListener(this);

        drawPhaseObject = new DrawPhase();
        preparationPhaseObject = new PreparationPhase();
        battlePhaseObject = new BattlePhase();
        defaultFont = new DefaultFont();
        cardsEffectsObject = new CardsEffects();

        timeCountHolder = 0;
        drawing = true;
        preparing = false;
        battling = false;
        botPicking = true;

        roundIncrement = 1;

        drawFPS = new Timer(1000 / fps, this); // 1000ms/24 means 24 frame per sec
        // drawFPS.start();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        // HEADER IN ALL PHASE
        g.setColor(Color.WHITE);
        g.setFont(defaultFont.getBoldFontCustomSize(40));
        g.drawString("Round " + roundIncrement, (width / 2) - (132 / 2), 50);

        g.setFont(defaultFont.getBoldFontCustomSize(25));

        // FIRST DRAW
        if (drawing) {
            g.drawString("DRAW PHASE", 20, 40);
            drawPhaseObject.draw(g);
        }

        // PREPARATION
        if (preparing) {
            g.drawString("PREPARATION PHASE", 20, 40);
            // PARA AFTER DYNAMIC DRAW G DURING DRAWPHASE, MA KEEP DIRIA DURING PREPHASE
            preparationPhaseObject.draw(g, drawPhaseObject.playerOnHand, drawPhaseObject.botOnHand,
                    drawPhaseObject.playerCardCount,
                    drawPhaseObject.botCardCount, false);
            if (botPicking) {
                // PASS THE BOT ON HAND HERE TOO TO PICK A PLAY
                preparationPhaseObject.BotCardChoice(drawPhaseObject.botOnHand, drawPhaseObject.botCardCount);
                preparationPhaseObject.BotRuneChoice();
                // TAMA AKO HINALA, IF WALAY BOOLEAN, THEN REPAINT = NEW CHOICE,
                // WHICH IS STILL GOOD, LOOKS LIKE A LOT MACHINE THEN END OF TIME MA BUNOT NA
                // NIYA
                botPicking = true;
                // e true rani balik after result,
                // pra dli every repaint mag change ang choice ni bot
            }
            g.setColor(Color.RED);
            g.setFont(defaultFont.getLightFontCustomSize(20));
            g.drawString("Preparation Phase ENDS in " + (roundPreparingTimer - timeCountHolder) / 1000, 20, 70);
        }

        // BATTLE
        if (battling) {
            g.setColor(Color.WHITE);
            g.drawString("BATTLE PHASE", 20, 40);
            // PARA NEXT ROUND ANG OG NGA ENERGY, GKAN NA SA DEDUCTED ENERGY
            preparationPhaseObject.beforeDeductionPlayerEnergy = preparationPhaseObject.playerEnergyCount;
            battlePhaseObject.passingObjects(preparationPhaseObject, drawPhaseObject, cardsEffectsObject);
            battlePhaseObject.draw(g);
            // COLLIDE SA BEFORE RESULT, MAO NAA SA BABA BATTLE RUNE
            battlePhaseObject.collisionRunes(
                    preparationPhaseObject.holderRune,
                    preparationPhaseObject.botHolderRune);
            battlePhaseObject.battleRunes(
                    preparationPhaseObject.activeRune,
                    preparationPhaseObject.botHolderRune);
            // PASS SD KA CARD EFFECTS
            cardsEffectsObject.passingObjects(preparationPhaseObject, battlePhaseObject);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // if (drawing) {
        // // timeCountHolder += fps;
        // System.out.println("CURRENT COUNT: " + timeCountHolder);
        // }
        if (drawing && drawPhaseObject.drawCount == 0) {
            drawing = false;
            preparing = true;
            System.out.println("MANA DRAW");
        }
        if (preparing) {
            timeCountHolder += fps;
        }
        if (timeCountHolder >= roundPreparingTimer) {
            if (preparing)
                System.out.println("MANA PREPARATION");
            preparing = false;
            battling = true;
        }
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (preparing) {
            mouseClickCoordinatesPoint = e.getPoint();
            preparationPhaseObject.PreparationMouseClick(mouseClickCoordinatesPoint,
                    drawPhaseObject.playerOnHand,
                    drawPhaseObject.playerCardCount);
            // PARA NAA NA SA DESIGNATED CLASS ANG PAG HANDLE, DRI SA PANEL, IGO RA PASA

            // separate class nalang ang active runes yati ky d mani array,
            // para dli na mo taas samot parameter anang isa
            preparationPhaseObject.PreparationMouseClickRunes(mouseClickCoordinatesPoint);

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