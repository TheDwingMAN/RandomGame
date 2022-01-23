package game.entity;

import game.KeyHandler;
import game.Main;
import game.map.TileManager;
import game.map.World;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable{

    private final int originalTileSize = 16; // 16 x 16 tile
    private final int scale = 3;

    private final int tileSize = originalTileSize * scale; //48 Pixels
    private final int screenCol = 16;
    private final int screenRow = 12;
    private final int screenWidth = tileSize * screenCol;
    private final int screenHeight = tileSize * screenRow;


    World world;


    private KeyHandler keyH = new KeyHandler();
    private TileManager tileManager = new TileManager(this);
    private Thread gameThread;

    private Player player;
    private final int screenX = screenWidth/2 - (tileSize/2);
    private final int screenY = screenHeight/2 - (tileSize/2);

    public GamePanel(File worldFile,int worldWidth,int worldHeight) throws IOException {
        this.player = new Player(screenX,screenY,4,"Adam_16x16.png");
        this.world = new World(this,worldWidth,worldHeight,worldFile);


        System.out.println(screenWidth + " " + screenHeight);

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        gameThread = new Thread(this);
        gameThread.start();

    }


    @Override
    public void run() {
        tileManager.loadWorld(world);
        boolean running = true;
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
        player.setMoving(true);
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

        Animation animation = Direction.getMovingAnimationForDirection(player.getTexture(),player.getFacingDirection());
        animation.runAnimation();
        if (keyH.isNotMoving()) {
            animation.resetAnimation();
            player.setMoving(false);
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        //super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        tileManager.drawMap(g2);

        Animation animation = player.isMoving() ? Direction.getMovingAnimationForDirection(player.getTexture(),
                              player.getFacingDirection()) :
                              Direction.getStandingAnimationForDirection(player.getTexture(),player.getFacingDirection());



        animation.drawAnimation(g2,screenX,screenY);

        g2.dispose();
    }

    public int getScreenCol() {
        return screenCol;
    }

    public int getScreenRow() {
        return screenRow;
    }

    public int getTileSize() {
        return tileSize;
    }

    public Player getPlayer() {
        return player;
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public World getWorld() {
        return world;
    }
}
