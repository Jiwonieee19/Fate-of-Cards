import java.awt.Graphics;

public class CardsEffects {

    PreparationPhase preparationPhaseObject;
    String winnerName;
    int winnerCardDuration, loserCardDuration;
    BattlePhase battlePhaseObject;

    CardsEffects() {
        preparationPhaseObject = new PreparationPhase();
        winnerName = "tie";
        winnerCardDuration = 5000;
        loserCardDuration = 5000;
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

    public void cardEffectCheckerAndVisual(MainCards card, Boolean isWinner, Graphics g) {
        // si winner sa
        if (isWinner) {
            if (winnerCardDuration > 0) {
                drawPerCardEffects(g, "winner");
                winnerCardDuration -= 1000 / 24;
            } else {
                storeCardsEffects(card, true);
                battlePhaseObject.winnerCardEffectDone = true;
            }
        }

        // si loser na
        else if (!isWinner) {
            if (loserCardDuration > 0) {
                drawPerCardEffects(g, "loser");
                loserCardDuration -= 1000 / 24;
            } else {
                storeCardsEffects(card, true);
                battlePhaseObject.loserCardEffectDone = true;
            }
        }
    }

    public void drawPerCardEffects(Graphics g, String whos) {
        // MainCards cardEffectVisual
        // if (winnerName.equals("player")) {

        // }
        g.drawString("null" + whos, 50, 50);
    }
}
