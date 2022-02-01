package game.entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animation {

    private int speed; //The bigger the speed the slower the animation will run
    private int frames;

    private int index = 0;
    private int count = 0;

    private BufferedImage[] images;

    public Animation(int speed, BufferedImage... images) {
        this.images = images;
        this.speed = speed;
        frames = images.length;
    }

    public Animation(BufferedImage... images) {
        this(4,images);
    }

    public void runAnimation() {
        index++;
        if (index > speed) {
            index = 0;
            nextFrame();
        }
    }

    private void nextFrame() {
        count++;
        if (count >= frames)
            count = 0;
    }

    public void drawAnimation(Graphics2D g2,int x,int y) {
        g2.drawImage(images[count],x,y,Texture.w * 2,Texture.h * 2,null);
    }

    public void resetAnimation() {
        this.index = 0;
        this.count = 0;
    }


}
