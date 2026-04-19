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

    // GENERAL CARD EFFECT VARIETY

    // 1 energycost type has balanced effect for both win/lose
    // since its still 50% to achieve such condition

    // 1 energy cost for win/lose card (addvantage effect but condtionally)
    // 2 energy cost for regardless (either win or lose, takes minor/plain effect)
    // 3 energy cost for flexible (both win & lose had diff advantage effect)

    // FIRST PATCHNOTE
    // OVERVIEW:
    // 3 cards pa, star, tower, devil
    // all 3 cards are 1 energycost type

    // CARDLIST:
    // star, win: heals 20 hp
    // tower, lose: deals 10 dmg and heals 10 hp
    // devil, win: deals 20 dmg

    public void storeCardsEffects(MainCards card, Boolean runeWinner) {

        // STORE ANG CARD EFFECT PLUS WHEN MO EFFECT (WIN/LOSE CONDITION)
        // THEN ISA2 PAG CALL BOTH NI PLAYER AND BOT

        // STAR
        if (card.getName().equals("star") && runeWinner) {
            // System.out.println("star == star"); // NAKASULOD
            preparationPhaseObject.playerCurrentHp += 20;
            System.out.println("HEALED 20");
        }
        // TOWER
        else if (card.getName().equals("tower") && !runeWinner) {
            // WORKING, PERO PER FRAME MAG MINUS BHWHAHAH // fixed
            preparationPhaseObject.botCurrentHp -= 10;
            preparationPhaseObject.playerCurrentHp += 10;
            System.out.println("HEALED 10 AND DAMAGED DEALT 10");
        }
        // DEVIL
        else if (card.getName().equals("devil") && runeWinner) {
            preparationPhaseObject.botCurrentHp -= 20;
            System.out.println("DAMAGED DEALT 20");
        }
    }

    // TODO: SEPARATE PATCHNOTE AS TEXTFILE

    // TODO: PLAYABLE WHEN CARDEFFECTVALIDATOR WORKS/DONE

    // TODO: ENERGY COST LOGIC

    // TODO: SEQUENCE OF CARD EFFECTS (WINNER EFFECT FIRST THEN LOSER
    // (SO HEALS EFFECT MAKE SINCE))

    // TODO: CLEAN/REPHRASE SYSOUTS OF ALL FILE TO UNIFORM AND UNDERSTAND BETTER

    // CHECK BOTH CARD AND SEE ITS CONDITION (WIN/LOSE/EITHER)
    public void cardEffectValidator(MainCards player, MainCards bot, MainRunes runeWinner) {
        // TODO: Implement function
    }
}
