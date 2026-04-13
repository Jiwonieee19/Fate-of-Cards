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

    public void tryy(MainCards player, MainCards bot) {
        if (player.getName().equals("tower")) {
            System.out.println("TOWER EFFECTS IN CUMMING");
        }
    }
}
