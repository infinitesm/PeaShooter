package ai.peashooter.game;

import ai.peashooter.game.loop.GameLoop;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    public static final int HEIGHT = 720;
    public static final int WIDTH = 1280;

    public GameWindow() {
        GameLoop loop = new GameLoop();

        add(loop);

        setTitle("Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setPreferredSize(new Dimension(GameWindow.WIDTH, GameWindow.HEIGHT));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
