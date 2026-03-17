import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel implements ActionListener {

    int width = 700;
    int height = 900;

    JButton casualButton;

    FateOfCards Foc;

    Menu() {

        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        setBackground(Color.BLACK);
        // addMouseListener(this);

        casualButton = new JButton();

        casualButton.setFont(new Font("Arial", Font.BOLD, 24));
        casualButton.setText("Casual Game");
        casualButton.setFocusable(false);
        casualButton.addActionListener(this);
        casualButton.setEnabled(true);
        casualButton.setVisible(true);
        casualButton.setBackground(Color.BLACK);
        casualButton.setBorderPainted(false);
        // mag flick lang ang effect if clicked, nays ilhanan
        // casualButton.setContentAreaFilled(false);
        // goods no bg, but walay aftereffect if gi click
        // casualButton.setBackground(new Color(0, 0, 0, 0));
        // naay effect pag click but mag stay na pud in ato forever

        this.add(casualButton);

        Foc = new FateOfCards();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == casualButton) {
            System.out.println("Hi");
            this.add(Foc);
            Foc.setVisible(true);
        }
    }
}
