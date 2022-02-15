package game.map;

import game.GamePanel;

import java.io.File;

public class World {

    private int maxWorldCol;
    private int maxWorldRow;

    private int worldWidth;
    private int worldHeight;

    private File worldFile;

    public World(GamePanel gp,int maxWorldRow,int maxWorldCol, File worldFile) {
        this.maxWorldCol = maxWorldCol;
        this.maxWorldRow = maxWorldRow;
        this.worldFile = worldFile;
        this.worldWidth = maxWorldRow * gp.getTileSize();
        this.worldHeight = maxWorldCol * gp.getTileSize();
    }

    public int getMaxWorldCol() {
        return maxWorldCol;
    }

    public int getMaxWorldRow() {
        return maxWorldRow;
    }

    public int getWorldWidth() {
        return worldWidth;
    }

    public int getWorldHeight() {
        return worldHeight;
    }

    public File getWorldFile() {
        return worldFile;
    }
}
