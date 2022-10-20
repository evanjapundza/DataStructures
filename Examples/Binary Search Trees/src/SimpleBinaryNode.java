// C343 / Spring 2022
//
// a very simple, starting binary node class;
// it's so simple, it's named SimpleBinaryNode.

public class SimpleBinaryNode <E extends Comparable<?super E>> {
    private E value;                     // value-only, no key
    private SimpleBinaryNode<E> left;    // left child
    private SimpleBinaryNode<E> right;   // right child

    public SimpleBinaryNode(E e) {
        value = e;
        left = right = null;
    }

    public void setLeft(SimpleBinaryNode<E> node) {
        left = node;
    }

    public void setRight(SimpleBinaryNode<E> node) {
        right = node;
    }

    public boolean findHelper(SimpleBinaryNode<E> currNode, E value) {
        boolean flag = false;
        if (currNode == value) {
            return true;
        }
        if(currNode.left != value) {
            findHelper(currNode.left, value);
        }
        if(currNode.right != value) {
            findHelper(currNode.right, value);
        }
        return false;
    }

    public boolean find(E q) {

        boolean flag = false;
        if (q.compareTo(this.value) == 0) {
            flag = true;
        }
        else {
            if(this.left != null) {
                flag = this.left.find(q);
            }
            if(flag == true) {
                return true;
            }
            if(this.right != null) {
                flag = this.right.find(q);
            }
        }
        return flag;
    }

    public static void main(String[] argv) {

        // TODO for Lab Task 05 Task A
        // see instructions

        SimpleBinaryNode<Integer> root = new SimpleBinaryNode<Integer>(7);
        SimpleBinaryNode<Integer> node1 = new SimpleBinaryNode<Integer>(29);
        SimpleBinaryNode<Integer> node2 = new SimpleBinaryNode<Integer>(36);
        SimpleBinaryNode<Integer> node3 = new SimpleBinaryNode<Integer>(69);
        SimpleBinaryNode<Integer> node4 = new SimpleBinaryNode<Integer>(706);
        SimpleBinaryNode<Integer> node5 = new SimpleBinaryNode<Integer>(500);
        SimpleBinaryNode<Integer> node6 = new SimpleBinaryNode<Integer>(301);
        SimpleBinaryNode<Integer> node7 = new SimpleBinaryNode<Integer>(2);
        SimpleBinaryNode<Integer> node8 = new SimpleBinaryNode<Integer>(366);
        SimpleBinaryNode<Integer> node9 = new SimpleBinaryNode<Integer>(290);

        root.setLeft(node1);
        root.setRight(node2);
        root.left.setLeft(node3);
        root.left.setRight(node4);
        root.right.setLeft(node5);
        root.right.setRight(node6);
        root.left.left.setLeft(node7);
        root.left.left.setRight(node8);
        root.left.right.setRight(node9);

        SimpleBinaryNode<Integer> root1 = new SimpleBinaryNode<Integer>(16);
        SimpleBinaryNode<Integer> node10 = new SimpleBinaryNode<Integer>(229);
        SimpleBinaryNode<Integer> node11 = new SimpleBinaryNode<Integer>(52);
        SimpleBinaryNode<Integer> node12 = new SimpleBinaryNode<Integer>(19);
        SimpleBinaryNode<Integer> node13 = new SimpleBinaryNode<Integer>(206);
        SimpleBinaryNode<Integer> node14 = new SimpleBinaryNode<Integer>(10);
        SimpleBinaryNode<Integer> node15 = new SimpleBinaryNode<Integer>(67);
        SimpleBinaryNode<Integer> node16 = new SimpleBinaryNode<Integer>(3);
        SimpleBinaryNode<Integer> node17 = new SimpleBinaryNode<Integer>(999);
        SimpleBinaryNode<Integer> node18 = new SimpleBinaryNode<Integer>(786);

        root1.setLeft(node10);
        root1.setRight(node11);
        root1.left.setLeft(node12);
        root1.left.setRight(node13);
        root1.right.setLeft(node14);
        root1.right.setRight(node15);
        root1.left.left.setLeft(node16);
        root1.left.left.setRight(node17);
        root1.left.left.left.setRight(node18);


        // find() needs to be implemented
        System.out.println("is 36 found in the tree: " + root.find(36));
        // find(36) should return true
        System.out.println("is 103 found in the tree: " + root.find(103));
        // find(103) should return false
        System.out.println("is 7 found in the tree: " + root.find(7));
        // find(7) should return true
        System.out.println("is 301 found in the tree: " + root.find(301));
        // find(301) should return true
        System.out.println("is 290 found in the tree: " + root.find(290));
        // find(290) should return true

        System.out.println("\n---------------------SECOND TREE---------------------\n");

        // find() needs to be implemented
        System.out.println("is 52 found in the tree: " + root1.find(52));
        // find(52) should return true
        System.out.println("is 33 found in the tree: " + root1.find(33));
        // find(33) should return false
        System.out.println("is 17 found in the tree: " + root1.find(17));
        // find(17) should return false
        System.out.println("is 16 found in the tree: " + root1.find(16));
        // find(16) should return true
        System.out.println("is 999 found in the tree: " + root1.find(999));
        // find(999) should return true


        // TODO for Lab Task 05 Task A
        // see instructions

    } // end of main()

} // end of SimpleBinaryNode class