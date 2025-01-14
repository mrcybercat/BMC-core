package multi.converter.data.color;

import multi.converter.data.color.abstractions.ColorChannelName;
import multi.converter.data.color.abstractions.ColorData;
import multi.converter.data.color.abstractions.wrappers.DoubleColorData;
import multi.converter.util.MatrixWrapper;

import java.util.LinkedHashMap;

public class HVSData extends DoubleColorData {

    public HVSData(LinkedHashMap<ColorChannelName, Double[][]> colorChannels) {
        super(colorChannels);
    }

    public static HVSData fromArrays(double[][] hue, double[][] saturation, double[][] value){
        LinkedHashMap<ColorChannelName, Double[][]> channels =  new LinkedHashMap<>();
        channels.put(ColorChannelName.HUE,          MatrixWrapper.wrapPrimitive(hue));
        channels.put(ColorChannelName.SATURATION,   MatrixWrapper.wrapPrimitive(saturation));
        channels.put(ColorChannelName.VALUE,        MatrixWrapper.wrapPrimitive(value));
        return new HVSData(channels);
    }
}
