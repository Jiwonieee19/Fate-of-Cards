import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class BattlePhase {

    PreparationPhase cardObject;
    DrawPhase drawObject;
    MainRunes runeWinner;
    Boolean drawXALoser, drawXBLoser;
    Image X;

    BattlePhase() {
        drawXALoser = false;
        drawXBLoser = false;
        X = new ImageIcon(getClass().getResource("assets/X.png")).getImage();
    }

    public void Function() {

    }

    // PEDE RA DAAY NI YW PARA ISA RA KA OBJECT DALA NNYU
    // HAHAHAHAH WORKING, NA FIX ANG BUG NGA DILI MO GAWAS ANG RESULT SA RUNES
    // IF NAA ANG DRAW, PLUS BANTUG EMPTY ANG NEW DRAW KAY TUNGOD NEW MAN TONG 2
    // OBJECT PAG INITIALIZE SA TAAS
    public void PassingObjects(PreparationPhase preparationPhase, DrawPhase drawPhase) {
        this.cardObject = preparationPhase;
        this.drawObject = drawPhase;
    }

    public void BattleRunes(MainRunes player, MainRunes bot) {

        // THIS IS MUCH FLEXIBLE BUT THIS IS JUST FOR RUNES SO REKTA NAME NA
        // if (a.getName().equals(cardObject.rockRune.getName())) {
        if (player.getName().equals("rock")) {

            if (bot.getName().equals("scissors")) {
                runeWinner = player;
            } else if (bot.getName().equals("paper")) {
                runeWinner = bot;
            }
        } else if (player.getName().equals("paper")) {
            if (bot.getName().equals("rock")) {
                runeWinner = player;
            } else if (bot.getName().equals("scissors")) {
                runeWinner = bot;
            }
        } else if (player.getName().equals("scissors")) {
            if (bot.getName().equals("paper")) {
                runeWinner = player;
            } else if (bot.getName().equals("rock")) {
                runeWinner = bot;
            }
        } else {
            System.out.println("TIE 2");
            runeWinner = null;
        }
        if (runeWinner != null) {
            System.out.println(
                    "player :" + player.getName() + "\nbot: " + bot.getName() + "\nwinner: " + runeWinner.getName());
        }
    }

    public void collideResult(MainCards a, MainCards b) {
        // System.out.println("COllide");
        // if (winner == a) {
        // System.out.println("Compared two Mainclass successfully"); // works fine
        // drawXBLoser = true;
        // } else if (winner == b) {
        // drawXALoser = true;
        // }
    }

    // public void draw(Graphics g, MainCards a, int aVelocityY, MainCards b, int
    // bVelocityY) {
    // g.drawImage(a.getImg(), 700 / 2 - (prepPhaseObj.cardWidth / 2), aVelocityY,
    // a.getWidth() - 60,
    // a.getHeight() - 60,
    // null);
    // // -60 ka player since every a ky active card, which is nidako sad if active
    // g.drawImage(b.getImg(), 700 / 2 - (prepPhaseObj.cardWidth / 2), bVelocityY,
    // prepPhaseObj.cardWidth,
    // prepPhaseObj.cardHeight, null);
    // // prep nagkuha width and height, pra dli active width ug height makuha tie
    // if (drawXBLoser) {
    // // kaya pala mali ang b.getX(), kto daay sa og nga x coordinates gina
    // retrieve
    // g.drawImage(X, 700 / 2 - (prepPhaseObj.cardWidth / 2) - 50, bVelocityY, 180,
    // 180, null);
    // } else if (drawXALoser) {
    // g.drawImage(X, 700 / 2 - (prepPhaseObj.cardWidth / 2) - 50, aVelocityY, 180,
    // 180, null);
    // }
    // }
    // }

    // INSTEAD NG ACTIVE VS BOTHOLLDER, BOTH HOLDER NALANG KY MAO MN TO
    // E MANIPULATE PAG DRAW
    public void CollisionRune(MainRunes player, MainRunes bot) {
        if (player.getY() < bot.getY() + bot.getHeight()) {
            System.out.println("collide rune");
        } else {
            // player.setY(player.getY() - 10);
            // nigana, pero yw si holder nsd ato controllon dri, kalibog not consistent
            // cardObject.holderRune.setY(
            // cardObject.holderRune.getY() - 5);
            player.setY(player.getY() - 5);
        }
    }

    public void draw(Graphics g) {
        cardObject.draw(g, drawObject.playerOnHand, drawObject.botOnHand,
                drawObject.playerCardCount,
                drawObject.botCardCount);
    }

}