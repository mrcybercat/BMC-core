package multi.converter;

import multi.converter.algorithm.AlgorithmType;

public class MainDriver {
    public static void main(String[] args) {
        Options options = Options.extractOptions(args);
        Workflow workflow = new Workflow(options);
        workflow.runWorkflow();
    }
}