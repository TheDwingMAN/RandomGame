package game.entity;

import java.awt.image.BufferedImage;

public enum Direction {
    UP,DOWN,LEFT,RIGHT;

    public static Animation getMovingAnimationForDirection(Texture texture,Direction direction) {
        if (direction == UP) return texture.getMovingUp();
        if (direction == DOWN) return texture.getMovingDown();
        if (direction == LEFT) return texture.getMovingLeft();
        if (direction == RIGHT) return texture.getMovingRight();
        return null;
    }

    public static Animation getStandingAnimationForDirection(Texture texture,Direction direction) {
        if (direction == UP) return texture.getStandingUp();
        if (direction == DOWN) return texture.getStandingDown();
        if (direction == LEFT) return texture.getStandingLeft();
        if (direction == RIGHT) return texture.getStandingRight();
        return null;
    }
}
