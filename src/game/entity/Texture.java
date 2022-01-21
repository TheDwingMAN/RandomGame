package game.entity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Texture {

    public static int w = 16, h = 24; // TEXTURES WIDTH AND LENGTH

    private BufferedImage[] movingUp,movingDown,movingRight,movingLeft;

    private int movingSprite = 29;

    public Texture(BufferedImage image) {

        movingRight = new BufferedImage[6];
        for (int i = 0; i < 6; i++) {
            movingRight[i] = image.getSubimage(i * 16,72,w,h);
        }

        movingUp = new BufferedImage[6];
        for (int i = 0; i < 6; i++) {
            movingUp[i] = image.getSubimage(i * 16 + 96,72,w,h);
        }

        movingLeft = new BufferedImage[6];
        for (int i = 0; i < 6; i++) {
            movingLeft[i] = image.getSubimage(i * 16 + (96*2),72,w,h);
        }

        movingDown = new BufferedImage[6];
        for (int i = 0; i < 6; i++) {
            movingDown[i] = image.getSubimage(i * 16 + (96*3),72,w,h);
        }



    }

    public BufferedImage[] getMovingUp() {
        return movingUp;
    }

    public BufferedImage[] getMovingDown() {
        return movingDown;
    }

    public BufferedImage[] getMovingRight() {
        return movingRight;
    }

    public BufferedImage[] getMovingLeft() {
        return movingLeft;
    }

    public void addToMovingSprite() {
        movingSprite = (movingSprite + 1) == movingDown.length * 5 ? 0 : movingSprite + 1;
    }

    public int getMovingSprite() {
        return (movingSprite * 2) / 10;
    }

    public void resetMovingSprite() {
        movingSprite = 29;
    }
}
