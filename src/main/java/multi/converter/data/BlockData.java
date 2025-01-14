package multi.converter.data;

public class BlockData implements DataClass {
    DataBlock[] blocksY;
    DataBlock[] blocksU;
    DataBlock[] blocksV;

    public BlockData(DataBlock[] blocksU, DataBlock[] blocksV, DataBlock[] blocksY) {
        this.blocksU = blocksU;
        this.blocksV = blocksV;
        this.blocksY = blocksY;
    }
}
