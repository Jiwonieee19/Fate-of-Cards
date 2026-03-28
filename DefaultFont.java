import java.awt.Font;
import java.awt.GraphicsEnvironment;

public class DefaultFont {

    Font cormorantBoldFont;
    Font cormorantLightFont;
    Font cormorantBoldFontCustomSize;
    Font cormorantLightFontCustomSize;

    float size;

    DefaultFont() {
        // LOAD CUSTOM FONT (BOLD)
        try {
            cormorantBoldFont = Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("/assets/fonts/CormorantGaramond-Bold.ttf"))
                    .deriveFont(34f);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(cormorantBoldFont);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // LOAD CUSTOM FONT (LIGHT)
        try {
            cormorantLightFont = Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("/assets/fonts/CormorantGaramond-Light.ttf"))
                    .deriveFont(30f);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(cormorantLightFont);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadBoldCustomSize() {
        // CUSTOM SIZE BOLD FONT
        try {
            cormorantBoldFontCustomSize = Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("/assets/fonts/CormorantGaramond-Bold.ttf"))
                    .deriveFont(this.size);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(cormorantBoldFontCustomSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadLightCustomSize() {
        // CUSTOM SIZE LIGHT FONT
        try {
            cormorantLightFontCustomSize = Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("/assets/fonts/CormorantGaramond-Light.ttf"))
                    .deriveFont(this.size);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(cormorantLightFontCustomSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Font getBoldFont() {
        return cormorantBoldFont;
    }

    public Font getLightFont() {
        return cormorantLightFont;
    }

    public Font getBoldFontCustomSize(int size) {
        this.size = size;
        loadBoldCustomSize();
        return cormorantBoldFontCustomSize;
    }

    public Font getLightFontCustomSize(int size) {
        this.size = size;
        loadLightCustomSize();
        return cormorantLightFontCustomSize;
    }
}
