package multi.converter.algorithm.directors;

import multi.converter.AlgorithmOptions;
import multi.converter.algorithm.steps.compression.*;
import multi.converter.algorithm.steps.convertions.ConvertRGBtoYUVStep;
import multi.converter.algorithm.steps.convertions.ConvertYUVtoRGBStep;
import multi.converter.algorithm.steps.decompression.*;
import multi.converter.algorithm.steps.file.ExtractRGBFromImageStep;
import multi.converter.algorithm.steps.file.ReadImageFromAFileStep;
import multi.converter.algorithm.Algorithm;
import multi.converter.algorithm.steps.file.SaveImageToFileStep;
import multi.converter.algorithm.steps.file.SaveRGBToImageStep;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JPEGCompressionDirector implements AlgorithmDirector {

    @Override
    public Algorithm defineAlgorithm(Algorithm.AlgorithmBuilder builder, AlgorithmOptions options) {
        return Algorithm.AlgorithmBuilder.newInstance()
                // compression
                .addStep(new ReadImageFromAFileStep())
                .addStep(new ExtractRGBFromImageStep())
                .addStep(new ConvertRGBtoYUVStep())
                .addStep(new DownscaleChromaStep(options.UVDownScaleFactor(), options.UVDownScaleFactor()))
                .addStep(new DivideIntoBlocksStep(8))
                .addStep(new ForwardDCTStep())
                .addStep(new QuantizeBlocksStep())
                .addStep(new FlattenBlocksZigZagStep())
                .addStep(new RLEEncodingStep())
                .addStep(new HuffmanEncodingStep())
                // decompression
                .addStep(new HuffmanDecodingStep())
                .addStep(new RLEDecodingStep(
                        8,
                        readWidth(options.sourcePath()),
                        readHeight(options.sourcePath()),
                        options.UVDownScaleFactor()))
                .addStep(new RecontructZigZagToBlocksStep(8, readWidth(options.sourcePath()), readHeight(options.sourcePath())))
                .addStep(new DequantizeBlocksStep())
                .addStep(new InverseDCTStep())
                .addStep(new ReconstructFromBlocksStep(
                        8,
                        readWidth(options.sourcePath()),
                        readHeight(options.sourcePath()),
                        1,
                        options.UVDownScaleFactor(),
                        options.UVDownScaleFactor()))
                .addStep(new RescaleChromaStep(options.UVDownScaleFactor(), options.UVDownScaleFactor()))
                .addStep(new ConvertYUVtoRGBStep())
                .addStep(new SaveRGBToImageStep())
                .addStep(new SaveImageToFileStep("jpeg", options.outputPath()))
                .getAlgorithm();
    }


}
