package multi.converter.algorithm.steps.miscalenious;


import multi.converter.algorithm.steps.AlgorithmStep;
import multi.converter.algorithm.steps.UnableToPerformStepException;
import multi.converter.algorithm.steps.convertions.ConvertImageSequenceToCuboidDataStep;
import multi.converter.algorithm.steps.file.ExtractRGBDataFromImageSequenceStep;
import multi.converter.algorithm.steps.file.ExtractRGBFromImageStep;
import multi.converter.algorithm.steps.file.ReadImageFromAFileStep;
import multi.converter.algorithm.steps.file.ReadImageSequenceFromFileStep;
import multi.converter.data.CuboidData;
import multi.converter.data.RGBSequence;
import multi.converter.data.SourceFile;
import multi.converter.data.color.RGBData;
import multi.converter.metrics.abstractions.MetricWorkflow;
import multi.converter.metrics.abstractions.MetricWorkflowType;
import multi.converter.metrics.factories.ImageMetricWorkflowFactory;
import multi.converter.metrics.factories.Metric3DWorkflowFactory;

public class Calculate3DMetricsStep extends AlgorithmStep<CuboidData, CuboidData> {
    String originalFile;
    MetricWorkflow<CuboidData> metricWorkflow;

    public Calculate3DMetricsStep(String originalFile, MetricWorkflowType type) {
        this.metricWorkflow = new Metric3DWorkflowFactory().createMetricWorkflow(type);
        this.originalFile = originalFile;
    }

    @Override
    public CuboidData performAlgorithmStep(CuboidData source) throws UnableToPerformStepException {
        ReadImageSequenceFromFileStep read = new ReadImageSequenceFromFileStep();
        ExtractRGBDataFromImageSequenceStep extractRGB = new ExtractRGBDataFromImageSequenceStep();
        ConvertImageSequenceToCuboidDataStep convert = new ConvertImageSequenceToCuboidDataStep();
        CuboidData original = convert.performAlgorithmStep(
                            extractRGB.performAlgorithmStep(
                            read.performAlgorithmStep(new SourceFile(originalFile))));

        System.out.println(metricWorkflow.calculateMetrics(original, source));

        return source;
    }

}
