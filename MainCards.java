import java.awt.Color;
import java.awt.Image;

public class MainCards {
    int x, y, width, height;
    Image img;
    Color standard = Color.WHITE;

    MainCards(int x, int y, int width, int height, Image img) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.img = img;
    }
}
