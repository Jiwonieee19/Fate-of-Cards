
// import java.awt.Color;
import java.awt.Image;

public class MainCards {
    // INITIALIZE / INSTANTIATE
    private int x, y, width, height;
    private Image img;
    // private Color standard = Color.WHITE;

    // CONSTRUCTORS
    MainCards(int x, int y, int width, int height, Image img) {
        this.setX(x);
        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);
        this.setImg(img);
    }

    // OOP YARNS
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Image getImg() {
        return this.img;
    }
}
