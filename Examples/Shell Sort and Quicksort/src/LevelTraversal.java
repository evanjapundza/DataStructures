import com.sun.source.tree.BinaryTree;

import java.util.*;

public class LevelTraversal <K extends Comparable<? super K>> {
    BinaryNode<K> root;
    BinaryNode<K> current;

    public LevelTraversal() {
        root = null;
        current = null;
    }

    public void build(K[] keys) {
        for (int i = 0; i < keys.length; i++)
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
        System.out.print("Level-order traversal: ");
        levelOrder(root);
    }

    public void traversePreOrder(BinaryNode<K> entry) {
        System.out.print(entry.getKey() + " ");
        if (entry.getLeft() != null) traversePreOrder(entry.getLeft());
        if (entry.getRight() != null) traversePreOrder(entry.getRight());
    }

    public void levelOrder(BinaryNode<K> entry) {
        //TODO: Implement pseudocode from PS2 Task A.1
        Queue<BinaryNode<K>> queue = new LinkedList<BinaryNode<K>>();
        queue.add(entry);
        while (!queue.isEmpty()) {
            BinaryNode<K> tempNode = queue.poll();
            System.out.print(tempNode.getKey() + " ");

            if (tempNode.getLeft() != null) {
                queue.add(tempNode.getLeft());
            }

            if (tempNode.getRight() != null) {
                queue.add(tempNode.getRight());
            }
        }
    }


    public static void main(String[] args) {
        LevelTraversal<Integer> tree = new LevelTraversal<Integer>();
        Integer[] keys = {2, 4, 6, 8, 10, 3, 5, 7, 9, 11, 12, -10, -20, 100};
        tree.build(keys);
        tree.display();

        System.out.println("\n");

        LevelTraversal<Integer> tree2 = new LevelTraversal<Integer>();
        Integer[] keys2 = {8,6,7,10,40,60,23,5};
        tree2.build(keys2);
        tree2.display();

        //TODO: Add another tree to test level-order traversal
    }
}