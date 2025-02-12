package multi.converter.algorithm.steps.decompression;

import multi.converter.algorithm.steps.AlgorithmStep;
import multi.converter.algorithm.steps.UnableToPerformStepException;
import multi.converter.data.BlockData;
import multi.converter.data.DataBlock;

public class DequantizeBlocksStep extends AlgorithmStep<BlockData, BlockData> {

    private double[][] quantMatrixY = {
            {16, 11, 10, 16, 24, 40, 51, 61},
            {12, 12, 14, 19, 26, 58, 60, 55},
            {14, 13, 16, 24, 40, 57, 69, 56},
            {14, 17, 22, 29, 51, 87, 80, 62},
            {18, 22, 37, 56, 68, 109, 103, 77},
            {24, 35, 55, 64, 81, 104, 113, 92},
            {49, 64, 78, 87, 103, 121, 120, 101},
            {72, 92, 95, 98, 112, 100, 103, 99}
    };
    private double[][] quantMatrixUV= {
            {17, 18, 24, 47, 99, 99, 99, 99},
            {18, 21, 26, 66, 99, 99, 99, 99},
            {24, 26, 56, 99, 99, 99, 99, 99},
            {47, 66, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99}
    };


    @Override
    public BlockData performAlgorithmStep(BlockData source) throws UnableToPerformStepException {
        return new BlockData(
                dequantizeLuminaceBlocks(source.getBlocksY()),
                dequantizeChromaBlocks(source.getBlocksU()),
                dequantizeChromaBlocks(source.getBlocksV())
        );
    }

    private DataBlock[] dequantizeLuminaceBlocks(DataBlock[] blocks){
        DataBlock[] result = new DataBlock[blocks.length];
        for(int i = 0; i < result.length; i++){
            result[i] = dequantize(blocks[i], quantMatrixY);
        }
        return result;
    }

    private DataBlock[] dequantizeChromaBlocks(DataBlock[] blocks){
        DataBlock[] result = new DataBlock[blocks.length];
        for(int i = 0; i < result.length; i++){
            result[i] = dequantize(blocks[i], quantMatrixUV);
        }
        return result;
    }


    private DataBlock dequantize(DataBlock block, double[][] quantMatrix){
        double[][] dequantizedBlock = new double[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                dequantizedBlock[i][j] = Math.round((float) block.getBlockValueOnXY(j, i) * quantMatrix[i][j]);
            }
        }
        return new DataBlock(dequantizedBlock);
    }
}
