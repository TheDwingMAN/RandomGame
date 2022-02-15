package game.entity;

import game.GamePanel;
import game.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    private boolean isMoving;

    private int currentHP;
    private int maxHP;
    private BufferedImage hpBar;
    private BufferedImage hpBackground;




    public Player(int x, int y, int speed, BufferedImage texture) {
        super(x, y, speed, texture);
    }

    public Player(int x, int y, int speed, String texture) throws IOException {
        this(x, y, speed, ImageIO.read(Main.class.getResource("/Resources/" + texture)));
        this.isMoving = false;
        this.hitbox.x = 1;
        this.hitbox.y = 7;
        this.hitbox.width = 14;
        this.hitbox.height = 16;
        this.maxHP = 100;
        this.currentHP = maxHP;


        hpBar = ImageIO.read(Main.class.getResource("/Resources/HealthBar/health.png"));
        hpBackground = ImageIO.read(Main.class.getResource("/Resources/HealthBar/healthBar.png"));
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }


    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }


    public void updateHPBar(Graphics2D g) {
        //Draws the background of the health bar
        double widthPercent = 0; // percent
        double heightPercent = 0.902; // percent

        int widthPosition = (int) (GamePanel.getInstance().getScreenWidth() * widthPercent);
        int heightPosition = (int) (GamePanel.getInstance().getScreenHeight() * heightPercent);


        int backgroundWidth = hpBackground.getWidth() * GamePanel.getInstance().getScale();
        int backgroundHeight = hpBackground.getHeight() * GamePanel.getInstance().getScale();

        g.drawImage(hpBackground,widthPosition,heightPosition,backgroundWidth,backgroundHeight,null);

        //Draws the health (red thingy)
        double healthPercent = (double) currentHP / maxHP;

        System.out.println(healthPercent);

        double widthBarPercent = 0.075;
        double heightBarPercent = 0.943;

        int barWidthPosition = (int) (GamePanel.getInstance().getScreenWidth() * widthBarPercent);
        int barHeightPosition = (int) (GamePanel.getInstance().getScreenHeight() * heightBarPercent);

        int barWidth = (int) (hpBar.getWidth() * GamePanel.getInstance().getScale() * healthPercent);
        int barHeight = hpBar.getHeight() * GamePanel.getInstance().getScale();

        g.drawImage(hpBar,barWidthPosition,barHeightPosition,barWidth,barHeight,null);

    }
}
