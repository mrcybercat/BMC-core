package multi.converter.algorithm.steps.decompression;

import multi.converter.algorithm.steps.AlgorithmStep;
import multi.converter.algorithm.steps.UnableToPerformStepException;
import multi.converter.data.BlockData;
import multi.converter.data.DataBlock;

public class InverseDCTStep extends AlgorithmStep<BlockData, BlockData> {
    @Override
    public BlockData performAlgorithmStep(BlockData source) throws UnableToPerformStepException {
        return new BlockData(
                runDCTonBlocks(source.getBlocksY()),
                runDCTonBlocks(source.getBlocksU()),
                runDCTonBlocks(source.getBlocksV())
        );
    }
    private DataBlock[] runDCTonBlocks(DataBlock[] blocks){
        DataBlock[] result = new DataBlock[blocks.length];
        for(int i = 0; i < result.length; i++){
            result[i] = runInverseDCT(blocks[i]);
        }
        return result;
    }

    private DataBlock runInverseDCT(DataBlock sourceBlock){
        double[][] matrix = sourceBlock.getBlock();
        int N = matrix.length;
        int M = matrix[0].length;
        double[][] idct = new double[N][M];

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                double sum = 0.0;
                for (int u = 0; u < N; u++) {
                    for (int v = 0; v < M; v++) {
                        double alphaU = (u == 0) ? (1.0 / Math.sqrt(N)) : (Math.sqrt(2.0 / N));
                        double alphaV = (v == 0) ? (1.0 / Math.sqrt(M)) : (Math.sqrt(2.0 / M));
                        sum += alphaU * alphaV * matrix[u][v] *
                                Math.cos((2 * x + 1) * u * Math.PI / (2 * N)) *
                                Math.cos((2 * y + 1) * v * Math.PI / (2 * M));
                    }
                }
                idct[x][y] = sum;
            }
        }
        return new DataBlock(idct);
    }

}
