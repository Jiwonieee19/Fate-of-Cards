import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class BattlePhase_copy {

    PreparationPhase prepPhaseObj;
    MainCards winner;
    Boolean drawXALoser, drawXBLoser;
    Image X;

    BattlePhase_copy() {
        prepPhaseObj = new PreparationPhase();
        drawXALoser = false;
        drawXBLoser = false;
        X = new ImageIcon(getClass().getResource("assets/X.png")).getImage();
    }

    public void Function() {

    }

    public void BattleCards(MainCards a, MainCards b) {

        if (a.getName().equals(prepPhaseObj.starCard.getName()) &&
                b.getName().equals(prepPhaseObj.devilCard.getName())) {

            System.out.println("star A, devil B winner: DEVIL B {CHECKER}");
            winner = b;

        } else if (a.getName().equals(prepPhaseObj.starCard.getName()) &&
                b.getName().equals(prepPhaseObj.towerCard.getName())) {

            System.out.println("star A, tower B winner: STAR A {CHECKER}");
            winner = a;

            // TOWER PLAYER A
        } else if (a.getName().equals(prepPhaseObj.towerCard.getName()) &&
                b.getName().equals(prepPhaseObj.starCard.getName())) {

            System.out.println("tower A, star B winner: STAR B {CHECKER}");
            winner = b;

        } else if (a.getName().equals(prepPhaseObj.towerCard.getName()) &&
                b.getName().equals(prepPhaseObj.devilCard.getName())) {

            System.out.println("tower A, devil B winner: TOWER A {CHECKER}");
            winner = a;

            // DEVIL PLAYER A
        } else if (a.getName().equals(prepPhaseObj.devilCard.getName()) &&
                b.getName().equals(prepPhaseObj.towerCard.getName())) {

            System.out.println("devil A, tower B winner: TOWER B {CHECKER}");
            winner = b;

        } else if (a.getName().equals(prepPhaseObj.devilCard.getName()) &&
                b.getName().equals(prepPhaseObj.starCard.getName())) {

            System.out.println("devil A, star B winner: DEVIL A {CHECKER}");
            winner = a;

        } else {
            System.out.println("TIE 2");
            winner = null;
        }
    }

    public void collideResult(MainCards a, MainCards b) {
        System.out.println("COllide");
        if (winner == a) {
            System.out.println("Compared two Mainclass successfully"); // works fine
            drawXBLoser = true;
        } else if (winner == b) {
            drawXALoser = true;
        }
    }

    public void draw(Graphics g, MainCards a, int aVelocityY, MainCards b, int bVelocityY) {
        g.drawImage(a.getImg(), 700 / 2 - (prepPhaseObj.cardWidth / 2), aVelocityY, a.getWidth() - 60,
                a.getHeight() - 60,
                null);
        // -60 ka player since every a ky active card, which is nidako sad if active
        g.drawImage(b.getImg(), 700 / 2 - (prepPhaseObj.cardWidth / 2), bVelocityY, prepPhaseObj.cardWidth,
                prepPhaseObj.cardHeight, null);
        // prep nagkuha width and height, pra dli active width ug height makuha tie
        if (drawXBLoser) {
            // kaya pala mali ang b.getX(), kto daay sa og nga x coordinates gina retrieve
            g.drawImage(X, 700 / 2 - (prepPhaseObj.cardWidth / 2) - 50, bVelocityY, 180, 180, null);
        } else if (drawXALoser) {
            g.drawImage(X, 700 / 2 - (prepPhaseObj.cardWidth / 2) - 50, aVelocityY, 180, 180, null);
        }
    }
}
