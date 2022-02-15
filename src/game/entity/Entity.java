package game.entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity{

    private int x,y;
    private Direction facingDirection;
    private int speed;

    private final Texture texture;
    protected final Rectangle hitbox;

    protected Entity(int x, int y, int speed, BufferedImage texture) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.texture = new Texture(texture);
        facingDirection = Direction.UP;
        this.hitbox = new Rectangle();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
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

    public Rectangle getHitbox() {
        return hitbox;
    }
}
