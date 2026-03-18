import javax.swing.JButton;

public class DefaultButton extends JButton {

    private String text;
    private int y;

    DefaultFont defaultFont = new DefaultFont();

    DefaultButton(String text, int y) {
        this.text = text;
        this.y = y;
        // MENU BUTTON SET UP
        this.setFont(defaultFont.getFont());
        this.setText(this.text);
        this.setBounds(100, this.y, 200, 50);
        this.setFocusable(false);
        this.setEnabled(true);
    }

    // public void setButton(String text, int y) {
    // this.text = text;
    // this.y = y;
    // }

    // public JButton getButton() {
    // return this;
    // }
    // cc
}
