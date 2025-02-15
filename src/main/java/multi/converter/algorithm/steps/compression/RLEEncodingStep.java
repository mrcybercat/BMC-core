package multi.converter.algorithm.steps.compression;

import multi.converter.algorithm.steps.AlgorithmStep;
import multi.converter.algorithm.steps.UnableToPerformStepException;
import multi.converter.data.DataBatch;
import multi.converter.data.RLEEncodedData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// int[] -> String -> byte[]

public class RLEEncodingStep extends AlgorithmStep<RLEEncodedData, DataBatch> {

    @Override
    public RLEEncodedData performAlgorithmStep(DataBatch source) throws UnableToPerformStepException {
        System.out.println("RLE: source.getArrY() = " + Arrays.toString(source.getArrY()));


        System.out.println("RLE: code = " +
                createRLECode(rleEncode(source.getArrY())) +
                        createRLECode(rleEncode(source.getArrU())) +
                        createRLECode(rleEncode(source.getArrV()))
        );

        return new RLEEncodedData(
                    createRLECode(rleEncode(source.getArrY())) +
                    createRLECode(rleEncode(source.getArrU())) +
                    createRLECode(rleEncode(source.getArrV()))
        );
    }

    private String createRLECode(List<int[]> rleData){
        StringBuilder code = new StringBuilder();
        for (int[] pair : rleData) {
            code.append(pair[0]).append("\0").append(pair[1]).append("\0");
        }
        return code.toString();
    }

    private List<int[]> rleEncode(int[] data) {
        List<int[]> rleEncoding = new ArrayList<>();
        int count = 1;

        for (int i = 0; i < data.length; i++) {
            int value = data[i];

            if (i + 1 < data.length && value == data[i + 1]) {
                count++;
            } else {
                rleEncoding.add(new int[]{value, count});
                count = 1;
            }
        }

        return rleEncoding;
    }
}
