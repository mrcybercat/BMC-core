package multi.converter.data.color;

import multi.converter.data.color.abstractions.ColorChannelName;
import multi.converter.data.color.abstractions.wrappers.ByteColorData;
import multi.converter.util.MatrixUnwrapper;
import multi.converter.util.MatrixWrapper;

import java.util.LinkedHashMap;

public class YUVData extends ByteColorData {

    public YUVData(LinkedHashMap<ColorChannelName, Byte[][]> colorChannels) {
        super(colorChannels);
    }

    public static YUVData fromArrays(byte[][] luma, byte[][] chromaU, byte[][] chromaV) {
        LinkedHashMap<ColorChannelName, Byte[][]> channels =  new LinkedHashMap<>();
        channels.put(ColorChannelName.LUMAY,    MatrixWrapper.wrapPrimitive(luma));
        channels.put(ColorChannelName.CHROMAU,  MatrixWrapper.wrapPrimitive(chromaU));
        channels.put(ColorChannelName.CHROMAV,  MatrixWrapper.wrapPrimitive(chromaV));
        return new YUVData(channels);
    }

    public int getWidth() {
        return this.getChannelWidth(ColorChannelName.LUMAY);
    }

    public int getHeight() {
        return this.getChannelHeight(ColorChannelName.LUMAY);
    }


    public int getWidthLuma() {
        return this.getChannelWidth(ColorChannelName.LUMAY);
    }

    public int getHeightLuma() {
        return this.getChannelHeight(ColorChannelName.LUMAY);
    }

    public int getWidthChromaU() {
        return this.getChannelWidth(ColorChannelName.CHROMAU);
    }

    public int getHeightChromaU() {
        return this.getChannelHeight(ColorChannelName.CHROMAU);
    }

    public int getWidthChromaV() {
        return this.getChannelWidth(ColorChannelName.CHROMAV);
    }

    public int getHeightChromaV() {
        return this.getChannelHeight(ColorChannelName.CHROMAV);
    }

    public Byte[][] getLuma() {
        return this.getChannel(ColorChannelName.LUMAY);
    }

    public Byte[][] getChromaU() {
        return this.getChannel(ColorChannelName.CHROMAU);
    }

    public Byte[][] getChromaV() {
        return this.getChannel(ColorChannelName.CHROMAV);
    }

    public byte[][] getLumaPrimitive() {
        return MatrixUnwrapper.unwrapPrimitive(
                this.getChannel(ColorChannelName.LUMAY));
    }

    public byte[][] getChromaUPrimitive() {
        return MatrixUnwrapper.unwrapPrimitive(
                this.getChannel(ColorChannelName.CHROMAU));
    }

    public byte[][] getChromaVPrimitive() {
        return MatrixUnwrapper.unwrapPrimitive(
                this.getChannel(ColorChannelName.CHROMAV));
    }


}
