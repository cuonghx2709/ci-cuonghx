package game.enemies;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by cuonghx2709 on 7/12/2017.
 */
public class Enemies {
    public int x;
    public int y = 0;
    public BufferedImage image;

    public int  rand(int Width){
        Random random = new Random();
        return random.nextInt(Width);
    }

    public void move(int dx, int dy){
        x += dx;
        y += dy;
    }
    public void render(Graphics2D g2d){
        g2d.drawImage(image, x, y, null);
    }
}