import java.awt.*;
import java.util.Random;

import javax.swing.ImageIcon;

public class PreparationPhase {

        MainCards starCard;
        MainCards towerCard;
        MainCards devilCard;

        int cardWidth = 735 / 8; // 92
        int cardHeight = 1208 / 8; // 151
        int margin = 50;
        int cardY = 550;
        // int starCardX = margin;
        // int towerCardX = margin * 2;
        // int devilCardX = margin * 3;

        Random randomizer;

        // REFACTORS START HERE
        MainRunes rockRune;
        MainRunes paperRune;
        MainRunes scissorsRune;

        // MATHS MATHS MATHS
        int runesWH = 120;
        int runesY = 630;
        int scissorsRuneY = 630 - ((runesWH + 90) / 2);
        int rockRuneX = 900;
        int paperRuneX = rockRuneX + runesWH;
        int scissorsRuneX = rockRuneX + (runesWH / 2);

        MainCards deckCards;

        DefaultFont defaultFont;

        Image activeCardImage;
        MainCards activeCard;
        Boolean isActiveCard;

        Image activeRuneImage;
        MainRunes activeRune;
        Boolean isActiveRune;

        MainRunes[] runesArray;

        int playerCurrentHp, botCurrentHp;

        Image energyImage;
        int playerEnergyCount, botEnergyCount;

        Image cardHolderImage, runeHolderImage;
        MainCards holderCard;
        MainRunes holderRune;

        // MATHS MATHS MATHS
        int holderCardX = (1200 / 2) - (cardWidth + (margin / 2));
        int holderCardY = (800 / 2) + margin;
        // i forgot naa mn daay extra pixel pa nang pagbuhat sa runes, so /2 to /4
        int holderRuneX = (1200 / 2) + (margin / 4);
        int holderRuneY = (800 / 2) + margin + ((cardHeight - runesWH) / 2);

        MainCards botHolderCard;
        MainRunes botHolderRune;

        PreparationPhase() {

                // CREATING THE IMAGES AND CARDS HERE COZ SEPARATING COST A LONGER CALLING
                // e.g. g.drawImage(instantiateCards.starCard.getImage(), , , , )
                // i dont want that

                Image starImg = new ImageIcon(getClass().getResource("assets/cards/star.png")).getImage();
                Image towerImg = new ImageIcon(getClass().getResource("assets/cards/tower.png")).getImage();
                Image devilImg = new ImageIcon(getClass().getResource("assets/cards/devil.png")).getImage();

                // TANAN CARS, SAME X, MAG DIFFER NA DIDTO SA ARRAY NGA ONHAND-CARDS NI PLAYER
                starCard = new MainCards("star", margin, cardY, cardWidth, cardHeight, starImg);
                towerCard = new MainCards("tower", margin, cardY, cardWidth, cardHeight, towerImg);
                devilCard = new MainCards("devil", margin, cardY, cardWidth, cardHeight, devilImg);

                randomizer = new Random();

                // REFACTOR STARTS HERE
                Image rockImage = new ImageIcon(getClass().getResource("assets/runes/rockRuneWhite.png")).getImage();
                Image paperImage = new ImageIcon(getClass().getResource("assets/runes/paperRuneWhite.png"))
                                .getImage();
                Image scissorsImage = new ImageIcon(getClass().getResource("assets/runes/scissorsRuneWhite.png"))
                                .getImage();

                rockRune = new MainRunes("rock", rockRuneX, runesY, runesWH, runesWH, rockImage);
                paperRune = new MainRunes("paper", paperRuneX, runesY, runesWH, runesWH, paperImage);
                scissorsRune = new MainRunes("scissors", scissorsRuneX, scissorsRuneY, runesWH, runesWH, scissorsImage);

                Image deckCardsImage = new ImageIcon(getClass().getResource("assets/cards/backOfCardTarot.png"))
                                .getImage();

                deckCards = new MainCards("deck", margin, (800 / 2) - (cardHeight / 2), cardWidth,
                                cardHeight,
                                deckCardsImage);

                defaultFont = new DefaultFont();

                activeCardImage = new ImageIcon(getClass().getResource("assets/cards/activeCard.png")).getImage();
                activeCard = new MainCards("activeCard", margin, margin, cardWidth, cardHeight, activeCardImage);
                isActiveCard = false;

                activeRuneImage = new ImageIcon(getClass().getResource("assets/runes/activeRuneTarot.png")).getImage();
                activeRune = new MainRunes("activeRune", margin, margin, runesWH, runesWH, activeRuneImage);
                isActiveRune = false;

                runesArray = new MainRunes[] { rockRune, paperRune, scissorsRune };

                playerCurrentHp = 200;
                botCurrentHp = 200;

                energyImage = new ImageIcon(getClass().getResource("assets/runes/energyTarot.png")).getImage();
                playerEnergyCount = 2;
                botEnergyCount = 2;

                cardHolderImage = new ImageIcon(getClass().getResource("assets/cards/cardHolder.png")).getImage();
                runeHolderImage = new ImageIcon(getClass().getResource("assets/runes/runeHolder.png")).getImage();

                holderCard = new MainCards("holderCard", holderCardX, holderCardY, cardWidth, cardHeight,
                                cardHolderImage);
                holderRune = new MainRunes("holderRune", holderRuneX, holderRuneY, runesWH, runesWH, runeHolderImage);

                // NEED NAKA OBJECT PUD KA BOT KAY IBUTANG BAYA DRIA NGA X AND Y ANG ACTIVE
                // CARD&RUNES SA BOT
                botHolderCard = new MainCards("botHolderCard", holderCardX, holderCardY - (cardHeight + (margin * 2)),
                                cardWidth,
                                cardHeight,
                                cardHolderImage);
                botHolderRune = new MainRunes("botHolderRune", holderRuneX, holderRuneY - (cardHeight + (margin * 2)),
                                runesWH,
                                runesWH, runeHolderImage);

                // BUT FOR RUNES CHOICES, IT WILL DIFFER, IBUTANG SA DRAW SAME OBJECT, PERO
                // IBUTANG SA ACTIVE NI BOT, RANDOMIZER SA BACKEND RA
        }

