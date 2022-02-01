package game.entity;

import java.awt.image.BufferedImage;

public class Texture {

    public static int w = 16, h = 24; // TEXTURES WIDTH AND LENGTH

    private Animation standingUp,standingDown,standingRight,standingLeft;

    private Animation movingUp,movingDown,movingRight,movingLeft;

    public Texture(BufferedImage image) {
        standingRight = new Animation(image.getSubimage(0,8,w,h));
        standingUp = new Animation(image.getSubimage(16,8,w,h));
        standingLeft = new Animation(image.getSubimage(32,8,w,h));
        standingDown = new Animation(image.getSubimage(48,8,w,h));

        BufferedImage[] movingRight = new BufferedImage[6];
        for (int i = 0; i < 6; i++) {
            movingRight[i] = image.getSubimage(i * 16,72,w,h);
        }
        this.movingRight = new Animation(movingRight);

        BufferedImage[] movingUp = new BufferedImage[6];
        for (int i = 0; i < 6; i++) {
            movingUp[i] = image.getSubimage(i * 16 + 96,72,w,h);
        }
        this.movingUp = new Animation(movingUp);

        BufferedImage[] movingLeft = new BufferedImage[6];
        for (int i = 0; i < 6; i++) {
            movingLeft[i] = image.getSubimage(i * 16 + (96*2),72,w,h);
        }
        this.movingLeft = new Animation(movingLeft);


        BufferedImage[]  movingDown = new BufferedImage[6];
        for (int i = 0; i < 6; i++) {
            movingDown[i] = image.getSubimage(i * 16 + (96*3),72,w,h);
        }
        this.movingDown = new Animation(movingDown);


    }

    public Animation getMovingUp() {
        return movingUp;
    }

    public Animation getMovingDown() {
        return movingDown;
    }

    public Animation getMovingRight() {
        return movingRight;
    }

    public Animation getMovingLeft() {
        return movingLeft;
    }

    public Animation getStandingUp() {
        return standingUp;
    }

    public Animation getStandingDown() {
        return standingDown;
    }

    public Animation getStandingRight() {
        return standingRight;
    }

    public Animation getStandingLeft() {
        return standingLeft;
    }

}
