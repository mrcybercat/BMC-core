package multi.converter;

import multi.converter.algorithm.directors.*;
import multi.converter.data.SourceFile;
import multi.converter.algorithm.Algorithm;


public class Workflow {

    private Algorithm algorithm;
    private AlgorithmOptions options;

    public Workflow(AlgorithmOptions options) {
        this.options = options;
        switch (options.type()) {
            case STANDARD_JPEG ->
                    this.algorithm = new JPEGCompressionDirector().defineAlgorithm(
                            Algorithm.AlgorithmBuilder.newInstance(),
                            options);
            case YUV_CONVERSION ->
                    this.algorithm = new YUVConversionDirector().defineAlgorithm(
                            Algorithm.AlgorithmBuilder.newInstance(),
                            options);
            case IMAGE_METRIC ->
                    this.algorithm = new ImageMetricMeasurementDirector().defineAlgorithm(
                            Algorithm.AlgorithmBuilder.newInstance(),
                            options);
            case VIDEO_METRIC ->
                    this.algorithm = new VideoMetricMeasurementDirector().defineAlgorithm(
                            Algorithm.AlgorithmBuilder.newInstance(),
                            options);
            case VIDEO_METRIC3D ->
                    this.algorithm = new VideoMetric3DMeasurementDirector().defineAlgorithm(
                            Algorithm.AlgorithmBuilder.newInstance(),
                            options);

        }
    }

    public void runWorkflow() {
         this.algorithm.executeAlgorithm(new SourceFile(this.options.sourcePath()));
    }
}
