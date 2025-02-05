package multi.converter.algorithm.directors;

import multi.converter.Options;
import multi.converter.algorithm.steps.compression.DivideIntoBlocksStep;
import multi.converter.algorithm.steps.compression.FlattenBlocksZigZagStep;
import multi.converter.algorithm.steps.compression.ForwardDCTStep;
import multi.converter.algorithm.steps.convertions.ConvertRGBtoYUVStep;
import multi.converter.algorithm.steps.file.ExtractRGBFromImageStep;
import multi.converter.algorithm.steps.file.ReadImageFromAFileStep;
import multi.converter.algorithm.Algorithm;

public class JPEGCompressionDirector implements AlgorithmDirector {

    @Override
    public Algorithm defineAlgorithm(Algorithm.AlgorithmBuilder builder, Options options) {
        return Algorithm.AlgorithmBuilder.newInstance()
                .addStep(new ReadImageFromAFileStep())
                .addStep(new ExtractRGBFromImageStep())
                .addStep(new ConvertRGBtoYUVStep())
                .addStep(new DivideIntoBlocksStep(8))
                .addStep(new ForwardDCTStep())
                .addStep(new FlattenBlocksZigZagStep())
                .getAlgorithm();
    }
}
