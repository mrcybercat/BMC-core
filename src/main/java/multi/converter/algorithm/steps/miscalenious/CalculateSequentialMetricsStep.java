package multi.converter.algorithm.steps.miscalenious;

import multi.converter.algorithm.steps.AlgorithmStep;
import multi.converter.algorithm.steps.UnableToPerformStepException;
import multi.converter.algorithm.steps.file.ExtractRGBDataFromImageSequenceStep;
import multi.converter.algorithm.steps.file.ReadImageSequenceFromFileStep;
import multi.converter.data.RGBSequence;
import multi.converter.data.SourceFile;
import multi.converter.metrics.abstractions.MetricWorkflow;
import multi.converter.metrics.abstractions.MetricWorkflowType;
import multi.converter.metrics.factories.SequenceMetricWorkflowFactory;

import java.io.File;

public class CalculateSequentialMetricsStep extends AlgorithmStep<RGBSequence, RGBSequence> {
    String originalFile;
    MetricWorkflow<RGBSequence> metricWorkflow;

    public CalculateSequentialMetricsStep(String originalFile, MetricWorkflowType type) {
        this.metricWorkflow = new SequenceMetricWorkflowFactory().createMetricWorkflow(type);
        this.originalFile = originalFile;
    }

    @Override
    public RGBSequence performAlgorithmStep(RGBSequence source) throws UnableToPerformStepException {
        ReadImageSequenceFromFileStep read = new ReadImageSequenceFromFileStep();
        ExtractRGBDataFromImageSequenceStep extractRGB = new ExtractRGBDataFromImageSequenceStep();
        RGBSequence original = extractRGB.performAlgorithmStep(
                read.performAlgorithmStep(new SourceFile(originalFile)));



        System.out.println(metricWorkflow.calculateMetrics(original, source));

        return source;
    }
}
