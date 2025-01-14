package multi.converter.algorithm.steps.miscalenious;

import multi.converter.data.SourceFile;
import multi.converter.data.color.RGBData;
import multi.converter.metrics.abstractions.MetricWorkflow;
import multi.converter.metrics.abstractions.MetricWorkflowType;
import multi.converter.metrics.factories.ImageMetricWorkflowFactory;
import multi.converter.algorithm.steps.AlgorithmStep;
import multi.converter.algorithm.steps.UnableToPerformStepException;

import multi.converter.algorithm.steps.file.ReadImageFromAFileStep;
import multi.converter.algorithm.steps.file.ExtractRGBFromImageStep;

import java.io.File;

public class CalculateMetricsStep extends AlgorithmStep<RGBData, RGBData> {
    String originalFile;
    MetricWorkflow<RGBData> metricWorkflow;

    public CalculateMetricsStep(String originalFile, MetricWorkflowType type) {
        this.metricWorkflow = new ImageMetricWorkflowFactory().createMetricWorkflow(type);
        this.originalFile = originalFile;
    }

    @Override
    public RGBData performAlgorithmStep(RGBData source) throws UnableToPerformStepException {
        ReadImageFromAFileStep read = new ReadImageFromAFileStep();
        ExtractRGBFromImageStep extractRGB = new ExtractRGBFromImageStep();
        RGBData original = extractRGB.performAlgorithmStep(
                read.performAlgorithmStep(new SourceFile(originalFile)));

        System.out.println(metricWorkflow.calculateMetrics(original, source));

        return source;
    }
}
