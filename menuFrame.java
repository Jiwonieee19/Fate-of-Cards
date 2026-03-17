import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class menuFrame extends JFrame implements ActionListener {

    int width = 700;
    int height = 900;

    JButton casualButton;

    FateOfCards Foc = new FateOfCards();

    menuFrame() {
        setTitle("FoC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setResizable(false);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);

        // Menu menu = new Menu();

        // FoC.requestFocus();
        // add(FoC);
        // pack();

        // menu.requestFocus();
        // add(menu);
        // pack();

        casualButton = new JButton();
        casualButton.setFont(new Font("Arial", Font.BOLD, 24));
        casualButton.setText("Hi");
        casualButton.setSize(25, 40);
        casualButton.addActionListener(this);
        casualButton.setFocusable(false);
        casualButton.setEnabled(true);
        this.add(casualButton);

        Foc.requestFocus();
        add(Foc);
        Foc.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == casualButton) {
            casualButton.setEnabled(false);
            casualButton.setVisible(false);
            Foc.setVisible(true);
            System.out.println("TRY BUTTON CASUAL");
        }
    }
}
