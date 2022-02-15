package game.font;

import game.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FontManager {

    private GamePanel gp;
    private List<Font> fonts;

    public FontManager(GamePanel gp) {
        this.gp = gp;
        this.fonts = new ArrayList<>();
    }

    public void loadFont(Font font) {
        fonts.add(font);
    }

    public Font getFontFromName(String name) {
        for (Font font: fonts) {
            if (font.getName().equalsIgnoreCase(name)) return font;
        }
        return null;
    }

    public void drawFontAt(String text, int x, int y, int width, int height, Font font, Graphics2D g2) {
        int counter = 0;
        for (String key : text.split("")) {
            g2.drawImage(font.getFontKey(key),x + width * counter,y,width,height,null);
            counter++;
        }
    }
}
