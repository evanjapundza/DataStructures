import java.util.ArrayList;
import java.util.*;

public class SearchTree <K extends Comparable<?super K>> {
    BinaryNode<K> root;
    BinaryNode<K> current;

    public SearchTree() {
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
        System.out.println("Depth first search (rec): " + DFSRec(root));
        System.out.println("Depth first search (iter): " + DFSIter(root));
        System.out.println("Breadth first search: " + BFS(root));
        System.out.println("Breadth first level search: " + BFSLevel(root));
    }
    public void traversePreOrder(BinaryNode<K> entry) {
        System.out.print(entry.getKey() + " ");
        if (entry.getLeft() != null) traversePreOrder(entry.getLeft());
        if (entry.getRight() != null) traversePreOrder(entry.getRight());
    }

    public ArrayList<K> DFSRec (BinaryNode<K> entry) {
        //TODO Task A
        //Implement DFS through recursion
        ArrayList<K> listOfSearch = new ArrayList<>();
        listOfSearch.add(entry.getKey());
        if (entry.getLeft() != null)  {
            listOfSearch.addAll(DFSRec(entry.getLeft()));
        }

        if (entry.getRight() != null) {
            listOfSearch.addAll(DFSRec(entry.getRight()));
        }

        return listOfSearch;
    }

    public ArrayList<K> DFSIter (BinaryNode<K> entry) {
        //TODO Task B
        //Implement DFS through iteration
        Stack<BinaryNode<K>> stack = new Stack<>();
        BinaryNode<K> current = root;
        ArrayList<K> listOfSearch = new ArrayList<>();

        stack.push(root);
        while(!stack.isEmpty()) {
            current = stack.pop();
            listOfSearch.add(current.getKey());

            if(current.getRight() != null) {
                stack.push(current.getRight());
            }
            if(current.getLeft() != null) {
                stack.push(current.getLeft());
            }
        }
        return listOfSearch;
    }

    public ArrayList<K> BFS (BinaryNode<K> entry) {
        //TODO Task C
        //Implement BFS
        //NOTE: This is the same thing as level-order traversal
        Queue<BinaryNode<K>> queue = new LinkedList<BinaryNode<K>>();
        queue.add(entry);
        ArrayList<K> listOfSearch = new ArrayList<>();
        while (!queue.isEmpty()) {
            BinaryNode<K> tempNode = queue.poll();
            //System.out.print(tempNode.getKey() + " ");
            listOfSearch.add(tempNode.getKey());

            if (tempNode.getLeft() != null) {
                queue.add(tempNode.getLeft());
            }

            if (tempNode.getRight() != null) {
                queue.add(tempNode.getRight());
            }
        }
        return listOfSearch;
    }

    public ArrayList<ArrayList<K>> BFSLevel (BinaryNode<K> entry){
        //TODO Task D
        //This is a more challenging way of showing BFS. Copy your code from above and modify to have each line of the BST be its own list
        //You will return the list of lists
        Queue<BinaryNode<K>> queue = new LinkedList<BinaryNode<K>>();
        queue.add(entry);
        ArrayList<ArrayList<K>> listOfSearch = new ArrayList<>();
        while (!queue.isEmpty()) {

            ArrayList<K> listOfSearch2 = new ArrayList<>();
            int maxLevel = queue.size();
            for(int i =0; i < maxLevel; i++) {

                if(!queue.isEmpty()) {
                    BinaryNode<K> tempNode = queue.poll();
                    if (tempNode.getLeft() != null) {
                        queue.add(tempNode.getLeft());
                    }

                    if (tempNode.getRight() != null) {
                        queue.add(tempNode.getRight());

                    }
                    listOfSearch2.add(tempNode.getKey());
                }
            }

            if(!listOfSearch2.isEmpty())
                listOfSearch.add(listOfSearch2);
        }
        return listOfSearch;
    }


    public static void main(String[] argv) {
        SearchTree<Integer> tree = new SearchTree<Integer>();
        Integer[] keys = {2, 4, 6, 8, 10, 3, 5, 7, 9, 11, 12, -10, -20, 100};
        tree.build(keys);
        tree.display();

        System.out.println();

        SearchTree<Integer> tree2 = new SearchTree<Integer>();
        Integer[] keys2 = {20, 30, 10, 15, 11, 36, 29};
        tree2.build(keys2);
        tree2.display();

        System.out.println();

        SearchTree<Integer> tree3 = new SearchTree<Integer>();
        Integer[] keys3 = {10,11,12,13,14,15,16,17,18,19,5,4,3,2,1};
        tree3.build(keys3);
        tree3.display();

        //TODO Task E
        //Create two original trees to test your functions on. Make one tree complete, and one incomplete(feel free to make this one a little crazy to prove your functions work).
    }
}