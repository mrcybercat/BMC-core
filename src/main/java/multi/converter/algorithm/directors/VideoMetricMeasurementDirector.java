package multi.converter.algorithm.directors;

import multi.converter.Options;
import multi.converter.algorithm.Algorithm;
import multi.converter.algorithm.steps.file.ExtractRGBDataFromImageSequenceStep;
import multi.converter.algorithm.steps.file.ReadImageSequenceFromFileStep;
import multi.converter.algorithm.steps.miscalenious.CalculateSequentialMetricsStep;

import static multi.converter.metrics.abstractions.MetricWorkflowType.COMPREHENSIVE_IMAGE;

public class VideoMetricMeasurementDirector implements AlgorithmDirector {
    @Override
    public Algorithm defineAlgorithm(Algorithm.AlgorithmBuilder builder, Options options) {
        return Algorithm.AlgorithmBuilder.newInstance()
                .addStep(new ReadImageSequenceFromFileStep())
                .addStep(new ExtractRGBDataFromImageSequenceStep())
                .addStep(new CalculateSequentialMetricsStep(options.sourcePath(), COMPREHENSIVE_IMAGE))
                .getAlgorithm();
    }
}
