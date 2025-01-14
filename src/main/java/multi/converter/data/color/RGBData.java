package multi.converter.data.color;

import multi.converter.data.color.abstractions.ColorChannelName;
import multi.converter.data.color.abstractions.wrappers.ByteColorData;
import multi.converter.util.MatrixUnwrapper;
import multi.converter.util.MatrixWrapper;

import java.util.LinkedHashMap;

public class RGBData extends ByteColorData {

    public RGBData(LinkedHashMap<ColorChannelName, Byte[][]> colorChannels) {
        super(colorChannels);
    }

    public static RGBData fromArrays(byte[][] red, byte[][] green, byte[][] blue){
        LinkedHashMap<ColorChannelName, Byte[][]> channels =  new LinkedHashMap<>();
        channels.put(ColorChannelName.RED,      MatrixWrapper.wrapPrimitive(red));
        channels.put(ColorChannelName.GREEN,    MatrixWrapper.wrapPrimitive(green));
        channels.put(ColorChannelName.BLUE,     MatrixWrapper.wrapPrimitive(blue));
        return new RGBData(channels);
    }

    public int getWidth() {
        return this.getChannelWidth(ColorChannelName.RED);
    }

    public int getHeight() {
        return this.getChannelHeight(ColorChannelName.RED);
    }

    public Byte[][] getRed() {
        return this.getChannel(ColorChannelName.RED);
    }

    public Byte[][] getGreen() {
        return this.getChannel(ColorChannelName.GREEN);
    }

    public Byte[][] getBlue() {
        return this.getChannel(ColorChannelName.BLUE);
    }

    public byte[][] getRedPrimitive() {
        return MatrixUnwrapper.unwrapPrimitive(
                this.getChannel(ColorChannelName.RED));
    }

    public byte[][] getGreenPrimitive() {
        return MatrixUnwrapper.unwrapPrimitive(
                this.getChannel(ColorChannelName.GREEN));
    }

    public byte[][] getBluePrimitive() {
        return MatrixUnwrapper.unwrapPrimitive(
                this.getChannel(ColorChannelName.BLUE));
    }

    @Override
    public double getChannelAverageAtXY(int x, int y){
        return (0.2989  * this.getChannel(ColorChannelName.RED)[y][x] +
                0.5870  * this.getChannel(ColorChannelName.GREEN)[y][x] +
                0.1140  * this.getChannel(ColorChannelName.BLUE)[y][x]);
    }
}
