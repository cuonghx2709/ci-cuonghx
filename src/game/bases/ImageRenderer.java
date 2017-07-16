package game.bases;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by cuonghx2709 on 7/16/2017.
 */
public class ImageRenderer {
    public BufferedImage image;

    public ImageRenderer(BufferedImage image){
        this.image = image;
    }
    public void render(Graphics2D g2d, Vector2D position){
        g2d.drawImage(image, (int)( position.x - image.getWidth()/2) ,(int) (position.y - image.getHeight()), null);
    }
}
