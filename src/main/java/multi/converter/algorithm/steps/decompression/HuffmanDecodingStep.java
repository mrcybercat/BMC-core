package multi.converter.algorithm.steps.decompression;

import multi.converter.algorithm.steps.AlgorithmStep;
import multi.converter.algorithm.steps.UnableToPerformStepException;
import multi.converter.data.HuffmanEncodedData;
import multi.converter.data.RLEEncodedData;

import java.util.BitSet;

import static multi.converter.data.HuffmanEncodedData.decodeText;

public class HuffmanDecodingStep extends AlgorithmStep<RLEEncodedData, HuffmanEncodedData> {

    @Override
    public RLEEncodedData performAlgorithmStep(HuffmanEncodedData source) throws UnableToPerformStepException {
        String encodedText =  bytesToBinaryString(source.getCode(), source.getLength());
        String decodedText = decodeText(encodedText, source.getRoot());
        return new RLEEncodedData(decodedText);
    }

    public static String bytesToBinaryString(byte[] bytes, int originalLength) {
        BitSet bitSet = BitSet.valueOf(bytes);
        StringBuilder binaryString = new StringBuilder(originalLength);

        for (int i = 0; i < originalLength; i++) {
            binaryString.append(bitSet.get(i) ? '1' : '0');
        }

        return binaryString.toString();
    }
}
