package multi.converter.data.color.abstractions.wrappers;

import multi.converter.data.color.abstractions.ColorChannelName;
import multi.converter.data.color.abstractions.ColorData;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class BooleanColorData extends ColorData<Boolean> {
    public BooleanColorData(LinkedHashMap<ColorChannelName, Boolean[][]> colorChannels) {
        super(colorChannels);
    }

    public double getChannelAverageAtXY(int x, int y) {
        double sum = 0;
        //TODO: Implement

        for (Map.Entry<ColorChannelName, Boolean[][]> entry : this.colorChannels.entrySet()) {
        //    sum += entry.getValue()[y][x];
        }
        return sum/colorChannels.entrySet().size();
    }

}
