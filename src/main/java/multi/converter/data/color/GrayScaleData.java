package multi.converter.data.color;

import multi.converter.data.color.abstractions.ColorChannelName;
import multi.converter.data.color.abstractions.wrappers.DoubleColorData;
import multi.converter.util.MatrixWrapper;

import java.util.LinkedHashMap;

public class GrayScaleData extends DoubleColorData {

    public GrayScaleData(LinkedHashMap<ColorChannelName, Double[][]> colorChannels) {
        super(colorChannels);
    }

    public static GrayScaleData fromArrays(double[][] grayscale){
        LinkedHashMap<ColorChannelName, Double[][]> channels =  new LinkedHashMap<>();
        channels.put(ColorChannelName.GRAYSCALE,  MatrixWrapper.wrapPrimitive(grayscale));
        return new GrayScaleData(channels);
    }

}
