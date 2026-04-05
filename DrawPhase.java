import java.awt.*;
import java.util.Random;

public class DrawPhase {

    PreparationPhase cardsObject;

    MainCards starCard;
    MainCards towerCard;
    MainCards devilCard;

    MainCards deckCards;

    MainRunes rockRune;
    MainRunes paperRune;
    MainRunes scissorsRune;

    MainCards[] drawPossibility;
    MainCards[] playerOnHand;
    MainCards[] botOnHand;
    // MainCards[] drawPossibility = {starCard, towerCard, devilCard};

    Random picker;

    int deckCardVelocityX;
    int deckCardVelocityY;
    int drawFirstCount;
    int drawCount;

    Boolean playerDraw;
    Boolean botDraw;

    int playerCardCount;
    int botCardCount;

    DrawPhase() {

        // MAO NI NAMING KAY DDTO MAN SA PREPPHASE KUHAON
        cardsObject = new PreparationPhase();

        // HAHAHAHAAH PEDE DAAY NI
        starCard = cardsObject.starCard;
        towerCard = cardsObject.towerCard;
        devilCard = cardsObject.devilCard;
        deckCards = cardsObject.deckCards;

        // FOR G.DRAWINGS RA NI
        rockRune = cardsObject.rockRune;
        paperRune = cardsObject.paperRune;
        scissorsRune = cardsObject.scissorsRune;

        // ARRAY NI CARDS HERE
        // drawPossibility = {starCard, towerCard, devilCard};
        // constant can only be used during initialized
        // drawPossibility = new MainCards[3];
        // drawPossibility[0] = starCard; // HOOO tama dghn manig ways ang array diay
        // dara to mas tipid and flexibile
        drawPossibility = new MainCards[] { starCard, towerCard, devilCard };
        playerOnHand = new MainCards[3];
        botOnHand = new MainCards[3];

        picker = new Random();

        deckCardVelocityX = deckCards.getX();
        deckCardVelocityY = deckCards.getY();

        // PANG TOGGLE NI KUNG KINSA NA MO DRAW, first permi si player
        playerDraw = true;
        botDraw = false;

        // initial kay 2 man, so 2 cards sa 2 players = 4 ka cards ma draw
        drawCount = 4;
        // done draw > 5, no error
    }

    // public void startingDrawBoth() {
    // // MEANS PLAYER AND BOT PICK 2 INITIAL CARDS
    // playerOnHand[0] = drawPossibility[picker.nextInt(0, 3)];
    // // playerOnHand[1] = drawPossibility[picker.nextInt(0, 3)];
    // botOnHand[0] = drawPossibility[picker.nextInt(0, 3)];
    // // botOnHand[1] = drawPossibility[picker.nextInt(0, 3)];
    // }

    // MUCH BETTER AND FLEXIBLE NI, PARA LATER FOR CARD EFFECTS NGA BLOCK DRAW
    public void playerDrawCard() {
        for (int i = 0; i < 3; i++) {
            // MUCH FLEXIBLE PARA MA RECORD RA DRAW IF DLI PA MAX ANG ONHAND
            if (playerOnHand[i] == null) {
                playerOnHand[i] = drawPossibility[picker.nextInt(0, 3)];
                System.out.println("NA DRAW NI PLAYER: " + playerOnHand[i].getName());
                break;
            }
        }
    }

    public void botDrawCard() {
        for (int i = 0; i < 3; i++) {
            if (botOnHand[i] == null) {
                botOnHand[i] = drawPossibility[picker.nextInt(0, 3)];
                System.out.println("NA DRAW NI BOT: " + botOnHand[i].getName());
                break;
            }
        }
    }

    public void drawCardVelocityChanger() {
        // MATCH MY FREAK (INSTEAD OF PLAIN DRAW, ANIMATE USING VELOCITY AND SCALE ONLY)
        if (deckCardVelocityX > 200 && playerDraw) {
            deckCardVelocityY += 12;
            if (deckCardVelocityY > cardsObject.cardY) {
                // RESTART ANIMATION FOR NEXT DRAW
                deckCardVelocityX = cardsObject.deckCards.getX();
                deckCardVelocityY = cardsObject.deckCards.getY();
                playerDraw = false;
                botDraw = true;
                // diff between y of deck and y of playercard to use for bot limit animation
                int diff = (cardsObject.deckCards.getY() - cardsObject.devilCard.getY()); // 225
                System.out.println("BASIC RESTART DRAW ANIMATION " + diff);

                // AFTER MA REACH OR MAKA ISAG DRAW, THEN DDTO PA MAG MINUS
                drawCount -= 1;
                // para dli mag error pag nalapas
                if (playerCardCount < 3) {
                    playerCardCount++;
                }
                playerDrawCard();
            }
            // KANG BOT NI, KAY - ANG Y MEANS PASAKA ANIMATION
        } else if (deckCardVelocityX > 200 && botDraw) {
            deckCardVelocityY -= 12;
            if (deckCardVelocityY < (cardsObject.deckCards.getY() - 225)) {
                // RESTART ANIMATION FOR NEXT DRAW
                deckCardVelocityX = cardsObject.deckCards.getX();
                deckCardVelocityY = cardsObject.deckCards.getY();
                playerDraw = true;
                botDraw = false;
                drawCount -= 1;
                if (botCardCount < 3) {
                    botCardCount++;
                }
                botDrawCard();
            }
        } else {
            deckCardVelocityX += 12;
        }
    }

    public void drawingCardsAnimation(Graphics g) {
        // MALI ANG APPROACH NGA FOR LOOP, KAY E RUN TANAN BEFORE MO MOVE NEXT FPS

        // drawTimes *= 5; // HAHAHAH MARGIN INSIDE LOOP
        // for (int i = 0; i < drawTimes; i++) {
        // // EZ MARGIN SA VISUAL, 5 FRAMES BEFORE MO SUNOD ANG DRAWCARD
        // if (drawTimes % 5 == 0) {
        g.drawImage(deckCards.getImg(), deckCardVelocityX, deckCardVelocityY,
                deckCards.getWidth(),
                deckCards.getHeight(),
                null);
        drawCardVelocityChanger(); // CHANGE EVERY FRAME
        // // YW GALING, IM AMAZED RYAN HAHAHAHA ISULOD RANI DRI
        // }
        // }

        // drawCardVelocityChanger(); // CHANGE EVERY FRAME
        // PEDE RA DAAY DRI, MAS COMFY PARA IF NEED INT, PASA DTSO
        // (DATI NAA SA ACTIONPERFORMED NI)
        // YATI SA ACTIONPERFORMED DYUD DAAY PARA MA CONTROL PERFRAME, if statement lang
    }

    public void draw(Graphics g) {
        // KANI ANG PAG DRAW SA G DURING DRAWPHASE,
        // SA VSPANEL MAO NATO MAG STAY SA PREPPHASE NGA DRAW
        cardsObject.draw(g, playerOnHand, botOnHand, playerCardCount, botCardCount); // HAHAHAH kuha ra in.ani, naka
        // separate bitaw tong String nga phase chuchu
        // startingDrawBoth();
        drawingCardsAnimation(g);
    }
}
