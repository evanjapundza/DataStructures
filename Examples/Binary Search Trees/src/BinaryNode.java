// C343 / Spring 2022
// 
// a simple binary node class
// (albeit less simple than the SimpleBinaryNode class)

public class BinaryNode <K extends Comparable<?super K>> {

    private K key;                // key-only, no value
    private BinaryNode<K> left;   // left child
    private BinaryNode<K> right;  // right child

    private int size;          // the size (number of nodes)
    // of the subtree rooted at this node

    public BinaryNode(K k) {
        key = k;
        left = right = null;
        size = 1;
    }

    public void setLeft(BinaryNode<K> node) {
        left = node;
    }

    public void setRight(BinaryNode<K> node) {
        right = node;
    }

    public boolean isLeaf() {
        if (left == null && right == null)
            return true;
        else
            return false;
    }

    public BinaryNode<K> getLeft() {
        return left;
    }

    public BinaryNode<K> getRight() {
        return right;
    }

    public K getKey() {
        return key;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int newsize) {
        size = newsize;
    }

}