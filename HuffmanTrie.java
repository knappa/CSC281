import java.util.HashMap;
import java.util.Map;

/**
 * A binary trie implementation of Huffman codes
 *
 * @author <a href="mailto:knapp@american.edu">Adam Knapp</a>
 * @version 1.0
 */
public class HuffmanTrie {

    private HuffmanNode root;

    /**
     * private default constructor so that this class cannot be instantiated except via the
     * <code>buildTrie</code> method or the <code>readBinaryRepresentation</code>
     */
    private HuffmanTrie() {
    }

    /**
     * Compresses or decompresses standard in to standard out according to command line argument
     *
     * @param args specifies compression (c) or decompression (d)
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Provide a single command line argument to say if");
            System.out.println("I should be compressing (c) or decompressing (d).");
            return;
        }
        switch (args[0]) {
            case "c":
                compressStdIn();
                break;
            case "d":
                decompressStdIn();
                break;
            default:
                System.out.println("Invalid argument");
        }
    }

    /**
     * Decompress standard input to standard output
     */
    private static void decompressStdIn() {
        // TODO: implement
    }

    /**
     * Compress standard input to standard output
     */
    private static void compressStdIn() {
        // TODO: implement
    }

    /**
     * Constructs a HuffmanTrie based on the characters and counts given
     *
     * @param characters an array of characters found in the given data file, each character should appear at most once
     * @param charCounts an array of counts of characters such that <code>charCounts[i]</code> is the number of
     *                   occurrences of the character <code>characters[i]</code>.
     * @return a new instance of <code>HuffmanTrie</code> with tree structure given by Huffman's algorithm.
     */
    public static HuffmanTrie buildTrie(char[] characters, int[] charCounts) {
        assert characters != null && charCounts != null && characters.length == charCounts.length;

        return null; // TODO: implement
    }

    /**
     * Creates an new instance of <code>HuffmanTrie</code> by reading standard input using <code>BinaryStdIn</code>
     *
     * @return a new instance of <code>HuffmanTrie</code>
     */
    public static HuffmanTrie readBinaryRepresentation() {
        return null; // TODO: implement
    }

    /**
     * Returns a <code>Map</code> giving Huffman code determined by this trie. Code words are represented by Strings of
     * 0's and 1's.
     *
     * @return <code>Map</code> giving Huffman code determined by this trie
     */
    public Map<Character, String> getCode() {
        Map<Character, String> code = new HashMap<>();
        root.fillInCode(code, "");
        return code;
    }

    /**
     * Reads an encoded character from standard input using <code>BinaryStdIn</code>
     *
     * @return decoded character. We let '\0' denote a special end-of-file character. The file _should_ end after this
     * character is returned and <code>readChar()</code> should not be called again.
     */
    public char readChar() {
        return '\0'; // TODO: implement
    }

    /**
     * Writes the binary representation of <code>c</code> to standard output using <code>BinaryStdOut</code>
     *
     * @param c character to write
     */
    public void writeChar(char c) {
        // TODO: implement
    }

    /**
     * @return String representation of the trie via its pre-order traversal
     */
    @Override
    public String toString() {
        return null; // TODO: implement
    }

    /**
     * Write the binary representation of this trie to standard output using <code>BinaryStdOut</code>
     */
    public void writeBinaryRepresentation() {
        // TODO: implement
    }

    /**
     * Inner class for nodes of the binary trie
     */
    private class HuffmanNode implements Comparable<HuffmanNode> {
        char data;
        int count;
        HuffmanNode left, right;

        /**
         * @return <code>true</code> is <code>this</code> is a leaf (i.e. no children) Otherwise, <code>false</code>
         */
        boolean isLeaf() {
            return false; // TODO: implement
        }

        @Override
        public int compareTo(HuffmanNode o) {
            return 0; // TODO: implement so that nodes with smallest counts have greatest priority
        }

        /**
         * populates <code>Map</code> of codewords recursively
         *
         * @param codeWords <code>Map</code> to populate
         * @param codeSoFar <code>String</code> containing prefix for all codewords under this node.
         */
        void fillInCode(Map<Character, String> codeWords, String codeSoFar) {
            if (this.isLeaf()) {
                codeWords.put(data, codeSoFar);
            } else {
                assert left != null && right != null;
                left.fillInCode(codeWords, codeSoFar + '0');
                right.fillInCode(codeWords, codeSoFar + '1');
            }
        }
    }

}
