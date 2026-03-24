import java.awt.Font;
import java.awt.GraphicsEnvironment;

public class DefaultFont {

    Font cormorantFont;

    DefaultFont() {
        // LOAD CUSTOM FONT
        try {
            cormorantFont = Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("/assets/fonts/CormorantGaramond-Bold.ttf"))
                    .deriveFont(34f);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(cormorantFont);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Font getFont() {
        return cormorantFont;
    }
}
