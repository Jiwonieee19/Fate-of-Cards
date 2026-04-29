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

    int playerCurrentHp, botCurrentHp;

    VSBotPanel() {
        setBounds(0, 0, width, height);
        setFocusable(true);
        setBackground(Color.BLACK);
        addMouseListener(this);

        timeCountHolder = 0;
        drawing = true;
        preparing = false;
        battling = false;
        botPicking = true;

        roundIncrement = 1;

        playerCurrentHp = 200;
        botCurrentHp = 200;

        preparationPhaseObject = new PreparationPhase(this);
        drawPhaseObject = new DrawPhase(this);
        battlePhaseObject = new BattlePhase();
        defaultFont = new DefaultFont();
        cardsEffectsObject = new CardsEffects(this);

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
            cardsEffectsObject.passingObjects(preparationPhaseObject, battlePhaseObject, this);
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

    public void roundContinue() {

        // preparationPhaseObject.botCurrentHp = cardsEffectsObje

        // INCREMENT THAT SHOULD HAPPEN
        roundIncrement++;
        if (preparationPhaseObject.playerEnergyCount < 5) {
            preparationPhaseObject.playerEnergyCount++;
        }
        if (preparationPhaseObject.botEnergyCount < 5) {
            preparationPhaseObject.botEnergyCount++;
        }

        // NEED TO RESET HERE IN VSPANEL
        timeCountHolder = 0;
        drawing = true;
        preparing = false;
        battling = false;
        botPicking = true;

        // NEED TO RESET IN DRAW PHASE
        drawPhaseObject.drawCount = 2; // para 1draw each per round
        drawPhaseObject.playerDraw = true;
        drawPhaseObject.botDraw = false;

        // NEED TO RESET IN PREPARATION PHASE
        preparationPhaseObject.isActiveCard = false;
        preparationPhaseObject.isActiveRune = false;
        preparationPhaseObject.playerCurrentHp = playerCurrentHp;
        preparationPhaseObject.botCurrentHp = botCurrentHp;

        // NEED TO RESET IN BATTLE PHASE
        battlePhaseObject.incrementSpeed = 10;
        battlePhaseObject.stopAfterBack = false;
        battlePhaseObject.stopAfterBackTimer = 250;
        battlePhaseObject.animationRuneCollideDone = false;
        battlePhaseObject.winnerName = "tie";
        battlePhaseObject.secondCounter = 1000; // 1sec
        battlePhaseObject.winnerCardEffectDone = false;
        battlePhaseObject.loserCardEffectDone = false;

        // NEED TO RESET IN CARD EFFECT DRAW
        cardsEffectsObject.winnerName = "tie";
        cardsEffectsObject.winnerCardDuration = 1000;
        cardsEffectsObject.loserCardDuration = 1000;

        // UYY MOBALIK 200 HPS SA DUHA, MAGMINUS SA ROUND PERO RESET
        // GA INCREMENT UG 1 ANG PLAYER ENERGY PAG CANCEL SA 1ST ACTIVE CARD
        // PATI ENERGY, SIGURO TUNGOD MAG NEW PREPHASE OBJ KADA VSPANEL?
        // KULANG, DAPAT MINUS ANG CARD GIGAMIT
        // AND BALIK SA OG POSITION ANG RUNES NGA NAG COLLIDE,
        // D NA MO COLLIDE ANG NEXT ROUND COZ OF IT
    }
}