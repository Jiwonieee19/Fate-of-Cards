public class CardsEffects {

    PreparationPhase preparationPhaseObject;

    CardsEffects() {
        preparationPhaseObject = new PreparationPhase();
    }

    public void PassingObjects(PreparationPhase preparationPhaseObject) {
        // PARA ATONG E MANIPULATE KY KATONG EXISTING NGA PREPHASE
        // WHICH IS NAA DDTO TANAN, HP, ENERGY, CHUCHU
        this.preparationPhaseObject = preparationPhaseObject;
    }

    public void storeCardsEffects(MainCards card, Boolean isWinner) {

        // STORE ANG CARD EFFECT PLUS WHEN MO EFFECT (WIN/LOSE CONDITION)
        // THEN ISA2 PAG CALL BOTH NI PLAYER AND BOT

        // STAR
        if (card.getName().equals("star") && isWinner) {
            // System.out.println("star == star"); // NAKASULOD
            preparationPhaseObject.playerCurrentHp += 20;
            System.out.println("HEALED 20");
        }
        // TOWER
        else if (card.getName().equals("tower") && !isWinner) {
            // WORKING, PERO PER FRAME MAG MINUS BHWHAHAH // fixed
            preparationPhaseObject.botCurrentHp -= 10;
            preparationPhaseObject.playerCurrentHp += 10;
            System.out.println("HEALED 10 AND DAMAGED DEALT 10");
        }
        // DEVIL
        else if (card.getName().equals("devil") && isWinner) {
            preparationPhaseObject.botCurrentHp -= 20;
            System.out.println("DAMAGED DEALT 20");
        }
    }

    // TODO: PLAYABLE WHEN CARDEFFECTVALIDATOR WORKS/DONE

    // TODO: SEQUENCE OF CARD EFFECTS (WINNER EFFECT FIRST THEN LOSER
    // (SO HEALS EFFECT MAKE SINCE))

    // TODO: CLEAN/REPHRASE SYSOUTS OF ALL FILE TO UNIFORM AND UNDERSTAND BETTER

    // CHECK BOTH CARD AND SEE ITS CONDITION (WIN/LOSE/EITHER)
    public void cardEffectValidator(MainCards player, MainCards bot, String winnerName) {
        if (winnerName.equals("player")) {
            // ANI DAPAT, PRA MA PLAY GIHAPON ANG CARD NI LOSER TO CHECK IF PASOK
            storeCardsEffects(player, true);
            storeCardsEffects(bot, false);

        } else if (winnerName.equals("bot")) {
            storeCardsEffects(bot, true);
            storeCardsEffects(player, false);
        } else {
            // DIRIA PADUNG IF WALAY DAOG, MEANS TIE
            // PAG TIE, EITHER CARDS REKTA BURN
        }

    }
}
