package game.map;

import game.entity.GamePanel;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.invoke.StringConcatFactory;
import java.util.Arrays;
import java.util.Scanner;

public class TileManager {

    private GamePanel gp;

    private Tile[][] worldTiles;

    public TileManager(GamePanel gp) {
        this.gp = gp;
    }

    public void loadWorld(World world) {
        File file = world.getWorldFile();
        worldTiles = new Tile[world.getMaxWorldCol()][world.getMaxWorldRow()];


        if (!file.exists()) return;
        try {
            Scanner myReader = new Scanner(file);
            int y = 0;
            while (myReader.hasNext()) {
                int x = 0;
                String data = myReader.nextLine();
                for (String str : data.split("")) {
                    Tile tile;
                    try {
                        Integer id = Integer.valueOf(str);
                        tile = Tile.getTileFromID(id);
                    } catch (Exception ex) {
                        tile = Tile.INVALID;
                    }

                    if (x >= world.getMaxWorldRow()) break;
                    worldTiles[y][x] = tile;
                    x++;
                }
                if (y >= world.getMaxWorldCol()) break;
                y++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    /*
    public void drawMap(Graphics2D g2) {

        for (int y = 0;y < worldTiles.length;y++) {
            for (int x = 0; x < worldTiles[0].length;x++) {
                drawTileAt(g2,x * gp.getTileSize(),y * gp.getTileSize(),worldTiles[y][x]);
                System.out.println(x+ " " + y);
            }
        }
    }*/

    public void drawMap(Graphics2D g2) {

        for (int worldCol = 0; worldCol < worldTiles.length;worldCol++) {
            for (int worldRow = 0; worldRow < worldTiles[0].length;worldRow++) {
                Tile tile = worldTiles[worldCol][worldRow];

                int worldX = worldRow * gp.getTileSize();
                int worldY = worldCol * gp.getTileSize();
                int screenX = worldX - gp.getPlayer().getX() + gp.getScreenX();
                int screenY = worldY - gp.getPlayer().getY() + gp.getScreenY();

                if (worldX + gp.getTileSize() > gp.getPlayer().getX() - gp.getScreenX() &&
                    worldX - gp.getTileSize() < gp.getPlayer().getX() + gp.getScreenX() &&
                    worldY + gp.getTileSize() > gp.getPlayer().getY() - gp.getScreenY() &&
                    worldY - gp.getTileSize() < gp.getPlayer().getY() + gp.getScreenY()) {
                    drawTileAt(g2,screenX,screenY,tile);
                }
            }
        }



    }

    public void drawTileAt(Graphics2D g2,int x,int y, Tile tile) {
        g2.drawImage(tile.getTexture(),x,y,gp.getTileSize(),gp.getTileSize(),null);
    }

}
