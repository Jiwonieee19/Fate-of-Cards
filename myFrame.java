import javax.swing.*;

public class myFrame extends JFrame {

    int width = 100;
    int height = 100;

    myFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setResizable(false);

        FateOfCards FoC = new FateOfCards();
        Menu menu = new Menu();
        FoC.requestFocus();
        this.add(FoC);
        this.pack();
        this.setLocationRelativeTo(null);
    }
}
