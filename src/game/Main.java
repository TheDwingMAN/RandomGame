package game;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class Main {

    private static JFrame window;

    public static void main(String[] args) throws IOException {

        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Random 2D Game");

        File world = null;
        try {
            world = Paths.get(Main.class.getResource("/Resources/Map/map.txt").toURI()).toFile();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        GamePanel gamePanel = new GamePanel(world,32,20);
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);



    }
}
