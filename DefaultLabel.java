import java.awt.Color;

import javax.swing.JLabel;

public class DefaultLabel extends JLabel {

    private String text;
    private int x, y, width;

    DefaultFont defaultFont = new DefaultFont();

    DefaultLabel(String text, int x, int y, int width) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        // DEFAULT LABEL SET UP
        this.setFont(defaultFont.getLightFont());
        this.setText(this.text);
        this.setBounds(this.x, this.y, this.width, 50);
        this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);
    }

}
