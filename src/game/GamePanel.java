package game;

import game.KeyHandler;
import game.Main;
import game.entity.Animation;
import game.entity.CollisionChecker;
import game.entity.Direction;
import game.entity.Player;
import game.font.Font;
import game.font.FontManager;
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
    private CollisionChecker collisionChecker;
    private FontManager fontManager;
    private Thread gameThread;

    private Player player;
    private final int screenX = screenWidth/2 - (tileSize/2);
    private final int screenY = screenHeight/2 - (tileSize/2);

    private static GamePanel instance;

    public GamePanel(File worldFile,int worldWidth,int worldHeight) throws IOException {
        this.player = new Player(screenX,screenY,4,"Adam_16x16.png");
        this.world = new World(this,worldWidth,worldHeight,worldFile);
        this.collisionChecker = new CollisionChecker(this);
        this.fontManager = new FontManager(this);

        //Font font = new Font("Classic",);



        System.out.println(screenX + " " + screenY);

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        gameThread = new Thread(this);
        gameThread.start();
        instance = this;
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
            if (!collisionChecker.hasCollision(player, Direction.UP))
                player.setY(player.getY() - playerSpeed);
            player.setFacingDirection(Direction.UP);
        }
        if (keyH.isKeyPressed("S")) {
            if (!collisionChecker.hasCollision(player,Direction.DOWN))
                player.setY(player.getY() + playerSpeed);
            player.setFacingDirection(Direction.DOWN);
        }
        if (keyH.isKeyPressed("A")) {
            if (!collisionChecker.hasCollision(player,Direction.LEFT))
                player.setX(player.getX() - playerSpeed);
            player.setFacingDirection(Direction.LEFT);
        }
        if (keyH.isKeyPressed("D")) {
            if (!collisionChecker.hasCollision(player,Direction.RIGHT))
                player.setX(player.getX() + playerSpeed);
            player.setFacingDirection(Direction.RIGHT);
        }

        if (keyH.isKeyPressed("Q")) {
            player.setCurrentHP((int) (player.getCurrentHP() * 0.9));
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



        int x = screenX, y = screenY;

        if (player.getX() < screenX) {
            x = player.getX();
        }

        if (player.getY() < screenY) {
            y = player.getY();
        }

        int rightOffset = screenWidth - screenX;
        if (rightOffset > world.getWorldWidth() - player.getX()) {
            x = screenWidth - (world.getWorldWidth() - player.getX());
        }

        int bottomOffset = screenHeight - screenY;
        if (bottomOffset > world.getWorldHeight() - player.getY()) {
            y = screenHeight - (world.getWorldHeight() - player.getY());
        }


        animation.drawAnimation(g2,x,y);

        player.updateHPBar(g2);

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

    public int getScale() {
        return scale;
    }

    public World getWorld() {
        return world;
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public static GamePanel getInstance() {
        return instance;
    }
}
