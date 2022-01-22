package game.entity;

import game.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    private boolean isMoving;

    public Player(int x, int y, int speed, BufferedImage texture) {
        super(x, y, speed, texture);
    }

    public Player(int x, int y, int speed, String texture) throws IOException {
        this(x, y, speed, ImageIO.read(Main.class.getResource("/Resources/" + texture)));
        this.isMoving = false;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }
}
