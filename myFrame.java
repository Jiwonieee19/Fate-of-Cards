import javax.swing.*;

public class myFrame extends JFrame {

    int width = 100;
    int height = 100;

    myFrame() {
        setTitle("FoC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setResizable(false);

        FateOfCards FoC = new FateOfCards();
        // Menu menu = new Menu();
        FoC.requestFocus();
        add(FoC);
        pack();
        setLocationRelativeTo(null);
    }
}