        public void PreparationFunction() {

        }

        // HANDLES HOVER/CLICK TO ACTIVE A CARD
        public void PreparationMouseClick(Point mouseClickedCoordinates,
                        MainCards[] playerOnHand, int playerCardCount) {

                // if (mouseClickedCoordinates.y > starCard.getY()
                // && mouseClickedCoordinates.y < starCard.getY() + starCard.getHeight()
                // && mouseClickedCoordinates.x > starCard.getX()
                // && mouseClickedCoordinates.x < starCard.getX() + starCard.getWidth()) {

                // System.out.println("try nakasulod ba greater than y ni Star"); // works
                // perfectly

                // starCard.setAllCardsDetails(starCard.getX() - 30, cardY - 30, cardWidth + 60,
                // cardHeight + 60);
                // towerCard.setAllCardsDetails(towerCard.getX(), cardY, cardWidth, cardHeight);
                // devilCard.setAllCardsDetails(devilCard.getX(), cardY, cardWidth, cardHeight);

                // } else if (mouseClickedCoordinates.y > towerCard.getY()
                // && mouseClickedCoordinates.y < towerCard.getY() + towerCard.getHeight()
                // && mouseClickedCoordinates.x > towerCard.getX()
                // && mouseClickedCoordinates.x < towerCard.getX() + towerCard.getWidth()) {

                // System.out.println("in Tower");
                // towerCard.setAllCardsDetails(towerCard.getX() - 30, cardY - 30, cardWidth +
                // 60,
                // cardHeight + 60);
                // devilCard.setAllCardsDetails(devilCard.getX(), cardY, cardWidth, cardHeight);
                // starCard.setAllCardsDetails(starCard.getX(), cardY, cardWidth, cardHeight);

                // } else if (mouseClickedCoordinates.y > devilCard.getY()
                // && mouseClickedCoordinates.y < devilCard.getY() + devilCard.getHeight()
                // && mouseClickedCoordinates.x > devilCard.getX()
                // && mouseClickedCoordinates.x < devilCard.getX() + devilCard.getWidth()) {

                // System.out.println("in Devil");

                // devilCard.setAllCardsDetails(devilCard.getX() - 30, cardY - 30, cardWidth +
                // 60,
                // cardHeight + 60);
                // towerCard.setAllCardsDetails(towerCard.getX(), cardY, cardWidth, cardHeight);
                // starCard.setAllCardsDetails(starCard.getX(), cardY, cardWidth, cardHeight);

                // }

                for (int i = 0; i < playerCardCount; i++) {
                        // MAKA ACTIVE RAG CARD IF NAA KAY RUNES GIPILI
                        if (isActiveRune && mouseClickedCoordinates.x > (playerOnHand[i].getX() + (i * 100)) &&
                                        mouseClickedCoordinates.y > playerOnHand[i].getY() &&
                                        mouseClickedCoordinates.x < ((playerOnHand[i].getX() + (i * 100)) + cardWidth)
                                        && mouseClickedCoordinates.y < (playerOnHand[i].getY() + cardHeight)) {
                                System.out.println("NAKASULOD BA ET?");
                                // PANG CHECK SA LOGIC ERROR, NGA TANAN SAME OBJECT ONHAND KAY MA MANIPULATE
                                // playerOnHand[i].setAllCardsDetails(100, 500, 100, 100);
                                // NO NEED NA NGA INDIVIDUAL OBJECT ANG NAA SA ON HAND, PEDE RA ANG ACTIVECARD
                                // E REFERENCE SA BATTLEPHASE

                                System.out.println(playerOnHand[i].getName());

                                // ON FRAME IF KNSA ACTIVE
                                if (!isActiveCard) {
                                        // SETNAME PARA MAO NI GAMITON PUD LATER SA EFFECTS
                                        activeCard.setName(playerOnHand[i].getName());
                                        activeCard.setX(playerOnHand[i].getX() + (i * 100));
                                        activeCard.setY(playerOnHand[i].getY());
                                        isActiveCard = true;
                                        System.out.println("NAKA TRUE NA " + activeCard.getName());
                                        // BASIC TOGGLES
                                } else if (isActiveCard
                                                // && mouseClickedCoordinates.x >= (activeCard.getX() + (i * 100))
                                                // && mouseClickedCoordinates.x <= ((activeCard.getX() + (i * 100))
                                                // + cardWidth)
                                                // && mouseClickedCoordinates.x >= activeCard.getY()
                                                // && mouseClickedCoordinates.x <= (activeCard.getY() + cardHeight)) {
                                                && playerOnHand[i].getX() + (i * 100) == (activeCard.getX())
                                                && playerOnHand[i].getY() == activeCard.getY()) {
                                        isActiveCard = false;
                                        System.out.println("DAPAT NA OFF ACTIVE");
                                        // SETNAME TO OG
                                        activeCard.setName("activeCard");
                                } else if (isActiveCard) {
                                        // SETNAME PARA MAO NI GAMITON PUD LATER SA EFFECTS
                                        activeCard.setName(playerOnHand[i].getName());
                                        activeCard.setX(playerOnHand[i].getX() + (i * 100));
                                        activeCard.setY(playerOnHand[i].getY());
                                        System.out.println("REKTA SWITCH " + activeCard.getName());
                                }

                        }
                }
        }

