import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class menuFrame extends JFrame implements ActionListener {

    int width = 1200;
    int height = 800;

    FateOfCards Foc = new FateOfCards();
    DefaultFont defaultFont = new DefaultFont();
    DefaultButton casualButton = new DefaultButton("Try", 100);

    menuFrame() {

        setTitle("FoC");
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

        casualButton.addActionListener(this);
        add(casualButton);

        add(Foc);
        Foc.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == casualButton) {
            Foc.setVisible(true);
            Foc.requestFocus();
            System.out.println("TRY BUTTON CASUAL");
            casualButton.setVisible(false);
        }
    }
}
