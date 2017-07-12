package game;

import game.enemies.Enemies;
import game.players.Player;
import game.players.PlayerSpell;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;


/**
 * Created by cuonghx2709 on 7/12/2017.
 */
public class GameWindow extends JFrame{


    BufferedImage background;
    BufferedImage bufferedImagebackground;

    boolean upPress;
    boolean downPress;
    boolean rightPress;
    boolean leftPress;
    boolean spacePress;
    boolean check;

    int backGroundX;
    int backGroundY;
    static long start;
    static long end;
    long startB;
    static long endB;


    ArrayList<Enemies> enemiess = new ArrayList<>();
    ArrayList<PlayerSpell> playerSpells = new ArrayList<>();
    Player player = new Player();

    Graphics2D graphics2Dbfbg;

    public GameWindow(){
        SetUpGameWindow();
        Loadimage();
        SetUpInput();

        player.x = background.getWidth()/2;
        player.y = this.getHeight() - player.image.getHeight()*3;
        backGroundX = 0;
        backGroundY = this.getHeight()- background.getHeight();
        bufferedImagebackground = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        graphics2Dbfbg = (Graphics2D) bufferedImagebackground.getGraphics();
        start = System.currentTimeMillis();
        startB = System.currentTimeMillis();
        check = true;

        this.setVisible(true);
    }

    public void loop() {

        while (true){

            run();

            render();

        }
    }
    private void run(){
        int dx = 0;
        int dy = 0;
        end = System.currentTimeMillis();
        endB = System.currentTimeMillis();

        Random rd = new Random();
        int x = rd.nextInt(250) + 100;
        if(endB-startB > x){
            creatEnemies();
        }

        if (backGroundY < 0){
            backGroundY += 5;
        }
        if (upPress){
            dy -= 5;
        }
        if (downPress){
            dy += 5;
        }
        if (rightPress){
            dx +=5;
        }
        if (leftPress){
            dx -= 5;
        }
        if (spacePress){
            if (check) {
                creatNewSpell();
                start = System.currentTimeMillis();
                check = false;
            }else{
                if (CountDown()){
                    creatNewSpell();
                }

            }
        }
        for(PlayerSpell playerSpell : playerSpells){
            playerSpell.move();
        }
        player.move(dx, dy);

        for (Enemies enemies: enemiess) {
            enemies.move(0, 10);
        }

    }

    private void creatEnemies() {
        Enemies enemies = new Enemies();
        enemies.x=enemies.rand(background.getWidth());
        try {
            enemies.image = ImageIO.read(new File("assets/images/enemies/bullets/blue.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        enemiess.add(enemies);
        startB = System.currentTimeMillis();
    }

    private void creatNewSpell() {
        PlayerSpell playerSpell = new PlayerSpell();
        try {
            playerSpell.image = ImageIO.read(new File("assets/images/player-spells/a/1.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        playerSpell.x = player.x + player.image.getWidth()/ 8 ;
        playerSpell.y = player.y - playerSpell.image.getHeight()/3;

        playerSpells.add(playerSpell);
    }

    private void render(){
        try {
            Thread.sleep(17);
            graphics2Dbfbg.setColor(Color.BLACK);
            graphics2Dbfbg.fillRect(0, 0, this.getWidth(), this.getHeight());
            graphics2Dbfbg.drawImage(background, backGroundX, backGroundY, null);
            player.render(graphics2Dbfbg);
            for (PlayerSpell playerSpell: playerSpells) {
                playerSpell.render(graphics2Dbfbg);
            }
            for (Enemies enemies: enemiess) {
                enemies.render(graphics2Dbfbg);
            }

            Graphics2D g2d = (Graphics2D) this.getGraphics();
            g2d.drawImage(bufferedImagebackground, 0 , 0 , null);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private boolean CountDown(){
        if (end - start >= 150){
            start = System.currentTimeMillis();
            return true;
        }
        return false;
    }


    private void SetUpInput() {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_RIGHT:
                        rightPress = true;
                        break;
                    case KeyEvent.VK_LEFT:
                        leftPress = true;
                        break;
                    case KeyEvent.VK_UP:
                        upPress = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        downPress = true;
                        break;
                    case KeyEvent.VK_SPACE:
                        spacePress = true;
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_RIGHT:
                        rightPress = false;
                        break;
                    case KeyEvent.VK_LEFT:
                        leftPress = false;
                        break;
                    case KeyEvent.VK_UP:
                        upPress = false;
                        break;
                    case KeyEvent.VK_DOWN:
                        downPress = false;
                        break;
                    case KeyEvent.VK_SPACE:
                        spacePress = false;
                        check = true;
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void Loadimage () {
        try {
            background = ImageIO.read(new File("assets/images/background/0.png"));
            player.image = ImageIO.read(new File("assets/images/players/straight/0.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void SetUpGameWindow() {
        this.setSize(800,800);
        this.setTitle("cuonghx");
        this.setResizable(false);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}