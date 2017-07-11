package game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static tklibs.SpriteUtils.loadImage;

/**
 * Created by cuonghx2709 on 7/11/2017.
 */
public class Utils {
    public static BufferedImage LoadImage(String url){
        try {
            return ImageIO.read(new File(url));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static BufferedImage LoadAssetImage(String url){
        return loadImage("assets/images"+url);
    }
}
