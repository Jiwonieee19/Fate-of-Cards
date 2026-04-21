import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.awt.FontMetrics;

import javax.swing.ImageIcon;

public class BattlePhase {

    PreparationPhase cardObject;
    DrawPhase drawObject;
    MainRunes runeWinner;
    CardsEffects cardsEffectsObject;

    Image runeAnimationGIF, cardAnimationGIF;

    // REFACTOR INIT
    int backAnimationTimer;
    int incrementSpeed;
    Boolean stopAfterBack;
    int stopAfterBackTimer;

    DefaultFont defaultFont;

    Boolean animationRuneCollideDone;
    Boolean playerCardEffectDone, botCardEffectDone;

    int secondCounter;

    String winnerName;

    // COLLISION = COLLISION OF RUNES ANIMATION,
    // BATTLE = BATTLE OF RUNES LOGIC RESULT,
    // RESULT = RESULTS VISUAL
    // EFFECTS CHECKER = CHECK THE CARD IF PERFORM OR NAH
    // EFFECTS = RESULTS VISUAL
    // (FUNCTION/METHOD)
    BattlePhase() {
        URL gifUrl = BattlePhase.class.getResource("/assets/animations/runeLoseGIF.gif");
        runeAnimationGIF = new ImageIcon(gifUrl).getImage();
        // X = new ImageIcon(getClass().getResource("assets/X.png")).getImage();
        backAnimationTimer = 120;
        incrementSpeed = 10;
        stopAfterBack = false;
        stopAfterBackTimer = 250;
        defaultFont = new DefaultFont();
        animationRuneCollideDone = false;
        playerCardEffectDone = false;
        botCardEffectDone = false;
        winnerName = "tie";
        secondCounter = 1000; // 1sec
    }

    // PEDE RA DAAY NI YW PARA ISA RA KA OBJECT DALA NNYU
    // HAHAHAHAH WORKING, NA FIX ANG BUG NGA DILI MO GAWAS ANG RESULT SA RUNES
    // IF NAA ANG DRAW, PLUS BANTUG EMPTY ANG NEW DRAW KAY TUNGOD NEW MAN TONG 2
    // OBJECT PAG INITIALIZE SA TAAS
    public void passingObjects(PreparationPhase preparationPhase, DrawPhase drawPhase,
            CardsEffects cardsEffectsObject) {
        this.cardObject = preparationPhase;
        this.drawObject = drawPhase;
        // PEDE RA DLI IPASA APIL KY EFFECTS RMN NAA DDTO, PERO PASA NLNG PRA NO BUG
        this.cardsEffectsObject = cardsEffectsObject;
    }

    public void battleRunes(MainRunes player, MainRunes bot) {

        // ASSUMING NGA SI BOT GAPILI PERMI UG RUNES (WHICH IS MAO ANG CURRENT SETUP)
        if (player.getName().equals("activeRune")) {
            runeWinner = bot;
            winnerName = "bot";
            // THIS IS MUCH FLEXIBLE BUT THIS IS JUST FOR RUNES SO REKTA NAME NA
            // if (a.getName().equals(cardObject.rockRune.getName())) {
        } else if (player.getName().equals("rock")) {

            if (bot.getName().equals("scissors")) {
                runeWinner = player;
                winnerName = "player";
            } else if (bot.getName().equals("paper")) {
                runeWinner = bot;
                winnerName = "bot";
            }
        } else if (player.getName().equals("paper")) {
            if (bot.getName().equals("rock")) {
                runeWinner = player;
                winnerName = "player";
            } else if (bot.getName().equals("scissors")) {
                runeWinner = bot;
                winnerName = "bot";
            }
        } else if (player.getName().equals("scissors")) {
            if (bot.getName().equals("paper")) {
                runeWinner = player;
                winnerName = "player";
            } else if (bot.getName().equals("rock")) {
                runeWinner = bot;
                winnerName = "bot";
            }
        } else {
            System.out.println("TIE ETOH");
            runeWinner = null;
            winnerName = "tie";
        }
        // // CHECKER RA SA RUNES AND EVERY FRAME MO RUN
        // if (runeWinner != null) {
        // System.out.println(
        // "player :" + player.getName() + "\nbot: " + bot.getName() + "\nwinner: " +
        // runeWinner.getName());
        // }
    }

