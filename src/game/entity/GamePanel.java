package game.entity;

import game.KeyHandler;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable{

    private boolean running = false;

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


    public GamePanel() throws IOException {
        this.player = new Player(100,100,4,"Adam_16x16.png");
        System.out.println(screenWidth + " " + screenHeight);

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
        this.running = true;
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000/amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                update();
                delta--;
            }

            if (running)
                repaint();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }

        }
    }

    public void update() {
        int playerSpeed = keyH.isMovingDiagonally() ? 3 : this.player.getSpeed();

        if (keyH.isKeyPressed("W")) {
            player.setY(player.getY() - playerSpeed);
            player.setFacingDirection(Direction.UP);
        }
        if (keyH.isKeyPressed("S")) {
            player.setY(player.getY() + playerSpeed);
            player.setFacingDirection(Direction.DOWN);
        }
        if (keyH.isKeyPressed("A")) {
            player.setX(player.getX() - playerSpeed);
            player.setFacingDirection(Direction.LEFT);
        }
        if (keyH.isKeyPressed("D")) {
            player.setX(player.getX() + playerSpeed);
            player.setFacingDirection(Direction.RIGHT);
        }

        player.getTexture().addToMovingSprite();
        if (keyH.isNotMoving()) player.getTexture().resetMovingSprite();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;


        g2.drawImage(Direction.getImageForDirection(player.getTexture(),player.getFacingDirection())[player.getTexture().getMovingSprite()],
                player.getX(),player.getY(), Texture.w * 2,Texture.h * 2,null);
        g2.dispose();
    }
}
