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
        playerOnHand = new MainCards[5];
        botOnHand = new MainCards[5];

        picker = new Random();

        deckCardVelocityX = deckCards.getX();
        deckCardVelocityY = deckCards.getY();

        drawFirstCount = 3 * 5;
        drawCount = 1;

    }

    public void startingDrawBoth() {
        // MEANS PLAYER AND BOT PICK 2 INITIAL CARDS
        playerOnHand[0] = drawPossibility[picker.nextInt(0, 3)];
        playerOnHand[1] = drawPossibility[picker.nextInt(0, 3)];
        botOnHand[0] = drawPossibility[picker.nextInt(0, 3)];
        botOnHand[1] = drawPossibility[picker.nextInt(0, 3)];
    }

    public void drawCardVelocityChanger() {
        // MATCH MY FREAK (INSTEAD OF PLAIN DRAW, ANIMATE USING VELOCITY AND SCALE ONLY)
        if (deckCardVelocityX < 400) {
            deckCardVelocityX -= 12;
            deckCardVelocityY += 5;
        } else {
            deckCardVelocityX -= 12;

        }
    }

    public void drawingCardsAnimation(Graphics g) {
        // MALI ANG APPROACH NGA FOR LOOP, KAY E RUN TANAN BEFORE MO MOVE NEXT FPS

        // drawTimes *= 5; // HAHAHAH MARGIN INSIDE LOOP
        // for (int i = 0; i < drawTimes; i++) {
        // // EZ MARGIN SA VISUAL, 5 FRAMES BEFORE MO SUNOD ANG DRAWCARD
        // if (drawTimes % 5 == 0) {
        g.drawImage(devilCard.getImg(), deckCardVelocityX, deckCardVelocityY,
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
        cardsObject.draw(g); // HAHAHAH kuha ra in.ani, naka separate bitaw tong String nga phase chuchu
        startingDrawBoth();
        drawingCardsAnimation(g);
    }
}
