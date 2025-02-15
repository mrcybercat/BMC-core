package multi.converter.algorithm.steps.decompression;

import multi.converter.algorithm.steps.AlgorithmStep;
import multi.converter.algorithm.steps.UnableToPerformStepException;
import multi.converter.data.BlockData;
import multi.converter.data.DataBatch;
import multi.converter.data.DataBlock;

import java.util.Arrays;

public class RecontructZigZagToBlocksStep extends AlgorithmStep<BlockData, DataBatch> {

    private int blockSize;

    public RecontructZigZagToBlocksStep(int blockSize, int originalWidth, int originalHeight) {
        this.blockSize = blockSize;
    }

    @Override
    public BlockData performAlgorithmStep(DataBatch source) throws UnableToPerformStepException {
        BlockData result= new BlockData(
                writeChannelBlocks(source.getArrY()),
                writeChannelBlocks(source.getArrU()),
                writeChannelBlocks(source.getArrV())
        );

        return result;
    }

    private DataBlock[] writeChannelBlocks(int[] data) {
        DataBlock[] result = new DataBlock[(data.length / (blockSize*blockSize))+1];
        for(int i = 0; i < result.length; i++){
            int[] flatBlock = Arrays.copyOfRange(data,
                    i*(blockSize*blockSize),
                    (i+1)*(blockSize*blockSize));
            result[i] = new DataBlock(reconstructZigZagMatrix(flatBlock, blockSize, blockSize));
        }
        return result;
    }

    private static double[][] reconstructZigZagMatrix(int[] flatArray, int rows, int cols) {
        double[][] mat = new double[rows][cols];
        int row = 0, col = 0, index = 0;
        boolean rowInc = false;

        int minDim = Math.min(rows, cols);
        for (int len = 1; len <= minDim; ++len) {
            for (int i = 0; i < len; ++i) {
                mat[row][col] = flatArray[index++];
                if (i + 1 == len) break;

                if (rowInc) {
                    row++;
                    col--;
                } else {
                    row--;
                    col++;
                }
            }

            if (len == minDim) break;

            if (rowInc) {
                row++;
                rowInc = false;
            } else {
                col++;
                rowInc = true;
            }
        }

        if (row == 0) {
            if (col == cols - 1) row++;
            else col++;
            rowInc = true;
        } else {
            if (row == rows - 1) col++;
            else row++;
            rowInc = false;
        }

        int maxDim = Math.max(rows, cols) - 1;
        for (int len, diag = maxDim; diag > 0; --diag) {
            len = (diag > minDim) ? minDim : diag;
            for (int i = 0; i < len; ++i) {
                mat[row][col] = flatArray[index++];
                if (i + 1 == len) break;

                if (rowInc) {
                    row++;
                    col--;
                } else {
                    col++;
                    row--;
                }
            }

            if (row == 0 || col == cols - 1) {
                if (col == cols - 1) row++;
                else col++;
                rowInc = true;
            } else if (col == 0 || row == rows - 1) {
                if (row == rows - 1) col++;
                else row++;
                rowInc = false;
            }
        }
        return mat;
    }


}
