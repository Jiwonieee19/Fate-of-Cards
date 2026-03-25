import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class DefaultButton extends JButton {

    private String text;
    private int y, x;

    DefaultFont defaultFont = new DefaultFont();

    DefaultButton(String text, int y) {
        this.text = text;
        this.y = y;
        // MENU BUTTON SET UP
        this.setFont(defaultFont.getBoldFont());
        this.setText(this.text);
        this.setBounds(70, this.y, 220, 50);
        this.setFocusable(false);
        this.setBorderPainted(false);
        this.setEnabled(true);
        this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);
        this.setHorizontalAlignment(SwingConstants.LEFT);
    }

    // public void setButton(String text, int y) {
    // this.text = text;
    // this.y = y;
    // }

    // public JButton getButton() {
    // return this;
    // }

    DefaultButton(String text, int x, int y) {
        this.text = text;
        this.x = x;
        this.y = y;
        // DEFAULT BUTTON SET UP
        this.setFont(defaultFont.getBoldFont());
        this.setText(this.text);
        this.setBounds(this.x, this.y, 100, 50);
        this.setFocusable(false);
        this.setBorderPainted(false);
        this.setEnabled(true);
        this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);
    }
}
