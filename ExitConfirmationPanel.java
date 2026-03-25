import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitConfirmationPanel extends JPanel implements ActionListener {

    int width = 600, height = 400, margin = 100;

    // PARA MAKUHA SI PARENT, AND THEN MA ACCESS SIYA THROUGH
    // INSTANCE OF JFRAME FROM PARENT COMPONENT
    Component parentComponent = this.getParent();
    menuFrame parentFrame = (menuFrame) parentComponent;
    // MAS DALI RANI, NO NEED INSTANCE SA COMPONENT PARENT
    // menuFrame parentFrame = (menuFrame) SwingUtilities.getWindowAncestor(this);

    // ANI RA DAAY, ANCESTOR AND PARENTS ARE NOT THE SAME
    // PARENTS SI EXTEND, ANCESTOR SI NAG ADD NIMO
    Window window;
    menuFrame menuOnlyFrame;
    // GETS NA NAKO UNSAON PAG UTILIZE SA SWINGUTILITIES

    DefaultButton yesButton = new DefaultButton("Yes", 0 + margin, 200);
    DefaultButton noButton = new DefaultButton("No", width - (margin * 2), 200);
    DefaultLabel exitLabel = new DefaultLabel("Are U Sure U Want to Quit, quitter?",
            (width / 2) - 500 / 2,
            200 - margin,
            500);

    ExitConfirmationPanel() {
        setBounds(width / 2, height / 2, width, height);
        setBackground(Color.BLACK);
        setLayout(null);

        add(exitLabel);
        add(yesButton);
        yesButton.addActionListener(this);
        add(noButton);
        noButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == yesButton) {
            System.out.println("KASULOD SA YES");
            // d mo gana if sa instantiation ibutang
            window = SwingUtilities.getWindowAncestor(this);
            if (window != null) {
                window.dispose();
                // Releases all resources and closes the window
            }
        } else if (e.getSource() == noButton) {
            this.setVisible(false);
            // waw, window ang e retrieve but with () makuha ang any compo
            menuOnlyFrame = (menuFrame) SwingUtilities.getWindowAncestor(this);

            // pede walay if, but to make it not crash if ever, then do if
            if (menuOnlyFrame != null) {
                menuOnlyFrame.setButtonsVisibility(true);
            }
        }
    }

}

// NEW LEARNINGS: INSTEAD NGA PA CREATE2 KAG MULTI-FRAME, WHICH IS NANGITA KOG
// WAYS NGA ANG OG FRAME LANG DYUD GAMITON, SWINGUTILITIES FUNCTION CAME ON AND
// LEARNED IT