package multi.converter.algorithm.steps.compression;

import multi.converter.algorithm.steps.AlgorithmStep;
import multi.converter.algorithm.steps.UnableToPerformStepException;
import multi.converter.data.HuffmanEncodedData;
import multi.converter.data.HuffmanNode;
import multi.converter.data.RLEEncodedData;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

import static multi.converter.data.HuffmanEncodedData.*;

public class HuffmanEncodingStep extends AlgorithmStep<HuffmanEncodedData, RLEEncodedData> {

    @Override
    public HuffmanEncodedData performAlgorithmStep(RLEEncodedData source) throws UnableToPerformStepException {
        String code =  source.getCode();

        // Step 1: Calculate character frequencies
        Map<Character, Integer> charFreqs = new HashMap<>();
        for (char c : code.toCharArray()) {
            charFreqs.put(c, charFreqs.getOrDefault(c, 0) + 1);
        }

        // Step 2: Build Huffman Tree
        HuffmanNode root = buildHuffmanTree(charFreqs);

        // Step 3: Generate Huffman Codes
        Map<Character, String> huffmanCodes = new HashMap<>();
        generateCodes(root, "", huffmanCodes);

        // Step 4: Encode text
        String encodedText = encodeText(code, huffmanCodes);

        // Step 5: Convert to byte array
        byte[] encodedBytes = binaryStringToBytes(encodedText);

        return new HuffmanEncodedData(encodedBytes, root);
    }

    public static byte[] binaryStringToBytes(String binary) {
        BitSet bitSet = new BitSet(binary.length());

        for (int i = 0; i < binary.length(); i++) {
            if (binary.charAt(i) == '1') {
                bitSet.set(i);
            }
        }

        return bitSet.toByteArray();
    }

}
