package multi.converter.data;

import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanEncodedData implements DataClass{
    HuffmanNode root;
    int length;
    byte[] code;

    public HuffmanEncodedData(byte[] code, int length, HuffmanNode root) {
        this.code = code;
        this.length = length;
        this.root = root;
    }

    public byte[] getCode() {
        return code;
    }

    public int getLength() {
        return length;
    }

    public HuffmanNode getRoot() {
        return root;
    }

    // Method to build Huffman Tree and generate Huffman Codes
    public static HuffmanNode buildHuffmanTree(Map<Character, Integer> charFreqs) {
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : charFreqs.entrySet()) {
            pq.offer(new HuffmanNode(entry.getKey(), entry.getValue(), null, null));
        }

        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();
            HuffmanNode parent = new HuffmanNode('\0', left.frequency + right.frequency, left, right);
            pq.offer(parent);
        }

        return pq.poll(); // Root of the Huffman Tree
    }

    public static void generateCodes(HuffmanNode node, String code, Map<Character, String> huffmanCodes) {
        if (node != null) {
            if (node.left == null && node.right == null) {
                huffmanCodes.put(node.character, code);
            } else {
                generateCodes(node.left, code + "0", huffmanCodes);
                generateCodes(node.right, code + "1", huffmanCodes);
            }
        }
    }

    // Method to decode encoded text using the Huffman Tree
    public static String decodeText(String encodedText, HuffmanNode root) {
        StringBuilder decoded = new StringBuilder();
        HuffmanNode current = root;
        for (char bit : encodedText.toCharArray()) {
            current = (bit == '0') ? current.left : current.right;

            if (current.left == null && current.right == null) {
                decoded.append(current.character);
                current = root; // Reset to root for next character
            }
        }
        return decoded.toString();
    }

    // Method to encode text using the generated Huffman codes
    public static String encodeText(String text, Map<Character, String> huffmanCodes) {
        StringBuilder encoded = new StringBuilder();
        for (char c : text.toCharArray()) {
            encoded.append(huffmanCodes.get(c));
        }
        return encoded.toString();
    }

}
