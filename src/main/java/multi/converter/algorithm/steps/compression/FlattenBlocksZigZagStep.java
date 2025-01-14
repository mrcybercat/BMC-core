package multi.converter.algorithm.steps.compression;

import multi.converter.algorithm.steps.AlgorithmStep;
import multi.converter.algorithm.steps.UnableToPerformStepException;
import multi.converter.data.BlockData;
import multi.converter.data.DataBatch;

public class FlattenBlocksZigZagStep extends AlgorithmStep<DataBatch, BlockData> {

    @Override
    public DataBatch performAlgorithmStep(BlockData source) throws UnableToPerformStepException {
        return null;
    }

    public static void zigZagMatrix(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int row = 0, col = 0;

        // Boolean variable that is true if we need to
        // increment 'row' value;
        // otherwise, false if we increment 'col' value.
        boolean rowInc = false;

        // Print the first half of the zig-zag pattern
        int minDim = Math.min(m, n);
        for (int len = 1; len <= minDim; ++len) {
            for (int i = 0; i < len; ++i) {
                System.out.print(mat[row][col] + " ");

                if (i + 1 == len) break;

                // If rowInc is true, increment row and decrement col;
                // otherwise, decrement row and increment col.
                if (rowInc) {
                    row++;
                    col--;
                } else {
                    row--;
                    col++;
                }
            }

            if (len == minDim) break;

            // Update row or col value based on the last increment
            if (rowInc) {
                row++;
                rowInc = false;
            } else {
                col++;
                rowInc = true;
            }
        }

        // Adjust row and col for the second half of the matrix
        if (row == 0) {
            if (col == m - 1) row++;
            else col++;
            rowInc = true;
        } else {
            if (row == n - 1) col++;
            else row++;
            rowInc = false;
        }

        // Print the second half of the zig-zag pattern
        int maxDim = Math.max(m, n) - 1;
        for (int len, diag = maxDim; diag > 0; --diag) {
            len = (diag > minDim) ? minDim : diag;
            for (int i = 0; i < len; ++i) {
                System.out.print(mat[row][col] + " ");

                if (i + 1 == len) break;

                // Update row or col value based on the last increment
                if (rowInc) {
                    row++;
                    col--;
                } else {
                    col++;
                    row--;
                }
            }

            // Update row and col based on position in the matrix
            if (row == 0 || col == m - 1) {
                if (col == m - 1) row++;
                else col++;
                rowInc = true;
            } else if (col == 0 || row == n - 1) {
                if (row == n - 1) col++;
                else row++;
                rowInc = false;
            }
        }
    }

}
