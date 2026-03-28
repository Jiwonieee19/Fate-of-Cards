import java.awt.Image;

public class MainRunes {
    // INITIALIZE / INSTANTIATE
    private String name;
    private int x, y, width, height;
    private Image image;

    // CONSTRUCTOR
    MainRunes(String name, int x, int y, int width, int height, Image image) {
        setName(name);
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
        setImage(image);
    }

    public void adjustRunesVisual(int y) {
        this.setY(y);
    }

    // OOP KUNO
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

    public void setImage(Image image) {
        this.image = image;
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

    public Image getImage() {
        return this.image;
    }
}
