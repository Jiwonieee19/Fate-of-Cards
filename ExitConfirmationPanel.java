import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitConfirmationPanel extends JPanel implements ActionListener {

    int width = 600, height = 400;

    DefaultButton yesButton = new DefaultButton("Yes", width * (1 / 4), 300);
    DefaultButton noButton = new DefaultButton("No", width + (3 / 4), 300);

    ExitConfirmationPanel() {
        setBounds(width / 2, height / 2, width, height);
        setBackground(Color.BLUE);

        add(yesButton);
        add(noButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
