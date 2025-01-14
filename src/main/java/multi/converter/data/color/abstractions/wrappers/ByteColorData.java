package multi.converter.data.color.abstractions.wrappers;

import multi.converter.data.color.abstractions.ColorChannelName;
import multi.converter.data.color.abstractions.ColorData;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class ByteColorData extends ColorData<Byte> {
    public ByteColorData(LinkedHashMap<ColorChannelName, Byte[][]> colorChannels) {
        super(colorChannels);
    }

    public double getChannelAverageAtXY(int x, int y) {
        double sum = 0;
        //System.out.println("getChannelAverageAtXY:ByteColorData");
        for (Map.Entry<ColorChannelName, Byte[][]> entry : this.colorChannels.entrySet()) {
            sum += entry.getValue()[y][x];
        }
        return sum/colorChannels.entrySet().size();
    }
}
