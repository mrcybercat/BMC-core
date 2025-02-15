package multi.converter.algorithm.directors;

import multi.converter.AlgorithmOptions;
import multi.converter.algorithm.steps.convertions.ConvertRGBtoYUVStep;
import multi.converter.algorithm.steps.file.ExtractRGBFromImageStep;
import multi.converter.algorithm.steps.file.ReadImageFromAFileStep;
import multi.converter.algorithm.steps.file.SaveImageToFileStep;
import multi.converter.algorithm.steps.file.SaveRGBToImageStep;
import multi.converter.algorithm.steps.convertions.ConvertYUVtoRGBStep;
import multi.converter.algorithm.Algorithm;

public class YUVConversionDirector implements AlgorithmDirector {
    @Override
    public Algorithm defineAlgorithm(Algorithm.AlgorithmBuilder builder, AlgorithmOptions options) {
        return Algorithm.AlgorithmBuilder.newInstance()
                .addStep(new ReadImageFromAFileStep())
                .addStep(new ExtractRGBFromImageStep())
                .addStep(new ConvertRGBtoYUVStep())
                .addStep(new ConvertYUVtoRGBStep())
                .addStep(new SaveRGBToImageStep())
                .addStep(new SaveImageToFileStep("jpeg", options.outputPath()))
                .getAlgorithm();
    }
}
