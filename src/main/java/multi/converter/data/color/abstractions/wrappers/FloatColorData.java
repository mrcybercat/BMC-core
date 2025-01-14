package multi.converter.data.color.abstractions.wrappers;

import multi.converter.data.color.abstractions.ColorChannelName;
import multi.converter.data.color.abstractions.ColorData;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class FloatColorData extends ColorData<Double> {
    public FloatColorData(LinkedHashMap<ColorChannelName, Double[][]> colorChannels) {
        super(colorChannels);
    }

    public double getChannelAverageAtXY(int x, int y) {
        double sum = 0;
        for (Map.Entry<ColorChannelName, Double[][]> entry : this.colorChannels.entrySet()) {
            sum += entry.getValue()[y][x];
        }
        return sum/colorChannels.entrySet().size();
    }
}
