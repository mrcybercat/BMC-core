package multi.converter.data;

import multi.converter.data.color.RGBData;
import multi.converter.metrics.abstractions.MetricTarget;

import java.util.ArrayList;
import java.util.List;

public class RGBSequence implements DataClass, MetricTarget {
    List<RGBData> sourceImages;

    public RGBSequence() {
        sourceImages = new ArrayList<>();
    }

    public void add(RGBData rgbData) {
        sourceImages.add(rgbData);
    }

    public List<RGBData> getRGBData() {
        return this.sourceImages;
    }

    public int getDuration() {
        return this.sourceImages.size();
    }

    public int getWidth() {
        return this.sourceImages.get(0).getRed()[0].length;
    }

    public int getHeight() {
        return this.sourceImages.get(0).getRed().length;
    }

}
