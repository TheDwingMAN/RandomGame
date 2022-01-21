package game;

import game.entity.Player;
import game.entity.Texture;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main {

    private static JFrame window;
    private static GamePanel gamePanel;

    public static void main(String[] args) throws IOException {


        Player player = new Player(100,100,100,"Adam_16x16.png");
        File file = new File("image.png");
        ImageIO.write(player.getTexture().getMovingRight()[4],"png",file);





        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Random 2D Game");
        gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);



    }
}
