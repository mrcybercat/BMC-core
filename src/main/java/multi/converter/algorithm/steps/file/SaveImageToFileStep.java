package multi.converter.algorithm.steps.file;

import multi.converter.data.SourceFile;
import multi.converter.data.SourceImage;
import multi.converter.algorithm.steps.AlgorithmStep;
import multi.converter.algorithm.steps.UnableToPerformStepException;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class SaveImageToFileStep extends AlgorithmStep<SourceFile, SourceImage> {
    private String  extension = "";
    private String  outputFile = "";

    public SaveImageToFileStep(String extension, String outputFile) {
        this.extension = extension;
        this.outputFile = outputFile;
    }

    @Override
    public SourceFile performAlgorithmStep(SourceImage source) {
        try {
            if(ImageIO.write(source, extension, new File(outputFile))){
                return new SourceFile(outputFile);
            }else{
                throw new UnableToPerformStepException("Unable to save file");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
