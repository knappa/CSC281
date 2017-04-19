/**
 * A binary tree implementation of a symbol table.
 *
 * @param <Key>   Type parameter for the keys, must be comparable
 * @param <Value> Type parameter for values
 * @author <a href="mailto:knapp@american.edu">Adam Knapp</a>
 */
public class RedBlackTree<Key extends Comparable<Key>, Value> {

    private static final boolean RED = false, BLACK = true;

    private Node root;

    /**
     * Tests
     *
     * @param args ignored
     */
    public static void main(String[] args) {

        RedBlackTree<Integer, String> tree = new RedBlackTree<>();

        // insert single character strings with random keys
        for (int i = 0; i < 13; i++)
            tree.insert((int) (1000 * Math.random()), "" + (char) ('a' + i));

        // print post-order traversal
        System.out.println("post-order");
        System.out.println(tree.postOrder());

        // print post-order traversal
        System.out.println("pre-order");
        System.out.println(tree.preOrder());

        // print in-order traversal
        System.out.println("in-order");
        System.out.println(tree.inOrder());
        
    }

    /**
     * @return String representation of the post-order traversal
     */
    @Override
    public String toString() {
        return this.postOrder();
    }

    /**
     * @return String rep of post-order traversal
     */
    public String postOrder() {
        if (root == null) return "Empty";
        else return root.postOrder();
    }

    /**
     * @return String rep of pre-order traversal
     */
    public String preOrder() {
        if (root == null) return "Empty";
        else return root.preOrder();
    }

    /**
     * @return String rep of in-order traversal
     */
    public String inOrder() {
        if (root == null) return "Empty";
        else return root.inOrder();
    }

    /**
     * @param k a key
     * @return <code>true</code> if the tree contains this key with a non-null value
     */
    public boolean contains(Key k) {
        return this.get(k) != null;
    }

    /**
     * Adds a new Key, Value pair into the tree. If there is already a value associated with the given key, the previous
     * value will be overridden
     *
     * @param newKey   key to insert
     * @param newValue value to associate with key
     */
    public void insert(Key newKey, Value newValue) {
        root = insert(newKey, newValue, root);
        root.color = BLACK;
    }

    private Node insert(Key newKey, Value newValue, Node head) {
        if (head == null) return new Node(newKey, newValue, RED);

        // compute comparison _once_
        int compare = newKey.compareTo(head.key);

        if (compare < 0)
            head.left = insert(newKey, newValue, head.left);
        else if (compare > 0)
            head.right = insert(newKey, newValue, head.right);
        else
            head.value = newValue;

        if (color(head.right) == RED && color(head.left) == BLACK)
            head = rotateLeft(head);

        // note that head.left may not be null after 1st test
        if (color(head.left) == RED && color(head.left.left) == RED)
            head = rotateRight(head);

        if (color(head.left) == RED && color(head.right) == RED)
            flipColors(head);

        return head;
    }

    /**
     * Rotates a pair left
     *
     * @param parent a node with a red right child
     * @return new head of this subtree
     */
    private Node rotateLeft(Node parent) {
        assert parent.right != null && parent.right.color == RED;

	// TODO: complete this
    }

    /**
     * Rotates a pair right
     *
     * @param parent a node with a red left child
     * @return new head of this subtree
     */
    private Node rotateRight(Node parent) {
        assert parent.left != null && parent.left.color == RED;

	// TODO: complete this
    }

    /**
     * Flip colors of a (black) node and its two red children
     *
     * @param node node to flip
     */
    private void flipColors(Node node) {
        assert node.left != null && node.left.color == RED;
        assert node.right != null && node.right.color == RED;

	// TODO: complete this
    }

    /**
     * @param node node to query
     * @return "color" of node or BLACK if node is null
     */
    private boolean color(Node node) {
        if (node == null) return BLACK; // default
        else return node.color;
    }

    /**
     * Retrieve the value associated with a key.
     *
     * @param searchKey key, whose value we will find
     * @return value associated with given key, <code>null</code> if <code>searchKey</code> is not present
     */
    public Value get(Key searchKey) {
        if (root == null)
            return null;
        else
            return root.get(searchKey);
    }

    private class Node {
        Key key;
        Value value;
        boolean color;
        Node left, right;

        Node(Key k, Value v, boolean c) {
            key = k;
            value = v;
            color = c;
        }

        Value get(Key searchKey) {
            if (searchKey.equals(key))
                return value;
            else if (searchKey.compareTo(key) < 0) {
                if (left != null) return left.get(searchKey);
                else return null;
            } else if (searchKey.compareTo(key) > 0) {
                if (right != null) return right.get(searchKey);
                else return null;
            } else
                return null;
        }

        @Override
        public String toString() {
            if (this.color == RED)
                return "r(" + key.toString() + "," + value.toString() + ")";
            else // (this.color == BLACK)
                return "b(" + key.toString() + "," + value.toString() + ")";
        }

        /**
         * @return post order traversal from this node
         */
        String postOrder() {
            String str = "";
            if (left != null) str += left.postOrder();
            if (right != null) str += right.postOrder();
            return str + this.toString() + " ";
        }

        /**
         * @return pre order traversal from this node
         */
        String preOrder() {
            String str = this.toString() + " ";
            if (left != null) str += left.preOrder();
            if (right != null) str += right.preOrder();
            return str;
        }

        /**
         * @return in order traversal from this node
         */
        String inOrder() {
            String str = "";
            if (left != null) str += left.inOrder();
            str += this.toString() + " ";
            if (right != null) str += right.inOrder();
            return str;
        }
    }

}
