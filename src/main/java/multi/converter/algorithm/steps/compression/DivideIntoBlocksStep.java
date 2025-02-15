package multi.converter.algorithm.steps.compression;

import multi.converter.data.BlockData;
import multi.converter.data.DataBlock;
import multi.converter.data.color.YUVData;
import multi.converter.algorithm.steps.AlgorithmStep;
import multi.converter.algorithm.steps.UnableToPerformStepException;

public class DivideIntoBlocksStep extends AlgorithmStep<BlockData, YUVData> {

    private static int blockSize;

    public DivideIntoBlocksStep(int blockSize){
        this.blockSize = blockSize;
    }

    /**
     * Splits the YUV data into 8x8 blocks and stores them in a BlockData object.
     *
     * @param source the YUV data to split
     * @return a BlockData object containing the 8x8 blocks for luma, chromaU, and chromaV
     */
    @Override
    public BlockData performAlgorithmStep(YUVData source) throws UnableToPerformStepException {
        DataBlock[] blocksY = splitChannelIntoBlocks(source.getLumaPrimitive());
        DataBlock[] blocksU = splitChannelIntoBlocks(source.getChromaUPrimitive());
        DataBlock[] blocksV = splitChannelIntoBlocks(source.getChromaVPrimitive());

        return new BlockData(blocksY, blocksU, blocksV);
    }

    /**
     * Splits a single channel into 8x8 blocks.
     *
     * @param channel the 2D byte array representing the channel data
     * @return an array of DataBlock objects, each containing a 8x8 block of data
     */
    private static DataBlock[] splitChannelIntoBlocks(byte[][] channel) {
        int height = channel.length;
        int width = channel[0].length;

        int blocksPerRow = (int) Math.ceil(width / (double) blockSize);
        int blocksPerColumn = (int) Math.ceil(height / (double) blockSize);

        DataBlock[] blocks = new DataBlock[blocksPerRow * blocksPerColumn];

        int blockIndex = 0;
        for (int row = 0; row < blocksPerColumn; row++) {
            for (int col = 0; col < blocksPerRow; col++) {
                blocks[blockIndex] = extractBlock(channel, row * blockSize, col * blockSize);
                blockIndex++;
            }
        }

        return blocks;
    }

    /**
     * Extracts a 8x8 block from a 2D byte array starting at the given row and column.
     *
     * @param channel the 2D byte array representing the channel data
     * @param startRow the starting row index of the block
     * @param startCol the starting column index of the block
     * @return a DataBlock object containing the 8x8 block of data
     */
    private static DataBlock extractBlock(byte[][] channel, int startRow, int startCol) {
        DataBlock block = new DataBlock(blockSize);
        for (int y = 0; y < blockSize; y++) {
            for (int x = 0; x < blockSize; x++) {
                if((startRow + y) < channel.length && (startCol + x) < channel[0].length) {
                    block.setBlockValueOnXY(channel[startRow + y][startCol + x], x, y);
                }
            }
        }
        return block;
    }

}
