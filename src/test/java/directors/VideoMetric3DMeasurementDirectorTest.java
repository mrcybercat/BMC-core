package directors;

import multi.converter.MetaData;
import multi.converter.AlgorithmOptions;
import multi.converter.Workflow;
import multi.converter.algorithm.AlgorithmType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class VideoMetric3DMeasurementDirectorTest {
    @Test
    void runSuccess() {
        AlgorithmOptions options = new AlgorithmOptions(
                "src\\main\\resources\\video.mp4",
                "src\\main\\resources\\yuv.jpeg",
                1,
                AlgorithmType.VIDEO_METRIC3D,
                false
        );

        Workflow workflow = new Workflow(options);
        workflow.runWorkflow();
        assertDoesNotThrow(
                workflow::runWorkflow);
    }

}