    // INSTEAD NG ACTIVE VS BOTHOLLDER, BOTH HOLDER NALANG KY MAO MN TO
    // E MANIPULATE PAG DRAW
    public void collisionRunes(MainRunes player, MainRunes bot) {
        if (player.getY() < bot.getY() + (bot.getHeight() - 19)) {
            // System.out.println("collide rune"); // CHECKER RA IF NAG COLLIDE, PERFRAME
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

    public void resultRunes(Graphics g) {
        // SA DRAW NA DAAY TA MAGBUTANG SA RESULT NGA TEXT, ANG SA VISUAL DIRI
        if (secondCounter > 0) {
            if (runeWinner == null) {
                // DIRIA PAG TIE
                g.drawImage(runeAnimationGIF, cardObject.holderRune.getX(), cardObject.holderRune.getY(),
                        120, 120, null);
                g.drawImage(runeAnimationGIF, cardObject.botHolderRune.getX(), cardObject.botHolderRune.getY(),
                        120, 120, null);
            } else {
                // ayg kalibog, if dli si player winner (means siya loser)
                if (!(runeWinner.getName().equals(cardObject.activeRune.getName()))) {
                    // KUHA
                    g.drawImage(runeAnimationGIF, cardObject.holderRune.getX(), cardObject.holderRune.getY(),
                            120, 120, null);
                } else {
                    g.drawImage(runeAnimationGIF, cardObject.botHolderRune.getX(), cardObject.botHolderRune.getY(),
                            120, 120, null);
                }
            }
            secondCounter -= (820 / 16); // after 24th run (1sec) hurot na, wa ni gana
            // SOBRA GAMAY FRAME, sakto ang 820 / 16 as 1sec ka gif nga 12fps
            // secondCounter = -1;
        }
        // CHECK IF MO X IF BOT ANG PILDI
        // g.drawImage(X, cardObject.botHolderRune.getX(),
        // cardObject.botHolderRune.getY(), 120, 120, null);
    }

    // CHECK MUNA ANF EFFECT NI CARD
    // (SINCE NAAY CARDS NGA MO EFFECT DURING LOSE CONDTION AND SO ON)
    public void effectCardsChecker(MainCards player, MainCards bot) {
        // PARA DLI PER FRAME ANG EFFECT
        if (!playerCardEffectDone) {
            cardsEffectsObject.cardEffectValidator(player, bot, winnerName);
            playerCardEffectDone = true;
            // ibalik rani false after the round
        }
    } // redundant, but its fine para dili gubot sa draw function

    public void draw(Graphics g) {
        cardObject.draw(g, drawObject.playerOnHand, drawObject.botOnHand,
                drawObject.playerCardCount,
                drawObject.botCardCount);

        if (animationRuneCollideDone) {
            g.setFont(defaultFont.getBoldFontCustomSize(35));
            // DRAW RESULT TEXT AND RESULT ART EFFECTS (GIF/ARRAY OF IMG)
            String resultText = "";
            if (runeWinner == cardObject.activeRune) {
                resultText = "YOU WIN DUMBASS!";
            } else if (runeWinner == cardObject.botHolderRune) {
                resultText = "DUHH ALWAYS A LOSER!";
            } else {
                resultText = "IT'S ATAY :>";
            }

            resultRunes(g); // RESULT VISUAL DIRI

            // MESSAGE FOR THE RESULTS PLUS ACCURATE CENTER (FM)
            FontMetrics fm = g.getFontMetrics();
            int resultTextWidth = fm.stringWidth(resultText);

            g.drawString(resultText, (1200 / 2) - (resultTextWidth / 2), 700);

            // NAKASULOD, DRIA MAGPASA FOR CHECKING
            effectCardsChecker(cardObject.activeCard, cardObject.botHolderCard);
        }
    }

}