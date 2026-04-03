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
        int runesY = 600;
        int scissorsRuneY = 600 - ((runesWH + 90) / 2);
        int rockRuneX = 810;
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

        PreparationPhase() {

                // CREATING THE IMAGES AND CARDS HERE COZ SEPARATING COST A LONGER CALLING
                // e.g. g.drawImage(instantiateCards.starCard.getImage(), , , , )
                // i dont want that

                Image starImg = new ImageIcon(getClass().getResource("assets/devil.jpg")).getImage();
                Image towerImg = new ImageIcon(getClass().getResource("assets/tower.jpg")).getImage();
                Image devilImg = new ImageIcon(getClass().getResource("assets/devil.png")).getImage();

                // TANAN CARS, SAME X, MAG DIFFER NA DIDTO SA ARRAY NGA ONHAND-CARDS NI PLAYER
                starCard = new MainCards("star", margin, cardY, cardWidth, cardHeight, starImg);
                towerCard = new MainCards("tower", margin, cardY, cardWidth, cardHeight, towerImg);
                devilCard = new MainCards("devil", margin, cardY, cardWidth, cardHeight, devilImg);

                randomizer = new Random();

                // REFACTOR STARTS HERE
                Image rockImage = new ImageIcon(getClass().getResource("assets/runes/rockRune.png")).getImage();
                Image paperImage = new ImageIcon(getClass().getResource("assets/runes/paperRune.png")).getImage();
                Image scissorsImage = new ImageIcon(getClass().getResource("assets/runes/scissorsRune.png")).getImage();

                rockRune = new MainRunes("rock", rockRuneX, runesY, runesWH, runesWH, rockImage);
                paperRune = new MainRunes("paper", paperRuneX, runesY, runesWH, runesWH, paperImage);
                scissorsRune = new MainRunes("scissors", scissorsRuneX, scissorsRuneY, runesWH, runesWH, scissorsImage);

                Image deckCardsImage = new ImageIcon(getClass().getResource("assets/backOfCard.jpg")).getImage();

                deckCards = new MainCards("deck", margin, (800 / 2) - (cardHeight / 2), cardWidth,
                                cardHeight,
                                deckCardsImage);

                defaultFont = new DefaultFont();

                activeCardImage = new ImageIcon(getClass().getResource("assets/activeCard.png")).getImage();
                activeCard = new MainCards("activeCard", margin, margin, cardWidth, cardHeight, activeCardImage);
                isActiveCard = false;

                activeRuneImage = new ImageIcon(getClass().getResource("assets/runes/activeRune.png")).getImage();
                activeRune = new MainRunes("activeRune", margin, margin, runesWH, runesWH, activeRuneImage);
                isActiveRune = false;

                runesArray = new MainRunes[] { rockRune, paperRune, scissorsRune };

                playerCurrentHp = 200;
                botCurrentHp = 200;

                energyImage = new ImageIcon(getClass().getResource("assets/runes/energy.png")).getImage();
                playerEnergyCount = 2;
                botEnergyCount = 2;
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
                g.setColor(Color.GREEN);
                g.fillRect((1200 - 400) + 150, (800 / 2) + 20, playerCurrentHp, 30);
                g.setColor(Color.WHITE);
                // ((Graphics2D) g).setStroke(new BasicStroke(5));
                g.drawRect((1200 - 400) + 150, (800 / 2) + 20, playerCurrentHp, 30);
                g.drawString("HP: " + playerCurrentHp, (1200 - 400) + 60, (800 / 2) + 41);

                // BOT HP
                g.setColor(Color.RED);
                g.fillRect((1200 - 400) + 150, (800 / 2) - (20 + 30), botCurrentHp, 30);
                g.setColor(Color.WHITE);
                // ((Graphics2D) g).setStroke(new BasicStroke(5));
                g.drawRect((1200 - 400) + 150, (800 / 2) - (20 + 30), botCurrentHp, 30);
                g.drawString("Bot HP: " + botCurrentHp, (1200 - 400) + 20, (800 / 2) - 30);

                // PLAYER ENERGY, AND JUST LIKE AXIE, D NMO MAKITA ENERGY SA KALABAN SOO PREDICT
                g.drawImage(energyImage, 1092, 478, 60, 60, null);
                g.drawString(playerEnergyCount + "/5", 1108, 512);
                g.drawString("Energy: ", 1010, 512);

                // LINES AND BOUNDARIES
                g.setColor(Color.WHITE);
                g.drawRect((1200 / 2) - ((cardWidth * 4) / 2), 0, cardWidth * 4, 800);
                g.drawLine(margin + cardWidth + margin, 800 / 2, 1200, 800 / 2);

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
