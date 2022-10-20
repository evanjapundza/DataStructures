// C343 / Spring 2022
//
// a very simple, starting BST class;
// it's so simple, it's named SimpleBST.


public class SimpleBST <K extends Comparable<?super K>> {
    BinaryNode<K> root;
    BinaryNode<K> current;

    // TODO for Lab 05 Task B
    // "unbalanced" is used for balance checking:
    //    if a node is unbalanced, the tree will be unbalanced
    BinaryNode<K> unbalanced = null;

    public SimpleBST() {
        root = null;
        current = null;
    }
    public void build(K[] keys) {
        for (int i = 0; i < keys.length; i ++)
            insert(keys[i]);
    }
    public void insert(K k) {
        BinaryNode<K> tmpNode = new BinaryNode<K>(k);
        if (root == null) {
            root = current = tmpNode;
        } else {
            current = search(root, k);
            if (k.compareTo(current.getKey()) < 0)
                current.setLeft(tmpNode);
            else
                current.setRight(tmpNode);
        }
    }
    public BinaryNode<K> search(BinaryNode<K> entry, K k) {
        if (entry == null) {
            return null;
        } else {
            // update the size of the subtree by one:
            entry.setSize(entry.getSize() + 1);
            if (entry.isLeaf())
                return entry;
            if (k.compareTo(entry.getKey()) < 0) {
                if (entry.getLeft() != null)
                    return search(entry.getLeft(), k);
                else
                    return entry;
            } else {
                if (entry.getRight() != null)
                    return search(entry.getRight(), k);
                else
                    return entry;
            }
        }
    }
    public void display() {
        if (root == null) {
            return;
        }
        System.out.print("Pre-order traversal: ");
        traversePreOrder(root);
        System.out.println();
        System.out.print("In-order traversal: ");
        traverseInOrder(root);
        System.out.println();
        System.out.print("Post-order traversal: ");
        traversePostOrder(root);
        System.out.println();
    }
    public void traversePreOrder(BinaryNode<K> entry) {
        System.out.print(entry.getKey() + " ");
        if (entry.getLeft() != null) traversePreOrder(entry.getLeft());
        if (entry.getRight() != null) traversePreOrder(entry.getRight());
    }

    // TODO for Lab 05 Task B
    // implement balanceCheck(),
    //   and you may write heightAtNode(node) as helper function

    public boolean balanceCheck() {
        unbalanced = null;
        heightAtNode(root);
        if (unbalanced == null) {
            return true;
        }

        return false;
    }

    public int heightAtNode(BinaryNode<K> node) {
        if (node == null)
            return 0;

        int lh = heightAtNode(node.getLeft());
        int rh = heightAtNode(node.getRight());

        if (Math.abs(lh-rh) > 1) {
            unbalanced = node;
        }
        return 1 + Math.max(lh, rh);
    }

    // TODO for Lab 05 Task C
    // implement traverseInOrder()
    // implement traversePostOrder()

    public void traverseInOrder(BinaryNode<K> node) {
        if (node == null)
            return;

        traverseInOrder(node.getLeft());
        System.out.print(node.getKey() + " ");
        traverseInOrder(node.getRight());
    }

    public void traversePostOrder(BinaryNode<K> node) {

        if (node == null)
            return;

        traversePostOrder(node.getLeft());

        traversePostOrder(node.getRight());
        System.out.print(node.getKey() + " ");
    }


    public static void main(String[] argv) {
        SimpleBST<Integer> tree = new SimpleBST<Integer>();
        SimpleBST<Integer> tree2 = new SimpleBST<Integer>();
        SimpleBST<Integer> tree3 = new SimpleBST<Integer>();
        Integer[] keys = {2, 4, 6, 8, 10, 3, 5, 7, 9, 11, 12, -10, -20, 100};
        Integer[] keys2 = {6,10,4,2};
        Integer[] keys3 = {50,17,76,9,23,14,19,12,76,54,72,67};
        tree.build(keys);
        tree2.build(keys2);
        tree3.build(keys3);
        tree.display();
        tree2.display();
        tree3.display();
        System.out.println("Tree 1 is balanced: "+tree.balanceCheck());
        System.out.println("Tree 2 is balanced: "+tree2.balanceCheck());
        System.out.println("Tree 3 is balanced: "+tree3.balanceCheck());



        // TODO for Lab Task 05 Task B and C
        // see instructions

    }
}