        public void PreparationMouseClickRunes(Point mouseClickedCoordinates) {
                // E ARRAY NALANG NI PARA MAS LIMPYO, BUHAT KO ARRAY NI RUNES
                for (int i = 0; i < 3; i++) {
                        // same sa taas, if dli active then active
                        if (mouseClickedCoordinates.x >= runesArray[i].getX() &&
                                        mouseClickedCoordinates.x <= (runesArray[i].getX() + runesWH) &&
                                        mouseClickedCoordinates.y >= runesArray[i].getY() &&
                                        mouseClickedCoordinates.y <= (runesArray[i].getY() + runesWH)) {
                                if (!isActiveRune) {
                                        // SETNAME PARA MAO NI GAMITON PUD LATER SA EFFECTS
                                        activeRune.setName(runesArray[i].getName());
                                        activeRune.setX(runesArray[i].getX());
                                        activeRune.setY(runesArray[i].getY());
                                        isActiveRune = true;
                                        System.out.println("ACTIVE: " + activeRune.getName());
                                        // if active then gi click usab, mahimong inactive
                                } else if (isActiveRune && runesArray[i].getX() == activeRune.getX() &&
                                                runesArray[i].getY() == activeRune.getY()) {
                                        isActiveRune = false;
                                        // pati active card madamay since ang rules ky no active runes no play
                                        isActiveCard = false;
                                        System.out.println("OFF BOTH");
                                        // SETNAME TO OG
                                        activeRune.setName("activeRune");
                                } else if (isActiveRune) {
                                        // SETNAME PARA MAO NI GAMITON PUD LATER SA EFFECTS
                                        activeRune.setName(runesArray[i].getName());
                                        activeRune.setX(runesArray[i].getX());
                                        activeRune.setY(runesArray[i].getY());
                                        System.out.println("REKTA SWITCH PUD SA RUNES: " + activeRune.getName());
                                }

                        }
                }
        }

