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

    public void storeCardsEffects(MainCards card, Boolean runeWinner) {

        // STORE ANG CARD EFFECT PLUS WHEN MO EFFECT (WIN/LOSE CONDITION)
        // THEN ISA2 PAG CALL BOTH NI PLAYER AND BOT

        // STAR
        if (card.getName().equals("star") && runeWinner) {
            System.out.println("star == star"); // NAKASULOD
        }
        // TOWER
        else if (card.getName().equals("tower")) {
            // WORKING, PERO PER FRAME MAG MINUS BHWHAHAH // fixed
            preparationPhaseObject.botCurrentHp -= 20;
        }
        // DEVIL
        else if (card.getName().equals("devil")) {
        }
    }
}
