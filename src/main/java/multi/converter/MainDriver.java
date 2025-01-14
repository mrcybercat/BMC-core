package multi.converter;

import multi.converter.algorithm.AlgorithmType;

public class MainDriver {
    public static void main(String[] args) {
        //Options options = Options.extractOptions(args);
        //Workflow workflow = new Workflow(options);
        //workflow.runWorkflow();


        Options options = new Options(
                "src\\main\\resources\\video.mp4",
                "src\\main\\resources\\yuv.jpeg",
                1,
                AlgorithmType.VIDEO_METRIC,
                false,
                new MetaData()
        );

        Workflow workflow = new Workflow(options);
        workflow.runWorkflow();
    }
}