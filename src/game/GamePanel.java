package game;

import game.entity.Direction;
import game.entity.Player;
import game.entity.Texture;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable{

    private final int originalTileSize = 16; // 16 x 16 tile
    private final int scale = 3;

    private final int tileSize = originalTileSize * scale;
    private final int screenCol = 16;
    private final int screenRow = 12;
    private final int screenWidth = tileSize * screenCol;
    private final int screenHeight = tileSize * screenRow;

    private KeyHandler keyH = new KeyHandler();
    private Thread gameThread;

    private Player player;

    private int FPS = 60;

    private double nextDrawInterval = 0;
    private double drawInterval = 1000000000/FPS;


    public GamePanel() throws IOException {
        System.out.println(screenWidth + " " + screenHeight);

        this.player = new Player(100,100,4,"Adam_16x16.png");

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.green);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameThread != null) {

            if (System.nanoTime() >= nextDrawInterval) {
                nextDrawInterval = System.nanoTime() + drawInterval;
                update();
                repaint();
            }
        }
    }

    public void update() {

        int playerSpeed = keyH.isMovingDiagonally() ? 3 : this.player.getSpeed();

        if (keyH.isUpPressed()) {
            player.setY(player.getY() - playerSpeed);
            player.setFacingDirection(Direction.DOWN);
        }
        if (keyH.isDownPressed()) {
            player.setY(player.getY() + playerSpeed);
            player.setFacingDirection(Direction.UP);
        }
        if (keyH.isLeftPressed()) {
            player.setX(player.getX() - playerSpeed);
            player.setFacingDirection(Direction.LEFT);
        }
        if (keyH.isRightPressed()) {
            player.setX(player.getX() + playerSpeed);
            player.setFacingDirection(Direction.RIGHT);
        }

        player.getTexture().addToMovingSprite();
        if (keyH.isKeysNoPressed()) player.getTexture().resetMovingSprite();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.white);

        g2.drawImage(Direction.getImageForDirection(player.getTexture(),player.getFacingDirection())[player.getTexture().getMovingSprite()],
                player.getX(),player.getY(), Texture.w * (scale - 1),Texture.h * (scale - 1),null);
        g2.dispose();
    }
}
