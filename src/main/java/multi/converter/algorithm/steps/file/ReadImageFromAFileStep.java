package multi.converter.algorithm.steps.file;

import multi.converter.data.SourceFile;
import multi.converter.data.SourceImage;
import multi.converter.algorithm.steps.AlgorithmStep;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ReadImageFromAFileStep extends AlgorithmStep<SourceImage, SourceFile> {
    @Override
    public SourceImage performAlgorithmStep(SourceFile source) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(source); // eventually C:\\ImageTest\\pic2.jpg
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return SourceImage.fromBufferedImage(img);
    }
}
