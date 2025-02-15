package multi.converter.algorithm.directors;

import multi.converter.AlgorithmOptions;
import multi.converter.algorithm.Algorithm;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * {@code AlgorithmDirector} defines a contract for creating an algorithm
 * with a specified structure and steps.
 */
public interface AlgorithmDirector {

    /**
     * Defines the algorithm using the provided builder and options.
     *
     * @param builder the builder to construct the algorithm
     * @param options the options that may contact data relevant to the
     *                algorithm execution or alter the algorithm structure
     * @return the constructed Algorithm
     */
    Algorithm defineAlgorithm(Algorithm.AlgorithmBuilder builder, AlgorithmOptions options);

    public default int readHeight(String filename)  {
        BufferedImage bimg = null;
        try {
            bimg = ImageIO.read(new File(filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bimg.getHeight();
    }

    public default int readWidth(String filename) {
        BufferedImage bimg = null;
        try {
            bimg = ImageIO.read(new File(filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bimg.getWidth();
    }

}
