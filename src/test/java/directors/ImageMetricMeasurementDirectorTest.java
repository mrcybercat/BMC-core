package directors;

import multi.converter.MetaData;
import multi.converter.AlgorithmOptions;
import multi.converter.Workflow;
import multi.converter.algorithm.AlgorithmType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ImageMetricMeasurementDirectorTest {
    @Test
    void runSuccess() {
        AlgorithmOptions options = new AlgorithmOptions(
                "src\\main\\resources\\img.png",
                "src\\main\\resources\\yuv.jpeg",
                1,
                AlgorithmType.IMAGE_METRIC,
                false
        );

        Workflow workflow = new Workflow(options);
        workflow.runWorkflow();
        assertDoesNotThrow(
                workflow::runWorkflow);
    }
}
