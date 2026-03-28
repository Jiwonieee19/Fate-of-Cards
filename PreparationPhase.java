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
    int cardY = 600;
    int starCardX = 100 + margin;
    int towerCardX = 100 + (margin * 2);
    int devilCardX = 100 + (margin * 3);

    Random randomizer;

    MainRunes rockRune;
    MainRunes paperRune;
    MainRunes scissorsRune;

    // MATHS MATHS MATHS
    int runesWH = 120;
    int runesY = 600;
    int scissorsRuneY = 600 - ((runesWH + 90) / 2);
    int rockRuneX = 850;
    int paperRuneX = rockRuneX + runesWH;
    int scissorsRuneX = rockRuneX + (runesWH / 2);

    PreparationPhase() {

        Image starImg = new ImageIcon(getClass().getResource("assets/star.jpg")).getImage();
        Image towerImg = new ImageIcon(getClass().getResource("assets/tower.jpg")).getImage();
        Image devilImg = new ImageIcon(getClass().getResource("assets/devil.jpg")).getImage();

        starCard = new MainCards("star", starCardX, cardY, cardWidth, cardHeight, starImg);
        towerCard = new MainCards("tower", towerCardX, cardY, cardWidth, cardHeight, towerImg);
        devilCard = new MainCards("devil", devilCardX, cardY, cardWidth, cardHeight, devilImg);

        randomizer = new Random();

        Image rockImage = new ImageIcon(getClass().getResource("assets/runes/rockRune.png")).getImage();
        Image paperImage = new ImageIcon(getClass().getResource("assets/runes/paperRune.png")).getImage();
        Image scissorsImage = new ImageIcon(getClass().getResource("assets/runes/scissorsRune.png")).getImage();

        rockRune = new MainRunes("rock", rockRuneX, runesY, runesWH, runesWH, rockImage);
        paperRune = new MainRunes("paper", paperRuneX, runesY, runesWH, runesWH, paperImage);
        scissorsRune = new MainRunes("scissors", scissorsRuneX, scissorsRuneY, runesWH, runesWH, scissorsImage);
    }

    public void PreparationFunction() {

    }

    // HANDLES HOVER/CLICK TO ACTIVE A CARD
    public void PreparationMouseClick(Point mouseClickedCoordinates) {

        if (mouseClickedCoordinates.y > starCard.getY()
                && mouseClickedCoordinates.y < starCard.getY() + starCard.getHeight()
                && mouseClickedCoordinates.x > starCard.getX()
                && mouseClickedCoordinates.x < starCard.getX() + starCard.getWidth()) {

            System.out.println("try nakasulod ba greater than y ni Star"); // works perfectly

            starCard.setAllCardsDetails(starCardX - 30, cardY - 30, cardWidth + 60, cardHeight + 60);
            towerCard.setAllCardsDetails(towerCardX, cardY, cardWidth, cardHeight);
            devilCard.setAllCardsDetails(devilCardX, cardY, cardWidth, cardHeight);

        } else if (mouseClickedCoordinates.y > towerCard.getY()
                && mouseClickedCoordinates.y < towerCard.getY() + towerCard.getHeight()
                && mouseClickedCoordinates.x > towerCard.getX()
                && mouseClickedCoordinates.x < towerCard.getX() + towerCard.getWidth()) {

            System.out.println("in Tower");
            towerCard.setAllCardsDetails(towerCardX - 30, cardY - 30, cardWidth + 60,
                    cardHeight + 60);
            devilCard.setAllCardsDetails(devilCardX, cardY, cardWidth, cardHeight);
            starCard.setAllCardsDetails(starCardX, cardY, cardWidth, cardHeight);

        } else if (mouseClickedCoordinates.y > devilCard.getY()
                && mouseClickedCoordinates.y < devilCard.getY() + devilCard.getHeight()
                && mouseClickedCoordinates.x > devilCard.getX()
                && mouseClickedCoordinates.x < devilCard.getX() + devilCard.getWidth()) {

            System.out.println("in Devil");

            devilCard.setAllCardsDetails(devilCardX - 30, cardY - 30, cardWidth + 60,
                    cardHeight + 60);
            towerCard.setAllCardsDetails(towerCardX, cardY, cardWidth, cardHeight);
            starCard.setAllCardsDetails(starCardX, cardY, cardWidth, cardHeight);

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

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect((1200 / 2) - ((cardWidth * 4) / 2), 0, cardWidth * 4, 800);
        g.drawLine(0, 800 / 2, 1200, 800 / 2);

        g.drawImage(starCard.getImg(), starCard.getX(), starCard.getY(), starCard.getWidth(), starCard.getHeight(),
                null);
        g.drawImage(towerCard.getImg(), towerCard.getX(), towerCard.getY(), towerCard.getWidth(), towerCard.getHeight(),
                null);
        g.drawImage(devilCard.getImg(), devilCard.getX(), devilCard.getY(), devilCard.getWidth(), devilCard.getHeight(),
                null);

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
    }
    // PLANNING TO REVISION THIS DRAFT
}
