package multi.converter.algorithm.steps.file;

import multi.converter.data.SourceImage;
import multi.converter.data.color.RGBData;
import multi.converter.algorithm.steps.AlgorithmStep;

public class ExtractRGBFromImageStep extends AlgorithmStep<RGBData, SourceImage> {

    @Override
    public RGBData performAlgorithmStep(SourceImage source) {
        byte[][] red    = new byte[source.getHeight()][source.getWidth()];
        byte[][] green  = new byte[source.getHeight()][source.getWidth()];
        byte[][] blue   = new byte[source.getHeight()][source.getWidth()];

        for (int y = 0; y < source.getHeight(); y++) {
            for (int x = 0; x < source.getWidth(); x++) {
                //                  2          1          0
                //    bitpos     32109876 54321098 76543210
                //    ------   --+--------+--------+--------+
                //    bits     ..|RRRRRRRR|GGGGGGGG|BBBBBBBB|
                red[y][x]      = (byte) ((source.getRGB(x, y) >> 16) & 0xff);
                green[y][x]    = (byte) ((source.getRGB(x, y) >> 8) & 0xff);
                blue[y][x]     = (byte) ((source.getRGB(x, y)) & 0xff);
            }
        }

        return RGBData.fromArrays(red, green, blue);
    }
}
