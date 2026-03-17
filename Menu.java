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

    JButton butt;

    Menu() {

        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        setBackground(Color.BLACK);
        // addMouseListener(this);

        butt = new JButton();

        butt.setFont(new Font("Arial", Font.BOLD, 24));
        butt.setText("Casual Game");
        butt.setFocusable(false);
        butt.addActionListener(this);
        butt.setEnabled(true);
        butt.setVisible(true);
        butt.setBackground(Color.BLACK);
        butt.setBorderPainted(false);

        this.add(butt);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == butt) {
            System.out.println("Hi");
        }
    }
}
