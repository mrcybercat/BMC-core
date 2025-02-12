package multi.converter.algorithm.steps.compression;

import multi.converter.data.BlockData;
import multi.converter.algorithm.steps.AlgorithmStep;
import multi.converter.algorithm.steps.UnableToPerformStepException;
import multi.converter.data.DataBlock;

public class ForwardDCTStep extends AlgorithmStep<BlockData, BlockData> {
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
            result[i] = runDCT(blocks[i]);
        }
        return result;
    }

    private DataBlock runDCT(DataBlock sourceBlock){
        int i, j, k, l;
        int m = sourceBlock.getSize();
        int n = sourceBlock.getSize();

        // dct will store the discrete cosine transform
        double[][] dct = new double[m][n];

        double ci, cj, dct1, sum;

        for (i = 0; i < m; i++)
        {
            for (j = 0; j < n; j++)
            {
                // ci and cj depends on frequency as well as
                // number of row and columns of specified matrix
                if (i == 0)
                    ci = 1 / Math.sqrt(m);
                else
                    ci = Math.sqrt(2) / Math.sqrt(m);

                if (j == 0)
                    cj = 1 / Math.sqrt(n);
                else
                    cj = Math.sqrt(2) / Math.sqrt(n);

                // sum will temporarily store the sum of
                // cosine signals
                sum = 0;
                for (k = 0; k < m; k++)
                {
                    for (l = 0; l < n; l++)
                    {
                        dct1 = sourceBlock.getBlockValueOnXY(k, l) *
                                Math.cos((2 * k + 1) * i * Math.PI / (2 * m)) *
                                Math.cos((2 * l + 1) * j * Math.PI / (2 * n));
                        sum = sum + dct1;
                    }
                }
                dct[i][j] = ci * cj * sum;
            }
        }
        return new DataBlock(dct);
    }
}
