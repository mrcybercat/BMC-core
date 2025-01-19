package multi.converter.data.color.abstractions;

import multi.converter.data.DataClass;
import multi.converter.metrics.abstractions.MetricTarget;

import java.util.LinkedHashMap;

public abstract class ColorData<T> implements DataClass, MetricTarget {
    protected LinkedHashMap<ColorChannelName, T[][]> colorChannels;

    public ColorData(LinkedHashMap<ColorChannelName, T[][]> colorChannels) {
        this.colorChannels = colorChannels;
    }

    public boolean hasSameDimensions(ColorData data) {
        for (ColorChannelName channelName : colorChannels.keySet()) {
            if(this.getChannelWidth(channelName) != data.getChannelWidth(channelName)){
                return false;
            }
            if(this.getChannelHeight(channelName) != data.getChannelHeight(channelName)){
                return false;
            }
        }
        return true;
    }

    public abstract double getChannelAverageAtXY(int x, int y);

    public double[][] getAveragedChannel(){
        if(!hasSameDimensions(this)){
            return null;
        }
        double[][] averagedChannel = new double[getFirstChannelHeight()][getFirstChannelWidth()];
        for (int y = 0; y < this.getFirstChannelHeight(); y++) {
            for (int x = 0; x < this.getFirstChannelWidth(); x++) {
                averagedChannel[y][x] = getChannelAverageAtXY(x, y);
            }
        }
        return averagedChannel;
    }

    public int getFirstChannelWidth() {
        return this.colorChannels.entrySet().stream().findFirst().get().getValue()[0].length;
    }

    public int getFirstChannelHeight() {
        return this.colorChannels.entrySet().stream().findFirst().get().getValue().length;
    }

    public int getChannelWidth(ColorChannelName name) {
        return this.colorChannels.get(name)[0].length;
    }

    public int getChannelHeight(ColorChannelName name) {
        return this.colorChannels.get(name).length;
    }

    public T[][] getChannel(ColorChannelName name) {
        return this.colorChannels.get(name);
    }
}


/*
    public ColorData(ColorData.ColorDataBuilder builder) {
        this.colorChannels = builder.colorChannels;
    }

    public static class ColorDataBuilder{
        private Map<ColorChannelName, byte[][]> colorChannels;

        private ColorDataBuilder() {
            this.colorChannels = new HashMap<>();
        }

        public static ColorDataBuilder newInstance() {
            return new ColorDataBuilder();
        }

        public ColorDataBuilder addChannel(ColorChannelName name, byte[][] channel) {
            this.colorChannels.put(name, channel);
            return this;
        }

        public ColorData getAlgorithm() {
            return new ColorData(this);
        }
    }

 */