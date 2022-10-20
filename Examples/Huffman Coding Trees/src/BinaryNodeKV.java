// C343
//
// a binary node class with Key and Value

public class BinaryNodeKV<Key, E> {
    private Key key;                      // the Key for this node
    private E value;                      // the Value for this node
    private BinaryNodeKV<Key, E> left;    // the left child
    private BinaryNodeKV<Key, E> right;   // the right child

    public BinaryNodeKV(Key k, E e) {
        key = k;
        value = e;
        left = right = null;
    }

    public Key getKey() { return key; }   // getters, setters, etc.
    public E getValue() { return value; }
    public void setKey(Key k) { key = k; }
    public void setValue(E e) { value = e; }
    public void setLeft(BinaryNodeKV<Key, E> node) { left = node; }
    public void setRight(BinaryNodeKV<Key, E> node) { right = node; }
    public void removeLeft() { left = null; }
    public void removeRight() { right = null; }
    public BinaryNodeKV<Key, E> getLeft() { return left; }
    public BinaryNodeKV<Key, E> getRight() { return right; }

    public boolean isLeaf() {       // test if node is leaf
        if ((left == null) && (right == null))
            return true;
        else
            return false;
    }

    public String inorder() {       // inorder traversal description
        String tmp = "";
        if (left != null) {
            tmp = tmp + left.inorder();
        }
        tmp = tmp + "["+ key + ":" + value + "]";
        if (right != null) {
            tmp = tmp + right.inorder();
        }
        return tmp;
    }

} // end of class BinaryNodeKV