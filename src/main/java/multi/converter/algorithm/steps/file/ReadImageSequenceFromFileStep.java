package multi.converter.algorithm.steps.file;

import multi.converter.algorithm.steps.AlgorithmStep;
import multi.converter.algorithm.steps.UnableToPerformStepException;
import multi.converter.data.ImageSequence;
import multi.converter.data.SourceFile;
import multi.converter.data.SourceImage;
import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;

import java.io.IOException;

public class ReadImageSequenceFromFileStep extends AlgorithmStep<ImageSequence, SourceFile> {
    private static final int seconds = 1;

    @Override
    public ImageSequence performAlgorithmStep(SourceFile source) throws UnableToPerformStepException {
        ImageSequence sequence = new ImageSequence();
        FrameGrab grab = null;

        try {
            grab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(source));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JCodecException e) {
            throw new RuntimeException(e);
        }

        int totalFrames = grab.getVideoTrack().getMeta().getTotalFrames();
        double duration = grab.getVideoTrack().getMeta().getTotalDuration();

        double fps =  totalFrames / duration;
        System.out.println("Fps: " + fps);
        System.out.println("Duration: " + grab.getVideoTrack().getMeta().getTotalDuration());
        System.out.println("Frames: " + grab.getVideoTrack().getMeta().getTotalFrames());
        int frameInterval = (int) (seconds * fps); // Number of frames to skip for 5 seconds interval
        int frameNumber = 0;

        while (frameNumber < totalFrames) {
            Picture picture = null;
            try {
                picture = grab.seekToFramePrecise(frameNumber).getNativeFrame();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (JCodecException e) {
                throw new RuntimeException(e);
            }
            if (picture == null) break;

            SourceImage image = SourceImage.fromBufferedImage(AWTUtil.toBufferedImage(picture));
            sequence.add(image);

            frameNumber += frameInterval;
            System.out.println("Frame number: " + frameNumber);
        }
        return sequence;
    }
}
