import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class menuFrame extends JFrame implements ActionListener {

    int width = 1200;
    int height = 800;

    JButton casualButton;

    FateOfCards Foc = new FateOfCards();

    Font cormorantDefaultFont;

    menuFrame() {

        // LOAD CUSTOM FONT
        try {
            cormorantDefaultFont = Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("/assets/fonts/CormorantGaramond-Bold.ttf"))
                    .deriveFont(40f);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(cormorantDefaultFont);

        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("FoC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setBackground(Color.BLACK);

        // CASUAL BUTTON SET UP
        casualButton = new JButton();
        casualButton.setFont(cormorantDefaultFont);
        casualButton.setText("Hi");
        casualButton.setBounds(100, 500, 200, 50);
        casualButton.addActionListener(this);
        casualButton.setFocusable(false);
        casualButton.setEnabled(true);
        casualButton.setAlignmentX(BOTTOM_ALIGNMENT);
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
