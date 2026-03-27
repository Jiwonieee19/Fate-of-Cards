import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VSBotPanel extends JPanel implements ActionListener {

    int width = 1200, height = 800, pixel = 20;

    PreparationPhase preparationPhaseObject;
    BattlePhase battlePhaseObject;

    Timer drawFPS;

    VSBotPanel() {
        setBounds(0, 0, width, height);
        setFocusable(true);
        setBackground(Color.RED);

        preparationPhaseObject = new PreparationPhase();
        battlePhaseObject = new BattlePhase();

        drawFPS = new Timer(1000 / 24, this); // 1000ms/24 means 24 frame per sec
        drawFPS.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}