        public MainCards BotChoice() {
                int botchoice = randomizer.nextInt(0, 3);
                // dli ga properly work ang karaan na ways (2) + 1 // {0,1,2} + 1 = 1,2,3
                if (botchoice == 1) {
                        return starCard;
                } else if (botchoice == 2) {
                        return towerCard;
                } else {
                        return devilCard;
                }
        }

        // public void paintComponent(Graphics g) {
        // draw(g);
        // }

        public void draw(Graphics g, MainCards[] playerOnHand, MainCards[] botOnHand,
                        int playerCardCount, int botCardCount) {

                // DRAW HP BARS FOR BOTH, THEN PLAYER ENERGY
                g.setColor(Color.decode("#C1B59F"));
                g.fillRect((1200 - 400) + 150, (800 / 2) + 20, playerCurrentHp, 30);
                g.setColor(Color.WHITE);
                // ((Graphics2D) g).setStroke(new BasicStroke(5));
                g.drawRect((1200 - 400) + 150, (800 / 2) + 20, playerCurrentHp, 30);
                g.drawString("HP: " + playerCurrentHp, (1200 - 400) + 60, (800 / 2) + 41);

                // BOT HP
                g.setColor(Color.decode("#9A4B3A"));
                g.fillRect((1200 - 400) + 150, (800 / 2) - (20 + 30), botCurrentHp, 30);
                g.setColor(Color.WHITE);
                // ((Graphics2D) g).setStroke(new BasicStroke(5));
                g.drawRect((1200 - 400) + 150, (800 / 2) - (20 + 30), botCurrentHp, 30);
                g.drawString("Bot HP: " + botCurrentHp, (1200 - 400) + 20, (800 / 2) - 30);

                // PLAYER ENERGY, AND JUST LIKE AXIE, D NMO MAKITA ENERGY SA KALABAN SOO PREDICT
                g.drawImage(energyImage, 1092, 470, 60, 60, null);
                g.drawString(playerEnergyCount + "/5", 1108, 504);
                g.drawString("Energy: ", 1010, 504);

                // HOLDER OF CHOICE RUNE&CARD FOR BATTLE
                g.drawImage(holderCard.getImg(), holderCard.getX(), holderCard.getY(), holderCard.getWidth(),
                                holderCard.getHeight(), null);
                g.drawImage(holderRune.getImage(), holderRune.getX(), holderRune.getY(), holderRune.getWidth(),
                                holderRune.getHeight(), null);

                g.drawImage(botHolderCard.getImg(), botHolderCard.getX(), botHolderCard.getY(),
                                botHolderCard.getWidth(),
                                botHolderCard.getHeight(), null);
                g.drawImage(botHolderRune.getImage(), botHolderRune.getX(), botHolderRune.getY(),
                                botHolderRune.getWidth(),
                                botHolderRune.getHeight(), null);

                // LINES AND BOUNDARIES
                g.setColor(Color.WHITE);
                g.drawRect((1200 / 2) - ((cardWidth * 4) / 2), 0, cardWidth * 4, 800);
                g.drawLine(margin + cardWidth + margin, 800 / 2, 1200, 800 / 2);
                g.drawLine(1200 / 2, 0, 1200 / 2, 800);

                g.setFont(defaultFont.getLightFontCustomSize(20));
                g.drawString("total card: " + playerCardCount + "/3", starCard.getX(), starCard.getY() - 12);

                // g.drawImage(starCard.getImg(), starCard.getX(), starCard.getY(),
                // starCard.getWidth(),
                // starCard.getHeight(),
                // null);
                // g.drawImage(towerCard.getImg(), towerCard.getX(), towerCard.getY(),
                // towerCard.getWidth(),
                // towerCard.getHeight(),
                // null);
                // g.drawImage(devilCard.getImg(), devilCard.getX(), devilCard.getY(),
                // devilCard.getWidth(),
                // devilCard.getHeight(),
                // null);

                // 5 lang ka cards max per hand
                // System.out.println(playerCardCount);
                // KAY IF 2 OR NUMBER DYUD NAA SA FORLOOP LIMIT, GOODS RA
                // BUT PLAYERCARDCOUNT DO THE SAME NGANU MANGITA PA NIG I NGA WALA YAWA
                // AHHH NGANU GA DAGAN NIG BY 2 ANG PLAYERCOUNT
                // AYY KADA FRAME NIYA IHATAG ANG ONHAND PALA

                // SA DRAWPHASE NI IPHON TAS PASA AS PARAMETER
                // PEDE RA SA PANEL TAS SYA NA DIRECTLY NAG ACCESS SA DRAW UG SA PREP
                // OKEY D NA GA ADD BY 2, COMMENT THAT SHIT playerCount sysout
                if (playerCardCount != 0) {
                        // OKEY BANTUG ERROR KAY 5 MAN PERMI LENGTH, BUHAT KO COUNTER SA GAWAS
                        // for (int i = 0; i < playerOnHand.length; i++) {
                        for (int i = 0; i < playerCardCount; i++) {
                                g.drawImage(playerOnHand[i].getImg(),
                                                playerOnHand[i].getX() + (i * 100),
                                                playerOnHand[i].getY(),
                                                playerOnHand[i].getWidth(),
                                                playerOnHand[i].getHeight(),
                                                null);
                        }
                } else {
                        g.drawString("NO CARDS LEFT", starCard.getX(), starCard.getY() + (cardHeight / 2));
                }

                if (botCardCount != 0) {
                        // OKEY BANTUG ERROR KAY 5 MAN PERMI LENGTH, BUHAT KO COUNTER SA GAWAS
                        // for (int i = 0; i < playerOnHand.length; i++) {
                        for (int i = 0; i < botCardCount; i++) {
                                g.drawImage(botOnHand[i].getImg(),
                                                botOnHand[i].getX() + (i * 100),
                                                // MATHS MATHS MATHS
                                                deckCards.getY() -
                                                                (botOnHand[i].getY() - deckCards.getY()),
                                                botOnHand[i].getWidth(),
                                                botOnHand[i].getHeight(),
                                                null);
                        }
                } else {
                        g.drawString("NO CARDS LEFT", starCard.getX(),
                                        // starCard.getY() + (cardHeight / 2));
                                        // MATHS MATHS MATHS
                                        deckCards.getY() - (starCard.getY()
                                                        - (deckCards.getY() + (cardHeight / 2))));
                }

                // REFACTOR STARTS HERE
                // DRAW RUNES
                g.drawImage(rockRune.getImage(), rockRune.getX(), rockRune.getY(), rockRune.getWidth(),
                                rockRune.getHeight(),
                                null);
                g.drawImage(paperRune.getImage(), paperRune.getX(), paperRune.getY(), paperRune.getWidth(),
                                paperRune.getHeight(),
                                null);
                g.drawImage(scissorsRune.getImage(), scissorsRune.getX(), scissorsRune.getY(), scissorsRune.getWidth(),
                                scissorsRune.getHeight(),
                                null);

                // DRAW DECK
                g.drawImage(deckCards.getImg(), deckCards.getX(), deckCards.getY(), deckCards.getWidth(),
                                deckCards.getHeight(),
                                null);

                // DRAW WHERE IS THE ACTIVE CARD
                if (isActiveCard) {
                        g.drawImage(activeCard.getImg(), activeCard.getX(), activeCard.getY(), activeCard.getWidth(),
                                        activeCard.getHeight(), null);
                }

                // DRAW WHERE IS THE ACTIVE RUNE
                if (isActiveRune) {
                        g.drawImage(activeRune.getImage(), activeRune.getX(), activeRune.getY(), activeRune.getWidth(),
                                        activeRune.getHeight(), null);
                }
        }
        // PLANNING TO REVISION THIS DRAFT
}
