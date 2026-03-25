import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MenuFrame extends JFrame implements ActionListener {

    int width = 1200;
    int height = 800;

    FateOfCards FOCs = new FateOfCards();
    GuidePanel GP = new GuidePanel();
    ExitConfirmationPanel ECP = new ExitConfirmationPanel();

    DefaultFont defaultFont = new DefaultFont();
    DefaultButton casualButton = new DefaultButton("Casual", 250);
    DefaultButton competitiveButton = new DefaultButton("Competitive", 310);
    DefaultButton vsbotButton = new DefaultButton("VS Bot", 370);
    DefaultButton optionButton = new DefaultButton("Option", 440);
    DefaultButton guideButton = new DefaultButton("Guide", 500);
    DefaultButton exitButton = new DefaultButton("Exit", 560);

    MenuFrame() {

        setTitle("FC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        // kuhaon pa daay contentpane if frame e change bg
        getContentPane().setBackground(Color.BLACK);

        // CASUAL BUTTON SET UP
        // casualButton = new JButton();
        // casualButton.setFont(defaultFont.getFont());
        // casualButton.setText("Hi");
        // casualButton.setBounds(100, 500, 200, 50);
        // casualButton.addActionListener(this);
        // casualButton.setFocusable(false);
        // casualButton.setEnabled(true);

        // ADD PANELS TO TOGGLE SETVISIBLE BOOLEAN
        add(FOCs);
        FOCs.setVisible(false);
        add(GP);
        GP.setVisible(false);
        add(ECP);
        ECP.setVisible(false);

        // BUTTON LISTENER AND ADD
        casualButton.addActionListener(this);
        add(casualButton);
        competitiveButton.addActionListener(this);
        add(competitiveButton);
        vsbotButton.addActionListener(this);
        add(vsbotButton);
        optionButton.addActionListener(this);
        add(optionButton);
        guideButton.addActionListener(this);
        add(guideButton);
        exitButton.addActionListener(this);
        add(exitButton);
    }

    // kay mo show up if ma hover during render sa panel 10fps
    public void setButtonsVisibility(boolean b) {
        casualButton.setVisible(b);
        competitiveButton.setVisible(b);
        vsbotButton.setVisible(b);
        optionButton.setVisible(b);
        guideButton.setVisible(b);
        exitButton.setVisible(b);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // CASUAL
        if (e.getSource() == casualButton) {
            FOCs.setVisible(true);
            FOCs.requestFocus();
            FOCs.setFocusable(true);
            System.out.println("TRY BUTTON CASUAL");
            // DefaultButton.setVisible(false);
            setButtonsVisibility(false);
            // COMPE
        } else if (e.getSource() == competitiveButton) {
            System.out.println("Ranked");
            setButtonsVisibility(false);
            // VSBOT
        } else if (e.getSource() == vsbotButton) {
            System.out.println("VS BOT");
            setButtonsVisibility(false);
            // OPTION
        } else if (e.getSource() == optionButton) {
            System.out.println("Option");
            setButtonsVisibility(false);
            // GUIDE
        } else if (e.getSource() == guideButton) {
            System.out.println("Guide");
            setButtonsVisibility(false);
            GP.setVisible(true);
            // EXIT
        } else if (e.getSource() == exitButton) {
            System.out.println("Exited");
            ECP.setVisible(true);
            ECP.requestFocus();
            ECP.setFocusable(true);
            setButtonsVisibility(false);
            // this.setEnabled(false);
            // this.dispose();
        }
    }
}
