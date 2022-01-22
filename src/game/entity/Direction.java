package game.entity;

import java.awt.image.BufferedImage;

public enum Direction {
    UP,DOWN,LEFT,RIGHT;

    public static BufferedImage[] getImageForDirection(Texture texture,Direction direction) {
        if (direction == UP) return texture.getMovingUp();
        if (direction == DOWN) return texture.getMovingDown();
        if (direction == LEFT) return texture.getMovingLeft();
        if (direction == RIGHT) return texture.getMovingRight();
        return null;
    }
}
