import java.awt.Graphics;
import java.awt.Color;

public class CardsEffects {

    PreparationPhase preparationPhaseObject;
    String winnerName;
    int winnerCardDuration, loserCardDuration;
    BattlePhase battlePhaseObject;
    DefaultFont defaultFont;
    VSBotPanel vsBotPanel;

    int opponentCurrentHp, ownCurrentHp;

    CardsEffects(VSBotPanel vsBotPanel) {
        preparationPhaseObject = vsBotPanel.preparationPhaseObject;
        winnerName = "tie";
        winnerCardDuration = 1000;
        loserCardDuration = 1000;
        defaultFont = new DefaultFont();
        opponentCurrentHp = 0;
        ownCurrentHp = 0;
    }

    public void passingObjects(PreparationPhase preparationPhaseObject,
            BattlePhase battlePhaseObject,
            VSBotPanel vsBotPanel) {
        // PARA ATONG E MANIPULATE KY KATONG EXISTING NGA PREPHASE
        // WHICH IS NAA DDTO TANAN, HP, ENERGY, CHUCHU
        this.vsBotPanel = vsBotPanel;
        this.preparationPhaseObject = preparationPhaseObject;
        this.battlePhaseObject = battlePhaseObject;
    }

    // GOLDEN RULE:
    // (MUCH EASIER SOLUTION BUT NOT RECOMMENDABLE, SINCE LIBOG IN LONG RUN)
    // IF !ISWINNER ANG CARD EFFECT (MEANS MO EFFECT PAG PILDI), SIYA SI OPPONENT
    // TAKE TOWER CARD AS EXAMPLE
    public void storeCardsEffects(MainCards card, Boolean isWinner) {
        System.out.println(card.getName() + " WINNER? " + isWinner); // potek d mosulod pag false si iswinner
        String loserName = "";

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
            // preparationPhaseObject.botCurrentHp = opponentCurrentHp;
            // preparationPhaseObject.playerCurrentHp = ownCurrentHp;
            preparationPhaseObject.botCurrentHp = opponentCurrentHp;
            preparationPhaseObject.playerCurrentHp = ownCurrentHp;
            vsBotPanel.playerCurrentHp = ownCurrentHp;
            vsBotPanel.botCurrentHp = opponentCurrentHp;
        } else {
            // preparationPhaseObject.playerCurrentHp = opponentCurrentHp;
            // preparationPhaseObject.botCurrentHp = ownCurrentHp;
            preparationPhaseObject.playerCurrentHp = opponentCurrentHp;
            preparationPhaseObject.botCurrentHp = ownCurrentHp;
            vsBotPanel.playerCurrentHp = opponentCurrentHp;
            vsBotPanel.botCurrentHp = ownCurrentHp;
        }
    }

    public String storeTextOfCardEffects(MainCards card) {
        String cardText = "";
        if (card.getName().equals("star")) {
            cardText = "heal 20";
        } else if (card.getName().equals("tower")) {
            cardText = "heal 10 and deal 10 damage"; // malamang d mogana ang \n
        } else if (card.getName().equals("devil")) {
            cardText = "deal 20 damage";
        }
        return cardText;
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

        if (winnerName.equals("tie")) {
            // System.out.println("NO DRAWING KAY TIE"); // WORKING // WORKING RA PAG 1ROUND
            drawTieCardEffect(g);
        }

        // si winner sa
        else if (isWinner) {
            if (winnerCardDuration > 0) {
                drawPerCardEffects(g, card, true);
                drawTextOfCardEffects(g, card, true);
                // IF ANG CARD KY FOR LOSER, FIX LOGIC
                winnerCardDuration -= 24;
            } else {
                // mosulod pani kas.a sa else before ma true ang bool
                storeCardsEffects(card, true);
                battlePhaseObject.winnerCardEffectDone = true;
                // KUNG SI BOT, CARD = MNULL,
                // PAG SI PLAYER, IYANG ACTIVECARDIMAGE = NULL
                if (winnerName.equals("player")) {
                    preparationPhaseObject.activeCard.setImgOfActiveCard(null);
                    preparationPhaseObject.holderCard.setImg(null);
                } else {
                    card.setImg(null);
                    preparationPhaseObject.botCardChoiceImage = null;
                }
            }
        }

        // si loser na
        else if (!isWinner) {
            if (loserCardDuration > 0) {
                drawPerCardEffects(g, card, false);
                drawTextOfCardEffects(g, card, false);
                // GANA NANI IF FOR LOSER NGA CARD
                loserCardDuration -= 24;
            } else {
                storeCardsEffects(card, false);
                battlePhaseObject.loserCardEffectDone = true;
                if (winnerName.equals("bot")) {
                    preparationPhaseObject.activeCard.setImgOfActiveCard(null);
                    preparationPhaseObject.holderCard.setImg(null);
                } else {
                    card.setImg(null);
                    preparationPhaseObject.botCardChoiceImage = null;
                }
            }
        }
        // MAG NULL ANG BOOLEAN ISWINNER IF TIE, THATS WHY ITS PERFECT AND DLI MO GANA
        // ANG LOSE EFFECTS SA CARD

        // DRIA MAG END ANG ROUND, MAO NI LAST RUN SA CODE DBA? oonga
        // System.out.println("DAW MAABOT BA DIRI EVEN NO CARDS GI PLAY ANG BOTH");
        if (battlePhaseObject.loserCardEffectDone) {
            // si loserduration ra basehan, naa mn dyuy loser and if tie, kani man ginagamit

            // if neither sailang hp ky below/equal to 0, then padayun
            if (!(vsBotPanel.botCurrentHp <= 0) && !(vsBotPanel.playerCurrentHp <= 0)) {
                vsBotPanel.roundChecker = true; // SHET WORKING
                // vsBotPanel.battling = false;
                vsBotPanel.roundContinue();
                System.out.println(vsBotPanel.botCurrentHp);
                System.out.println(vsBotPanel.playerCurrentHp);
                // IF DRI, MAWALA RA ANG CARDS AFTER EACH ROUND
                // drawPhaseObject.deleteUsedCard(preparationPhaseObject.playerCardIndex);
            } else {
                // stop the fps
                vsBotPanel.drawing = false;
            }
        }
    }

    public void drawPerCardEffects(Graphics g, MainCards card, Boolean isWinner) {
        // MainCards cardEffectVisual
        // if (winnerName.equals("player")) {
        // }

        if (!cardEffectChecker(card, isWinner)) {
            g.drawImage(battlePhaseObject.cardNoEffectAnimationGIF,
                    card.getX(), card.getY(),
                    // duha ka get height kay square dyud ni ang animation
                    card.getHeight(), card.getHeight(),
                    null);
        } else {
            g.drawImage(card.getImg(),
                    // MATHS MATHS MATHS (PARA SAME RATIO GHPON PAGDAKO SA PIC CARD)
                    // 92:151 = 1:1.64
                    card.getX() - 25, card.getY() - 42,
                    card.getWidth() + 50, card.getHeight() + 84,
                    null);
        }

    }

    public void drawTieCardEffect(Graphics g) {

        // instead magbuhat bag.o pede ra mogamit bisan asa s duha (win/lose)
        if (loserCardDuration > 0) {
            g.drawImage(battlePhaseObject.cardNoEffectAnimationGIF,
                    preparationPhaseObject.holderCard.getX(), preparationPhaseObject.holderCard.getY(),
                    // duha ka get height kay square dyud ni ang animation
                    preparationPhaseObject.holderCard.getHeight(), preparationPhaseObject.holderCard.getHeight(),
                    null);

            g.drawImage(battlePhaseObject.cardNoEffectAnimationGIF,
                    preparationPhaseObject.botHolderCard.getX(), preparationPhaseObject.botHolderCard.getY(),
                    // duha ka get height kay square dyud ni ang animation
                    preparationPhaseObject.botHolderCard.getHeight(), preparationPhaseObject.botHolderCard.getHeight(),
                    null);

            loserCardDuration -= 24;
        } else {
            preparationPhaseObject.activeCard.setImgOfActiveCard(null);
            preparationPhaseObject.holderCard.setImg(null);
            preparationPhaseObject.botHolderCard.setImg(null);
            preparationPhaseObject.botCardChoiceImage = null;
            battlePhaseObject.loserCardEffectDone = true;
            // kani ra ato gamiton pra if tie matarog ghpon ni, and mao rani pang move sa
            // round instead sa duration ni loser pud, maparehas gaina
        }
    }

    public void drawTextOfCardEffects(Graphics g, MainCards card, Boolean isWinner) {
        String effectLabel = "";
        if (cardEffectChecker(card, isWinner)) {
            // winnerName.equals("player") // TAE THIS ONE WONT MAKE SENSE IF LOSER EFFECT
            // THIS ONE WILL INDICATE IF KA PLAYER NGA CARD NISULOD OR NOT BWAHAHAH
            if (card.getY() == preparationPhaseObject.holderCard.getY()) {
                g.setColor(Color.decode("#C1B59F"));
                effectLabel = "player: " + storeTextOfCardEffects(card);
            } else {
                g.setColor(Color.decode("#9A4B3A"));
                effectLabel = "bot: " + storeTextOfCardEffects(card);
            }

            g.setFont(defaultFont.getLightFontCustomSize(20));
            // int textWidth = g.getFontMetrics().stringWidth(effectLabel);
            // g.drawString(effectLabel, ((1200 / 2) - (textWidth / 2) - 92), 410);
            g.drawString(effectLabel, (50 + 92 + 50), 410);
        }
    }

    // GOOD DRAFT
    // NOTE: CARD DURATION ANIMATION SHOULD BE ALL EQUAL,
    // LOSER W/O EFFECT, L W/ E, W W/O E, W W/ E

    // THESE TODOs FOR SCALABILITY
    // TODO:SOUND EFFECTS
    // TODO: PROPER ANIMATIONS
    // TODO: ENDLESS MODE - DONE BETA
    // TODO: PVP if possible sa swing
}
