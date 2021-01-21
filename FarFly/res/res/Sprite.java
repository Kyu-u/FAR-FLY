package res;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sprite {
    public static BufferedImage getSprite(String fileName) throws IOException {
        return ImageIO.read(Sprite.class.getResourceAsStream(fileName));
    }
    
    public Image getImage(String loc) {
        try {
            return ImageIO.read(new File(loc));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
