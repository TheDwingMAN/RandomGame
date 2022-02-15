package game.font;

import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Font {

    private String name;
    private static String fontSequence = "!" + '"' + "#$%&" + "'" + "[]*+,-./0123456789:;<=>?@ABCDEFGHIJKLMOPQRSTUVWXYZ[\\]^_";
    private BufferedImage font;
    private HashMap<String,BufferedImage> keys;

    private int width,height;

    public Font(String name,BufferedImage font,int width,int height) {
        this.name = name;
        this.font = font;
        this.width = width;
        this.height = height;
        this.keys = new HashMap<>();

    }

    public boolean hasKey(String key) {
        key = key.split("")[0];

        for (int i = 0; i < fontSequence.split("").length; i++) {
            if (fontSequence.split("")[i].equalsIgnoreCase(key)) return true;
        }
        return false;
    }

    private void loadKeys() {
        int counter = 0;
        for (String key:fontSequence.split("")) {
            keys.put(key,font.getSubimage(counter * width,0,width,height));
        }
    }

    public BufferedImage getFontKey(String key) {
        key = key.split("")[0];
        return keys.get(key);
    }

    public String getName() {
        return name;
    }

    public BufferedImage getFont() {
        return font;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
