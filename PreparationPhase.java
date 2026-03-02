import java.awt.*;
import javax.swing.ImageIcon;

public class PreparationPhase {

    MainCards starCard;
    MainCards towerCard;
    MainCards devilCard;

    int cardWidth = 735 / 8; // 92
    int cardHeight = 1208 / 8; // 151
    int margin = 50;

    PreparationPhase() {

        Image starImg = new ImageIcon(getClass().getResource("assets/star.jpg")).getImage();
        Image towerImg = new ImageIcon(getClass().getResource("assets/tower.jpg")).getImage();
        Image devilImg = new ImageIcon(getClass().getResource("assets/devil.jpg")).getImage();

        starCard = new MainCards(100 + margin, 700, cardWidth, cardHeight, starImg);
        towerCard = new MainCards(192 + (margin * 2), 700, cardWidth, cardHeight, towerImg);
        devilCard = new MainCards(284 + (margin * 3), 700, cardWidth, cardHeight, devilImg);
    }

    public void PreparationFunction() {

    }

    // HANDLES HOVER/CLICK TO ACTIVE A CARD
    public void PreparationMouseClick(Point mouseClickedCoordinates) {
        boolean starIsActive = false, towerIsActive = false, devilIsActive = false;

        if (mouseClickedCoordinates.y > starCard.getY()
                && mouseClickedCoordinates.y < starCard.getY() + starCard.getHeight()
                && mouseClickedCoordinates.x > starCard.getX()
                && mouseClickedCoordinates.x < starCard.getX() + starCard.getWidth()) {

            System.out.println("try nakasulod ba greater than y ni Star"); // works perfectly

            starIsActive = true;
            towerIsActive = false;
            devilIsActive = false;

        } else if (mouseClickedCoordinates.y > towerCard.getY()
                && mouseClickedCoordinates.y < towerCard.getY() + towerCard.getHeight()
                && mouseClickedCoordinates.x > towerCard.getX()
                && mouseClickedCoordinates.x < towerCard.getX() + towerCard.getWidth()) {

            System.out.println("in Tower");

            starIsActive = false;
            towerIsActive = true;
            devilIsActive = false;

        } else if (mouseClickedCoordinates.y > devilCard.getY()
                && mouseClickedCoordinates.y < devilCard.getY() + devilCard.getHeight()
                && mouseClickedCoordinates.x > devilCard.getX()
                && mouseClickedCoordinates.x < devilCard.getX() + devilCard.getWidth()) {

            System.out.println("in Devil");

            starIsActive = false;
            towerIsActive = false;
            devilIsActive = true;
        }

        if (starIsActive) {
            starCard.setHeight(starCard.getHeight() + 100);
            starCard.setWidth(starCard.getWidth() + 100);
        }
        if (towerIsActive) {
            towerCard.setHeight(towerCard.getHeight() + 100);
            towerCard.setWidth(towerCard.getWidth() + 100);
        }
        if (devilIsActive) {
            devilCard.setHeight(devilCard.getHeight() + 100);
            devilCard.setWidth(devilCard.getWidth() + 100);
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
}
