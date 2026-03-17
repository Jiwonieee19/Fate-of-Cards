import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class myFrame extends JFrame implements ActionListener {

    int width = 700;
    int height = 900;

    JButton casualButton;

    FateOfCards Foc = new FateOfCards();

    myFrame() {
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
        casualButton.setFocusable(false);
        casualButton.setEnabled(true);
        this.add(casualButton);

        add(Foc);
        Foc.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == casualButton) {
            Foc.requestFocus();
            Foc.setVisible(true);
        }
    }
}
