import java.awt.Graphics;
import java.awt.Image;
import java.awt.FontMetrics;

import javax.swing.ImageIcon;

public class BattlePhase {

    PreparationPhase cardObject;
    DrawPhase drawObject;
    MainRunes runeWinner;

    Boolean drawXALoser, drawXBLoser;
    Image X;

    // REFACTOR INIT
    int backAnimationTimer;
    int incrementSpeed;
    Boolean stopAfterBack;
    int stopAfterBackTimer;

    DefaultFont defaultFont;

    Boolean animationRuneCollideDone;

    // COLLISION = COLLISION OF RUNES ANIMATION,
    // BATTLE = BATTLE OF RUNES LOGIC RESULT,
    // RESULT = RESULTS VISUAL
    // (FUNCTION/METHOD)
    BattlePhase() {
        drawXALoser = false;
        drawXBLoser = false;
        X = new ImageIcon(getClass().getResource("assets/X.png")).getImage();
        backAnimationTimer = 120;
        incrementSpeed = 10;
        stopAfterBack = false;
        stopAfterBackTimer = 250;
        defaultFont = new DefaultFont();
        animationRuneCollideDone = false;
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

        // ASSUMING NGA SI BOT GAPILI PERMI UG RUNES (WHICH IS MAO ANG CURRENT SETUP)
        if (player.getName().equals("activeRune")) {
            runeWinner = bot;
            // THIS IS MUCH FLEXIBLE BUT THIS IS JUST FOR RUNES SO REKTA NAME NA
            // if (a.getName().equals(cardObject.rockRune.getName())) {
        } else if (player.getName().equals("rock")) {

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
            System.out.println("TIE ETOH");
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
    public void CollisionRunes(MainRunes player, MainRunes bot) {
        if (player.getY() < bot.getY() + (bot.getHeight() - 19)) {
            System.out.println("collide rune");
            animationRuneCollideDone = true;
        } else if (backAnimationTimer >= 0) {
            backAnimationTimer -= 24;
            bot.setY(bot.getY() - 13);
            player.setY(player.getY() + 13);
            if (backAnimationTimer <= 0) {
                stopAfterBack = true;
            }
        } else if (stopAfterBack) {
            // stop ngani, wlay buhaton si rune pang momentum sa smash
            stopAfterBackTimer -= 24;
            if (stopAfterBackTimer < 0) {
                stopAfterBack = false;
            }
        } else {
            // player.setY(player.getY() - 10);
            // nigana, pero yw si holder nsd ato controllon dri, kalibog not consistent
            // cardObject.holderRune.setY(
            // cardObject.holderRune.getY() - 5);
            bot.setY(bot.getY() + incrementSpeed);
            bot.setX(bot.getX() + 1);
            player.setY(player.getY() - incrementSpeed);
            player.setX(player.getX() - 1);
            incrementSpeed += 4;
            // ((Graphics2D) g).rotate(0); // NEXT TIME NALANG NI
        }
    }

    public void ResultRunes(Graphics g) {
        // SA DRAW NA DAAY TA MAGBUTANG SA RESULT NGA TEXT, ANG SA VISUAL DIRI

        // ayg kalibog, if dli si player winner (means siya loser)
        if (!(runeWinner.getName().equals(cardObject.activeRune.getName()))) {
            // KUHA
            g.drawImage(X, cardObject.holderRune.getX(), cardObject.holderRune.getY(),
                    120, 120, null);
        } else {
            g.drawImage(X, cardObject.botHolderRune.getX(), cardObject.botHolderRune.getY(),
                    120, 120, null);
        }
        // CHECK IF MO X IF BOT ANG PILDI
        // g.drawImage(X, cardObject.botHolderRune.getX(),
        // cardObject.botHolderRune.getY(), 120, 120, null);
    }

    public void draw(Graphics g) {
        cardObject.draw(g, drawObject.playerOnHand, drawObject.botOnHand,
                drawObject.playerCardCount,
                drawObject.botCardCount);

        if (animationRuneCollideDone) {
            g.setFont(defaultFont.getBoldFontCustomSize(35));
            // DRAW RESULT TEXT AND RESULT ART EFFECTS (GIF/ARRAY OF IMG)
            String result = "";
            if (runeWinner == cardObject.activeRune) {
                result = "YOU WIN DUMBASS!";
            } else if (runeWinner == cardObject.botHolderRune) {
                result = "DUHH ALWAYS A LOSER!";
            } else {
                result = "IT'S ATAY :>";
            }

            ResultRunes(g); // RESULT VISUAL DIRI

            // MESSAGE FOR THE RESULTS PLUS ACCURATE CENTER (FM)
            FontMetrics fm = g.getFontMetrics();
            int resultTextWidth = fm.stringWidth(result);

            g.drawString(result, (1200 / 2) - (resultTextWidth / 2), 700);
        }
    }

}