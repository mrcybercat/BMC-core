package multi.converter.algorithm.steps.decompression;

import multi.converter.algorithm.steps.AlgorithmStep;
import multi.converter.algorithm.steps.UnableToPerformStepException;
import multi.converter.data.BlockData;
import multi.converter.data.DataBlock;
import multi.converter.data.color.YUVData;

public class ReconstructFromBlocksStep extends AlgorithmStep<YUVData, BlockData> {

    private static int blockSize;
    private int originalWidth;
    private int originalHeight;

    public ReconstructFromBlocksStep(int blockSize, int originalWidth, int originalHeight) {
        this.blockSize = blockSize;
        this.originalWidth = originalWidth;
        this.originalHeight = originalHeight;
    }

    @Override
    public YUVData performAlgorithmStep(BlockData source) throws UnableToPerformStepException {
        byte[][] yChannel = reconstructChannelFromBlocks(source.getBlocksY(), originalWidth, originalHeight);
        byte[][] uChannel = reconstructChannelFromBlocks(source.getBlocksU(), originalWidth, originalHeight);
        byte[][] vChannel = reconstructChannelFromBlocks(source.getBlocksV(), originalWidth, originalHeight);

        return YUVData.fromArrays(yChannel, uChannel, vChannel);
    }

    private static byte[][] reconstructChannelFromBlocks(DataBlock[] blocks, int width, int height) {
        byte[][] channel = new byte[height][width];

        int blocksPerRow = width / blockSize;
        int blocksPerColumn = height / blockSize;

        int blockIndex = 0;
        for (int row = 0; row < blocksPerColumn; row++) {
            for (int col = 0; col < blocksPerRow; col++) {
                insertBlock(channel, blocks[blockIndex], row * blockSize, col * blockSize);
                blockIndex++;
            }
        }

        return channel;
    }

    private static void insertBlock(byte[][] channel, DataBlock block, int startRow, int startCol) {
        for (int y = 0; y < block.getSize(); y++) {
            for (int x = 0; x < block.getSize(); x++) {
                channel[startRow + y][startCol + x] = (byte) block.getBlockValueOnXY(x, y);
            }
        }
    }
}