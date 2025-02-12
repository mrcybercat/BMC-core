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
        int i, j, k, l;
        int m = sourceBlock.getSize();
        int n = sourceBlock.getSize();

        // dct will store the discrete cosine transform
        double[][] dct = new double[m][n];

        double ck, cl, dct1, sum;

        for (i = 0; i < m; i++)
        {
            for (j = 0; j < n; j++)
            {
                // sum will temporarily store the sum of
                // cosine signals
                sum = 0;
                for (k = 0; k < m; k++)
                {
                    for (l = 0; l < n; l++)
                    {
                        // ci and cj depends on frequency as well as
                        // number of row and columns of specified matrix
                        if (k == 0)
                            ck = 1 / Math.sqrt(m);
                        else
                            ck = Math.sqrt(2) / Math.sqrt(m);

                        if (l == 0)
                            cl = 1 / Math.sqrt(n);
                        else
                            cl = Math.sqrt(2) / Math.sqrt(n);

                        dct1 = sourceBlock.getBlockValueOnXY(k, l) *
                                Math.cos((2 * k + 1) * i * Math.PI / (2 * m)) *
                                Math.cos((2 * l + 1) * j * Math.PI / (2 * n));

                        sum = sum + ck * cl * dct1;
                    }
                }
            }
        }
        return new DataBlock(dct);
    }

}
