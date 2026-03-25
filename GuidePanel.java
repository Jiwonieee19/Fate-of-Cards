import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuidePanel extends JPanel implements ActionListener {

    int width = 1200, height = 800;

    MenuFrame menuOnlyFrame;

    DefaultButton backButton = new DefaultButton("Back", 50, 50);
    DefaultLabel draftLabel = new DefaultLabel("Inside here are the future game tuts",
            (width / 2) - 250,
            (height / 2) - 25,
            500);

    GuidePanel() {
        setBounds(0, 0, width, height);
        setBackground(Color.BLACK);
        setLayout(null);

        add(draftLabel);
        add(backButton);
        backButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            System.out.println("BACK TO HOME");
            this.setVisible(false);

            menuOnlyFrame = (MenuFrame) SwingUtilities.getWindowAncestor(this);
            if (menuOnlyFrame != null) {
                menuOnlyFrame.setButtonsVisibility(true);
            }
        }
    }

}
