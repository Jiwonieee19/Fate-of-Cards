import java.awt.Graphics;

public class CardsEffects {

    PreparationPhase preparationPhaseObject;
    String winnerName;
    int winnerCardDuration, loserCardDuration;
    BattlePhase battlePhaseObject;

    CardsEffects() {
        preparationPhaseObject = new PreparationPhase();
        winnerName = "tie";
        winnerCardDuration = 1000;
        loserCardDuration = 1000;
    }

    public void passingObjects(PreparationPhase preparationPhaseObject, BattlePhase battlePhaseObject) {
        // PARA ATONG E MANIPULATE KY KATONG EXISTING NGA PREPHASE
        // WHICH IS NAA DDTO TANAN, HP, ENERGY, CHUCHU
        this.preparationPhaseObject = preparationPhaseObject;
        this.battlePhaseObject = battlePhaseObject;
    }

    // GOLDEN RULE:
    // (MUCH EASIER SOLUTION BUT NOT RECOMMENDABLE, SINCE LIBOG IN LONG RUN)
    // IF !ISWINNER ANG CARD EFFECT (MEANS MO EFFECT PAG PILDI), SIYA SI OPPONENT
    // TAKE TOWER CARD AS EXAMPLE
    public void storeCardsEffects(MainCards card, Boolean isWinner) {

        String loserName = "";
        int opponentCurrentHp = 0, ownCurrentHp = 0;

        // PARA DLI MAPUNO IF ELSE, AND ISA RA KA FUNCTION ANG EFFECTS

        // E STORE SA VARAIBLE ILA CURRENT NUMBERS OF SHITS
        if (this.winnerName.equals("player")) {
            loserName = "bot";
            opponentCurrentHp = preparationPhaseObject.botCurrentHp;
            ownCurrentHp = preparationPhaseObject.playerCurrentHp;
        } else {
            loserName = "player";
            opponentCurrentHp = preparationPhaseObject.playerCurrentHp;
            ownCurrentHp = preparationPhaseObject.botCurrentHp;
        }

        // STORE ANG CARD EFFECT PLUS WHEN MO EFFECT (WIN/LOSE CONDITION)
        // THEN ISA2 PAG CALL BOTH NI PLAYER AND BOT

        // STAR
        if (card.getName().equals("star") && isWinner) {
            // System.out.println("star == star"); // NAKASULOD
            ownCurrentHp += 20;
            if (ownCurrentHp > 200) {
                ownCurrentHp = 200;
            } // para ang heal d malapas sa max hp
            System.out.println(winnerName + ": HEALED 20");
        }
        // TOWER
        else if (card.getName().equals("tower") && !isWinner) {
            // WORKING, PERO PER FRAME MAG MINUS BHWHAHAH // fixed
            // DRAFT FIX, ACTUALLY WORKING,
            // TO THINK NGA IF DLI KA WINNER (!isWinner) THEN IKAW SI OPPONENT: ANA LANG
            // PERO LIBOG NI LONG RUN, SO CLEANUP NLNG IF ELSE SA TAAS
            // KANI NLNG SA, MAS DALI FOR ME
            opponentCurrentHp += 10;
            if (opponentCurrentHp > 200) {
                opponentCurrentHp = 200;
            } // para ang heal d malapas sa max hp
            ownCurrentHp -= 10;
            System.out.println(loserName + ": HEALED 10 AND DAMAGED DEALT 10");
            // AHHHHH THATS WHY, KAY SI OPPONNENT KAY AS OF NOW SI BOT,
            // KAY NA SET UP DDTOAS VARIABLES FOR WINNER RA BWHAHAHA GETS
        }
        // DEVIL
        else if (card.getName().equals("devil") && isWinner) {
            opponentCurrentHp -= 20;
            System.out.println(winnerName + ": DAMAGED DEALT 20");
        }

        // AFTER MA STORE, I PASA SA ORIGINAL TO CHANGE IT,
        // THIS SOLUTION MAKES IT SIMPLIER FOR BOTH PLAYER KESA NAKA LAHI2 ILANG
        // FUNCTION
        if (this.winnerName.equals("player")) {
            preparationPhaseObject.botCurrentHp = opponentCurrentHp;
            preparationPhaseObject.playerCurrentHp = ownCurrentHp;
        } else {
            preparationPhaseObject.playerCurrentHp = opponentCurrentHp;
            preparationPhaseObject.botCurrentHp = ownCurrentHp;
        }
    }

    public boolean cardEffectChecker(MainCards card, Boolean isWinner) {
        if (card.getName().equals("star") && isWinner)
            return true;
        else if (card.getName().equals("tower") && !isWinner)
            return true;
        else if (card.getName().equals("devil") && isWinner)
            return true;
        else
            return false;
    }

    public void cardEffectResultAndVisual(MainCards card, Boolean isWinner, Graphics g) {
        // System.out.println(card.getName()); // HOLDER INSTEAD ACTIVE, BUT DONE FIXED

        // si winner sa
        if (isWinner) {
            if (winnerCardDuration > 0) {
                drawPerCardEffects(g, card, true);
                winnerCardDuration -= 50;
            } else {
                // mosulod pani kas.a sa else before ma true ang bool
                storeCardsEffects(card, true);
                battlePhaseObject.winnerCardEffectDone = true;
            }
        }

        // si loser na
        else if (!isWinner) {
            if (loserCardDuration > 0) {
                drawPerCardEffects(g, card, false);
                loserCardDuration -= 50;
            } else {
                storeCardsEffects(card, false);
                battlePhaseObject.loserCardEffectDone = true;
            }
        }
    }

    public void drawPerCardEffects(Graphics g, MainCards card, Boolean isWinner) {
        // MainCards cardEffectVisual
        // if (winnerName.equals("player")) {

        // }
        // if (!cardEffectChecker(card, isWinner)) {
        // g.drawImage(battlePhaseObject.cardNoEffectAnimationGIF,
        // card.getX(), card.getY(),
        // // duha ka get height kay square dyud ni ang animation
        // card.getHeight(), card.getHeight(),
        // null);
        // } else {
        g.drawImage(card.getImg(),
                card.getX() - 50, card.getY() - 50,
                card.getWidth() + 100, card.getHeight() + 100,
                null);
        // }
        g.drawString("null", 50, 50);

    }

    // GOOD DRAFT
    // NOTE: CARD DURATION ANIMATION SHOULD BE ALL EQUAL,
    // LOSER W/O EFFECT, L W/ E, W W/O E, W W/ E

    // TODO: AFTER EFFECT FOR CARD AND RUNE, DISAPPEAR THOSE (BLACK IMG/NULL)
}
