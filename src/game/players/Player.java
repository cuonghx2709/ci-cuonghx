package game.players;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by cuonghx2709 on 7/11/2017.
 */
public class Player {
    //properties:thuoc tinh
    public int x;
    public int y;
    public BufferedImage image;
//methods:phuong thuc
    public void move(int dx, int dy){
        x += dx;
        y += dy;
    }
    public void  render(Graphics2D g2d){
        g2d.drawImage(image, x, y, null);

    }
}
