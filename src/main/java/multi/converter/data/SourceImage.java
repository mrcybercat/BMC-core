package multi.converter.data;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SourceImage extends BufferedImage implements DataClass{
    public SourceImage(int width, int height, int imageType) {
        super(width, height, imageType);
    }

    public static SourceImage fromBufferedImage(BufferedImage img) {
        SourceImage sourceImage = new SourceImage(img.getWidth(), img.getHeight(), img.getType());

        Graphics2D g = sourceImage.createGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();

        return sourceImage;
    }
}
