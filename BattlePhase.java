import java.awt.Graphics;

public class BattlePhase {

    PreparationPhase prepPhaseObj;

    BattlePhase() {
        prepPhaseObj = new PreparationPhase();
    }

    public void Function() {

    }

    public void BattleCards(MainCards a, MainCards b) {

        if (a.getName().equals(prepPhaseObj.starCard.getName()) &&
                b.getName().equals(prepPhaseObj.devilCard.getName())) {

            System.out.println("star A, devil B winner: DEVIL B {CHECKER}");

        } else if (a.getName().equals(prepPhaseObj.starCard.getName()) &&
                b.getName().equals(prepPhaseObj.towerCard.getName())) {

            System.out.println("star A, tower B winner: STAR A {CHECKER}");

            // TOWER PLAYER A
        } else if (a.getName().equals(prepPhaseObj.towerCard.getName()) &&
                b.getName().equals(prepPhaseObj.starCard.getName())) {

            System.out.println("tower A, star B winner: STAR B {CHECKER}");

        } else if (a.getName().equals(prepPhaseObj.towerCard.getName()) &&
                b.getName().equals(prepPhaseObj.devilCard.getName())) {

            System.out.println("tower A, devil B winner: TOWER A {CHECKER}");

            // DEVIL PLAYER A
        } else if (a.getName().equals(prepPhaseObj.devilCard.getName()) &&
                b.getName().equals(prepPhaseObj.towerCard.getName())) {

            System.out.println("devil A, tower B winner: TOWER B {CHECKER}");

        } else if (a.getName().equals(prepPhaseObj.devilCard.getName()) &&
                b.getName().equals(prepPhaseObj.starCard.getName())) {

            System.out.println("devil A, star B winner: DEVIL A {CHECKER}");

        } else {
            System.out.println("TIE 2");
        }
        // cc sori
    }

    public void collideFunction() {
        System.out.println("COllide");
    }

    public void draw(Graphics g, MainCards a, int aVelocityY, MainCards b, int bVelocityY) {
        g.drawImage(a.getImg(), 700 / 2 - (prepPhaseObj.cardWidth / 2), aVelocityY, a.getWidth() - 60,
                a.getHeight() - 60,
                null);
        // -60 ka player since every a ky active card, which is nidako sad if active
        g.drawImage(b.getImg(), 700 / 2 - (prepPhaseObj.cardWidth / 2), bVelocityY, prepPhaseObj.cardWidth,
                prepPhaseObj.cardHeight, null);
        // prep nagkuha width and height, pra dli active width ug height makuha tie
    }
}
