package multi.converter.algorithm.steps.file;

import multi.converter.algorithm.steps.AlgorithmStep;
import multi.converter.algorithm.steps.UnableToPerformStepException;
import multi.converter.data.ImageSequence;
import multi.converter.data.RGBSequence;
import multi.converter.data.SourceImage;

public class ExtractRGBDataFromImageSequenceStep extends AlgorithmStep<RGBSequence, ImageSequence> {
    @Override
    public RGBSequence performAlgorithmStep(ImageSequence source) throws UnableToPerformStepException {
        ExtractRGBFromImageStep step = new ExtractRGBFromImageStep();
        RGBSequence sequence = new RGBSequence();
        for(SourceImage image : source.getSourceImages()) {
            sequence.add(step.performAlgorithmStep(image));
        }
        return sequence;
    }
}
