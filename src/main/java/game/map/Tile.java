package game.map;

import game.Main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public enum Tile {

    INVALID("invalid.png",true,0),
    GRASS("grass.png",false,1),
    WATER("water.png",true,2),
    STONE("stone.png",true,3);


    private BufferedImage texture;
    private boolean collision;
    private int id;



    Tile(String texture,boolean collision,int id) {
        try {
            this.texture = ImageIO.read(Main.class.getResource("/Resources/Tiles/" + texture));
            this.collision = collision;
            this.id = id;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Tile getTileFromID(int id) {
        for (Tile tile : values()) {
            if (tile.id == id) return tile;
        }
        return Tile.INVALID;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public boolean isCollision() {
        return collision;
    }

    public int getId() {
        return id;
    }
}
