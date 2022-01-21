package game.entity;

import java.awt.image.BufferedImage;

public class Entity {


    private Direction facingDirection;
    private int x,y;
    private int speed;

    private final Texture texture;

    protected Entity(int x, int y, int speed, BufferedImage texture) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.texture = new Texture(texture);
        facingDirection = Direction.UP;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public Texture getTexture() {
        return texture;
    }

    public Direction getFacingDirection() {
        return facingDirection;
    }

    public void setFacingDirection(Direction facingDirection) {
        this.facingDirection = facingDirection;
    }
}
