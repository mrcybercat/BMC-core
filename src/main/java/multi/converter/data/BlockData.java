package multi.converter.data;

import java.util.Arrays;

public class BlockData implements DataClass {
    DataBlock[] blocksY;
    DataBlock[] blocksU;
    DataBlock[] blocksV;

    public BlockData(DataBlock[] blocksY, DataBlock[] blocksU, DataBlock[] blocksV) {
        this.blocksY = blocksY;
        this.blocksU = blocksU;
        this.blocksV = blocksV;
    }

    public DataBlock[] getBlocksU() {
        return blocksU;
    }

    public DataBlock[] getBlocksV() {
        return blocksV;
    }

    public DataBlock[] getBlocksY() {
        return blocksY;
    }

    @Override
    public String toString() {
        return "BlockData{" +
                "blocksY=" + Arrays.deepToString(blocksY) +
                ", blocksU=" + Arrays.deepToString(blocksU) +
                ", blocksV=" + Arrays.deepToString(blocksV) +
                '}';
    }
}
