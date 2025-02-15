package multi.converter.algorithm.steps.decompression;

import multi.converter.algorithm.steps.AlgorithmStep;
import multi.converter.algorithm.steps.UnableToPerformStepException;
import multi.converter.data.BlockData;
import multi.converter.data.DataBlock;
import multi.converter.data.color.YUVData;

public class ReconstructFromBlocksStep extends AlgorithmStep<YUVData, BlockData> {

    private int scaleFactorY = 1;
    private int scaleFactorU = 1;
    private int scaleFactorV = 1;

    private int blockSize;
    private int originalWidth;
    private int originalHeight;

    public ReconstructFromBlocksStep(int blockSize,
                                     int originalWidth,
                                     int originalHeight,
                                     int scaleFactorY,
                                     int scaleFactorU,
                                     int scaleFactorV){
        this.blockSize = blockSize;
        this.originalHeight = originalHeight;
        this.originalWidth = originalWidth;
        this.scaleFactorU = scaleFactorU;
        this.scaleFactorV = scaleFactorV;
        this.scaleFactorY = scaleFactorY;
    }

    @Override
    public YUVData performAlgorithmStep(BlockData source) throws UnableToPerformStepException {
        byte[][] luma = reconstructChannelFromBlocks(source.getBlocksY(),
                originalWidth / scaleFactorY,
                originalHeight / scaleFactorY);
        byte[][] chromaU = reconstructChannelFromBlocks(source.getBlocksU(),
                originalWidth / scaleFactorU,
                originalHeight / scaleFactorU);
        byte[][] chromaV = reconstructChannelFromBlocks(source.getBlocksV(),
                originalWidth / scaleFactorV,
                originalHeight / scaleFactorV);

        return YUVData.fromArrays(luma, chromaU, chromaV);
    }

    private byte[][] reconstructChannelFromBlocks(DataBlock[] blocks, int width, int height) {
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