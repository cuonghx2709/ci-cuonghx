package game;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import game.players.Player;
import game.players.PlayerSpell;
import javafx.print.PageLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by huynq on 7/9/17.
 */
public class GameWindow extends JFrame {

    private BufferedImage background;


    BufferedImage backBufferImage;
    Graphics2D backBufferGraphics2D;

    int backgroundY;

    boolean rightPressed;
    boolean leftPressed;
    boolean upPressed;
    boolean downPressed;
    boolean xPress;

    ArrayList<PlayerSpell> playerSpells = new ArrayList<>();
    Player player = new Player();

    public GameWindow() {
        setupWindow();
        loadImages();

        player.x = background.getWidth() / 2;
        player.y = this.getHeight() - player.image.getHeight();

        backgroundY = this.getHeight() - background.getHeight();

        backBufferImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        backBufferGraphics2D = (Graphics2D) backBufferImage.getGraphics();

        setupInputs();
        this.setVisible(true);
    }

    private void setupInputs() {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        rightPressed = true;
                        break;
                    case KeyEvent.VK_LEFT:
                        leftPressed = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        downPressed = true;
                        break;
                    case KeyEvent.VK_UP:
                        upPressed = true;
                        break;
                    case KeyEvent.VK_X:
                        xPress = true;
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        rightPressed = false;
                        break;
                    case KeyEvent.VK_LEFT:
                        leftPressed = false;
                        break;
                    case KeyEvent.VK_DOWN:
                        downPressed = false;
                        break;
                    case KeyEvent.VK_UP:
                        upPressed = false;
                        break;
                    case KeyEvent.VK_X:
                        xPress = false;
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public void loop() {
        while (true) {
            try {
                Thread.sleep(17);

                run();

                render();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void run() {
        if (backgroundY <= 0)
            backgroundY +=5;

        int dx = 0;
        int dy = 0;

        if (rightPressed) {
            dx += 5;
        }

        if (leftPressed) {
            dx -= 5;
        }

        if (downPressed) {
            dy += 5;
        }

        if (upPressed) {
            dy -= 5;
        }

        if (xPress){
            PlayerSpell playerSpell = new PlayerSpell();
            playerSpell.x = player.x;
            playerSpell.y = player.y;
            try {
                playerSpell.image = ImageIO.read(new File("assets/images/player-spells/a/0.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            playerSpells.add(playerSpell);
        }
        for (PlayerSpell playerSpell : playerSpells) {
            playerSpell.move();
        }
        player.move(dx, dy);
    }

    private void render() {
        backBufferGraphics2D.setColor(Color.BLACK);
        backBufferGraphics2D.fillRect(0, 0, this.getWidth(), this.getHeight());

        backBufferGraphics2D.drawImage(background, 0, backgroundY, null);

        player.render(backBufferGraphics2D);
        for (PlayerSpell playerSpell : playerSpells) {
            playerSpell.render(backBufferGraphics2D);
        }

        Graphics2D g2d = (Graphics2D) this.getGraphics();

        g2d.drawImage(backBufferImage, 0, 0, null);
    }


    private void loadImages() {
        try {
            background = ImageIO.read(new File("assets/images/background/0.png"));
            player.image = ImageIO.read(new File("assets/images/players/straight/0.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupWindow() {
        this.setSize(600, 600);
        this.setResizable(false);
        this.setTitle("Touhou - Remade by cuonghx");

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}