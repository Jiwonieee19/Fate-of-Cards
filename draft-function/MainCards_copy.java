
// import java.awt.Color;
import java.awt.Image;

public class MainCards_copy {
    // INITIALIZE / INSTANTIATE
    private String name;
    private int x, y, width, height;
    private Image img;
    // private Color standard = Color.WHITE;

    // CONSTRUCTORS
    MainCards_copy(String name, int x, int y, int width, int height, Image img) {
        this.setName(name);
        this.setX(x);
        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);
        this.setImg(img);
    }

    public void setAllCardsDetails(int x, int y, int width, int height) {
        // this.setName(name);
        this.setX(x);
        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);
        // this.setImg(img);
    }

    // OOP YARNS
    public void setName(String name) {
        this.name = name;
    }

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

    public String getName() {
        return this.name;
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
