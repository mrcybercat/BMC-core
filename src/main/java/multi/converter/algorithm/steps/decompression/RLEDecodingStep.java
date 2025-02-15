package multi.converter.algorithm.steps.decompression;

import multi.converter.algorithm.steps.AlgorithmStep;
import multi.converter.algorithm.steps.UnableToPerformStepException;
import multi.converter.data.DataBatch;
import multi.converter.data.RLEEncodedData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RLEDecodingStep extends AlgorithmStep<DataBatch, RLEEncodedData> {

    private int scaleFactorY = 1;
    private int scaleFactorU = 1;
    private int scaleFactorV = 1;

    private static int blockSize;
    private int originalWidth;
    private int originalHeight;


    public RLEDecodingStep(int blockSize, int originalWidth, int originalHeight) {
        this.blockSize = blockSize;
        this.originalWidth = originalWidth;
        this.originalHeight = originalHeight;
    }

    @Override
    public DataBatch performAlgorithmStep(RLEEncodedData source) throws UnableToPerformStepException {
        int[] data = decodeRLE(source.getCode());
        System.out.println("RLE data:" + Arrays.toString(data));
        //int effectiveSize = (originalWidth*originalHeight/blockSize);
        return new DataBatch(
                Arrays.copyOfRange(data, 0, (originalWidth*originalHeight)),
                Arrays.copyOfRange(data, (originalWidth*originalHeight) + 1, 2*(originalWidth*originalHeight)),
                Arrays.copyOfRange(data, 2*(originalWidth*originalHeight) + 1, data.length));
    }

    public static int[] decodeRLE(String encoded) {
        // Split by null character
        String[] parts = encoded.split("\0");

        // List to store the expanded values
        List<Integer> resultList = new ArrayList<>();

        // Iterate in pairs (value, count)
        for (int i = 0; i < parts.length - 1; i += 2) {
            int value = Integer.parseInt(parts[i]);  // Extract value
            int count = Integer.parseInt(parts[i + 1]);  // Extract count

            // Expand and add to the list
            for (int j = 0; j < count; j++) {
                resultList.add(value);
            }
        }
        // Convert List<Integer> to int[]
        return resultList.stream().mapToInt(Integer::intValue).toArray();
    }


}
