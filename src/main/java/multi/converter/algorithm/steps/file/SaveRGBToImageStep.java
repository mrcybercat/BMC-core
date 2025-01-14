package multi.converter.algorithm.steps.file;

import multi.converter.data.SourceImage;
import multi.converter.data.color.RGBData;
import multi.converter.algorithm.steps.AlgorithmStep;

import java.awt.image.BufferedImage;

public class SaveRGBToImageStep extends AlgorithmStep<SourceImage, RGBData> {
    @Override
    public SourceImage performAlgorithmStep(RGBData source) {
        SourceImage image = new SourceImage(
                source.getWidth(), source.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < source.getHeight(); y++) {
            for (int x = 0; x < source.getWidth(); x++) {
                int rgb = source.getRed()[y][x];
                rgb     = (rgb << 8) + (source.getGreen()[y][x] & 0xFF);
                rgb     = (rgb << 8) + (source.getBlue()[y][x] & 0xFF);
                image.setRGB(x, y, rgb);
            }
        }
        return image;
    }
}
