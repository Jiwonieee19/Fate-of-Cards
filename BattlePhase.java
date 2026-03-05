import java.awt.Graphics;

public class BattlePhase {

    PreparationPhase prepPhaseObj;

    BattlePhase() {
        prepPhaseObj = new PreparationPhase();
        prepPhaseObj.starCard.getClass();
    }

    public void Function() {

    }

    public MainCards BattleCards(MainCards a, MainCards b) {
        // MainCards winner = a;
        if (a.getName().equals(prepPhaseObj.starCard.getName())) {
            System.out.println("star yarn");
        }
        return null;
    }

    public void draw(Graphics g) {
// cc
    }
}
