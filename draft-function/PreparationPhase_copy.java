import java.awt.*;
import java.util.Random;

import javax.swing.ImageIcon;

public class PreparationPhase_copy {

    MainCards starCard;
    MainCards towerCard;
    MainCards devilCard;

    int cardWidth = 735 / 8; // 92
    int cardHeight = 1208 / 8; // 151
    int margin = 50;
    int cardY = 700;
    int starCardX = 100 + margin;
    int towerCardX = 192 + (margin * 2);
    int devilCardX = 284 + (margin * 3);

    Random randomizer;

    PreparationPhase_copy() {

        Image starImg = new ImageIcon(getClass().getResource("assets/star.jpg")).getImage();
        Image towerImg = new ImageIcon(getClass().getResource("assets/tower.jpg")).getImage();
        Image devilImg = new ImageIcon(getClass().getResource("assets/devil.jpg")).getImage();

        starCard = new MainCards("star", starCardX, cardY, cardWidth, cardHeight, starImg, 3);
        towerCard = new MainCards("tower", towerCardX, cardY, cardWidth, cardHeight, towerImg, 3);
        devilCard = new MainCards("devil", devilCardX, cardY, cardWidth, cardHeight, devilImg, 3);

        randomizer = new Random();
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
        g.drawImage(starCard.getImg(), starCard.getX(), starCard.getY(), starCard.getWidth(), starCard.getHeight(),
                null);
        g.drawImage(towerCard.getImg(), towerCard.getX(), towerCard.getY(), towerCard.getWidth(), towerCard.getHeight(),
                null);
        g.drawImage(devilCard.getImg(), devilCard.getX(), devilCard.getY(), devilCard.getWidth(), devilCard.getHeight(),
                null);
    }
    // PLANNING TO REVISION THIS DRAFT
}
