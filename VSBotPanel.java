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
    int roundPreparingTimer = 3000;
    int roundIncrement;

    Boolean drawing, preparing, battling;

    Point mouseClickCoordinatesPoint;

    Boolean botPicking;

    int playerCurrentHp, botCurrentHp;

    Boolean isEndless;

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

        isEndless = false;

        preparationPhaseObject = new PreparationPhase(this);
        drawPhaseObject = new DrawPhase(this);
        battlePhaseObject = new BattlePhase();
        defaultFont = new DefaultFont();
        cardsEffectsObject = new CardsEffects(this);

        drawFPS = new Timer(1000 / fps, this); // 1000ms/24 means 24 frame per sec
        // drawFPS.start();

    }

    // METHOD PARA I SET ANG ENDLESS MODE,
    // SO NEED HILBANTAN USAB ANG HP FOR VISUAL
    public void setEndless(Boolean isEndless) {
        this.isEndless = isEndless;
        if (isEndless) {
            isEndlessSetUp();
            preparationPhaseObject.botCurrentHp = botCurrentHp;
        } else {
            botCurrentHp = 200;
            preparationPhaseObject.botCurrentHp = botCurrentHp;
        }
    }

    public void isEndlessSetUp() {
        botCurrentHp = 100000;
        // D MN GURO KAABOT UG 100K DMG,
        // DRAFT LIMITER PRA GAGMAY RA MADUNGAG IF ELSE SA SULOD ANING MGA PHASE
        preparationPhaseObject.starCard.setEnergy(0); // no energy cost if endless and no energy/5
        preparationPhaseObject.towerCard.setEnergy(0);
        preparationPhaseObject.devilCard.setEnergy(0);
        preparationPhaseObject.setEndless(true);
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
            preparationPhaseObject.botHolderCard.setImg(preparationPhaseObject.botCardChoiceImage);
            preparationPhaseObject.botHolderRune.setImage(preparationPhaseObject.botRuneChoiceImage);
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
            // PREP FOR EFFECTS NA + MORE ENERGY
            if (preparationPhaseObject.playerEnergyCount > 5) {
                preparationPhaseObject.playerEnergyCount = 5;
            }
        }
        if (preparationPhaseObject.botEnergyCount < 5) {
            preparationPhaseObject.botEnergyCount++;
            if (preparationPhaseObject.botEnergyCount > 5) {
                preparationPhaseObject.botEnergyCount = 5;
            }
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
        // BANTUG MAG THROW EXCEPTION NAPUD KAY DLI MA MINUSAN ANG CARDCOUNT
        if (preparationPhaseObject.playerCardIndex != 9) {
            drawPhaseObject.playerCardCount -= 1;
        }
        if (preparationPhaseObject.botCardIndex != 9) {
            drawPhaseObject.botCardCount -= 1;
        }

        // NEED TO RESET IN PREPARATION PHASE
        preparationPhaseObject.isActiveCard = false;
        preparationPhaseObject.isActiveRune = false;
        preparationPhaseObject.playerCurrentHp = playerCurrentHp;
        preparationPhaseObject.botCurrentHp = botCurrentHp;
        // RESET ACTIVE CARDS & RUNES , HOLDER CARDS & RUNES
        // preparationPhaseObject.holderCard.setName("holderCard");
        preparationPhaseObject.activeCard.setName("activeCard");
        // preparationPhaseObject.holderRune.setName("holderRune");
        preparationPhaseObject.activeRune.setName("activeRune");
        // ACTIVE RA DAAY ANG NABILIN NA WA NA RESET NGA GA MATTER
        // SINCE SI BOT, DTSO PILI KADA AFTER DRAW SO OVERRIDE DTSO ANG NABILIN NGA DATA
        preparationPhaseObject.botHolderCard.setImg(preparationPhaseObject.cardHolderImage);
        preparationPhaseObject.botHolderRune.setImage(preparationPhaseObject.runeHolderImage);
        // RESET PUD ANG BOTH RUNES XY FOR PREP IN BATTLE
        preparationPhaseObject.holderRune.setX(preparationPhaseObject.holderRuneX);
        preparationPhaseObject.holderRune.setY(preparationPhaseObject.holderRuneY + 11);
        // GIHILOT NLNG NING 11 KY WA KO KABALO ASA ANG REASON NGANO MO ISDOG UG 11PIXEL
        // ANG RUNES AFTER ONLY SA 1SR ROUND
        // KA BOT
        preparationPhaseObject.botHolderRune.setX(preparationPhaseObject.holderRuneX);
        preparationPhaseObject.botHolderRune.setY(preparationPhaseObject.holderRuneY
                - (preparationPhaseObject.cardHeight + (preparationPhaseObject.margin * 2)) - 11);
        preparationPhaseObject.playerCardIndex = 9;
        preparationPhaseObject.botCardIndex = 9;
        // DIRIA DTSO E KALTAS ANG REYAL ENERGY NI BOT TO SEE IT VISUALLY AFTER PREP,
        // KAY IF NAA SA SULOD SA PREP FOR LOOP, D NA KAPILI ANG BOT DEDUCT DTSO
        preparationPhaseObject.botEnergyCount -= preparationPhaseObject.toBeDeductedBotEnergy;
        preparationPhaseObject.toBeDeductedBotEnergy = 0;
        preparationPhaseObject.botCardChoiceImage = preparationPhaseObject.cardHolderImage;
        preparationPhaseObject.botRuneChoiceImage = preparationPhaseObject.runeHolderImage;

        // NEED TO RESET IN BATTLE PHASE
        battlePhaseObject.incrementSpeed = 10;
        battlePhaseObject.stopAfterBack = false;
        battlePhaseObject.stopAfterBackTimer = 250;
        battlePhaseObject.animationRuneCollideDone = false;
        battlePhaseObject.winnerName = "tie";
        battlePhaseObject.secondCounter = 1000; // 1sec
        battlePhaseObject.winnerCardEffectDone = false;
        battlePhaseObject.loserCardEffectDone = false;
        battlePhaseObject.deleteUsedCardAnimationDuration = 1000;
        battlePhaseObject.runeWinner = null;

        // NEED TO RESET IN CARD EFFECT DRAW
        cardsEffectsObject.winnerName = "tie";
        cardsEffectsObject.winnerCardDuration = 1000;
        cardsEffectsObject.loserCardDuration = 1000;

        // --- THESE ARE WHAT IVE ENCOUNTERED AFTER PUTTING THE GAMELOOP RESET I THINK
        // ONLY MATTERED, SO THESE ARE THE LOGIC BUGS: ALL FIX?? --- YESSIR

        // ANG ACTIVE CARD NAME REMAINS LAST CHOSEN - fixed
        // MALAPAS UG NIGGA ANG HP - fixed
        // NULL ANG HOLDERS NI BOT AFTER ROUND 1 - fixed
        // UYY MOBALIK 200 HPS SA DUHA, MAGMINUS SA ROUND PERO RESET - fixed

        // -- GA INCREMENT UG 1 ANG PLAYER ENERGY PAG CANCEL SA 1ST ACTIVE CARD,
        // NO, MAWALA ANG 1 ENERGY PAGKA SWITCH, D LANG MABANTAYAN IF FIRST ROUND KY 3
        // ENERGY MAN AND SAKTO RA SA CARDS -- [COZ WLAY PANSALO SA FIRST IF, PERO NA
        // CATCH KO NAMANI DATI] - fixed

        // PATI ENERGY, SIGURO TUNGOD MAG NEW PREPHASE OBJ KADA VSPANEL? - fixed
        // KULANG, DAPAT MINUS ANG CARD GIGAMIT,
        // (this is a whole method ky need ni animation) - fixed

        // -- AND BALIK SA OG POSITION ANG RUNES NGA NAG COLLIDE,
        // D NA MO COLLIDE ANG NEXT ROUND COZ OF IT -- - fixed

        // COLLIDE AFTER 1ST ROUND SEEMS WRONG - fixed
        // BOT ENERGY PROPER MINUS - fixed
        // VISUAL FUNCTION FOR TUNAW/USED CARD DRAW - fixed
    }
}