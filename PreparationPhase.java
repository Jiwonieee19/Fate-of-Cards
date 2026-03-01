import java.awt.*;
import javax.swing.ImageIcon;

public class PreparationPhase {

    MainCards starCard;
    MainCards towerCard;
    MainCards devilCard;

    int cardWidth = 735 / 8; // 92
    int cardHeight = 1208 / 8; // 151

    PreparationPhase() {

        Image starImg = new ImageIcon(getClass().getResource("assets/star.jpg")).getImage();
        Image towerImg = new ImageIcon(getClass().getResource("assets/tower.jpg")).getImage();
        Image devilImg = new ImageIcon(getClass().getResource("assets/devil.jpg")).getImage();

        starCard = new MainCards(100, 100, cardWidth, cardHeight, starImg);
        towerCard = new MainCards(192, 100, cardWidth, cardHeight, towerImg);
        devilCard = new MainCards(284, 100, cardWidth, cardHeight, devilImg);
    }

    public void PreparationFunction() {

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
