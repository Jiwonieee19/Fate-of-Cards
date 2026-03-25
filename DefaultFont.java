import java.awt.Font;
import java.awt.GraphicsEnvironment;

public class DefaultFont {

    Font cormorantBoldFont;
    Font cormorantLightFont;

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

        // LOAD CUSTOM REG FONT
        try

        {
            cormorantLightFont = Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("/assets/fonts/CormorantGaramond-Light.ttf"))
                    .deriveFont(34f);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(cormorantLightFont);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Font getBoldFont() {
        return cormorantBoldFont;
    }

    public Font getRegFont() {
        return cormorantLightFont;
    }
}
