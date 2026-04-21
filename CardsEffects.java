public class CardsEffects {

    PreparationPhase preparationPhaseObject;
    String winnerName;

    CardsEffects() {
        preparationPhaseObject = new PreparationPhase();
        winnerName = "tie";
    }

    public void passingObjects(PreparationPhase preparationPhaseObject) {
        // PARA ATONG E MANIPULATE KY KATONG EXISTING NGA PREPHASE
        // WHICH IS NAA DDTO TANAN, HP, ENERGY, CHUCHU
        this.preparationPhaseObject = preparationPhaseObject;
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

    // TODO: SEQUENCE OF CARD EFFECTS (WINNER EFFECT FIRST THEN LOSER
    // (SO HEALS EFFECT MAKE SINCE))

    // CHECK BOTH CARD AND SEE ITS CONDITION (WIN/LOSE/EITHER)
    public void cardEffectValidator(MainCards player, MainCards bot, String winnerName) {

        this.winnerName = winnerName;

        if (winnerName.equals("player")) {
            // ANI DAPAT, PRA MA PLAY GIHAPON ANG CARD NI LOSER TO CHECK IF PASOK
            storeCardsEffects(player, true);
            storeCardsEffects(bot, false);

        } else if (winnerName.equals("bot")) {
            storeCardsEffects(bot, true);
            storeCardsEffects(player, false);
        } else {
            // DIRIA PADUNG IF WALAY DAOG, MEANS TIE, DILI NA MAGPASA PA IF TIE
            // PAG TIE, BOTH CARDS REKTA BURN
        }

    }
}
