package multi.converter.algorithm.directors;

import multi.converter.Options;
import multi.converter.algorithm.Algorithm;
import multi.converter.algorithm.steps.file.ExtractRGBFromImageStep;
import multi.converter.algorithm.steps.file.ReadImageFromAFileStep;
import multi.converter.algorithm.steps.miscalenious.CalculateMetricsStep;

import static multi.converter.metrics.abstractions.MetricWorkflowType.COMPREHENSIVE_IMAGE;

public class ImageMetricMeasurementDirector implements AlgorithmDirector {
    @Override
    public Algorithm defineAlgorithm(Algorithm.AlgorithmBuilder builder, Options options) {
        return Algorithm.AlgorithmBuilder.newInstance()
                .addStep(new ReadImageFromAFileStep())
                .addStep(new ExtractRGBFromImageStep())
                .addStep(new CalculateMetricsStep(options.sourcePath(), COMPREHENSIVE_IMAGE))
                .getAlgorithm();
    }
}
