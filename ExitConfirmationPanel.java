import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitConfirmationPanel extends JPanel implements ActionListener {

    int width = 600, height = 400, margin = 100;

    DefaultButton yesButton = new DefaultButton("Yes", 0 + margin, 250);
    DefaultButton noButton = new DefaultButton("No", width - (margin * 2), 250);
    DefaultLabel exitLabel = new DefaultLabel("Are U Sure U Want to Quit, quitter?",
            (width / 2) - 500 / 2,
            250 - margin,
            500);

    ExitConfirmationPanel() {
        setBounds(width / 2, height / 2, width, height);
        setBackground(Color.BLUE);
        setLayout(null);

        add(exitLabel);
        add(yesButton);
        add(noButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
