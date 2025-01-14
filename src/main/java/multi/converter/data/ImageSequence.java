package multi.converter.data;

import java.util.ArrayList;
import java.util.List;

public class ImageSequence implements DataClass {
    List<SourceImage> sourceImages;

    public ImageSequence() {
        sourceImages = new ArrayList<>();
    }

    public void add(SourceImage sourceImage) {
        sourceImages.add(sourceImage);
    }

    public List<SourceImage> getSourceImages() {
        return this.sourceImages;
    }

    public int getDuration() {
        return this.sourceImages.size();
    }
}
