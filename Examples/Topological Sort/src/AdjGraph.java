// a simple implementation for graphs with adjacency lists

// Lab 11 starter file

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;
import java.util.*;

public class AdjGraph implements Graph {

    // is it a directed graph (true or false) :
    private boolean digraph;

    private int totalNodes;
    // all the nodes in the graph:
    private Vector<String> nodeList;

    private int totalEdges;
    // all the adjacency lists, one for each node in the graph:
    private Vector<LinkedList<Integer>>  adjList;

    // all the weights of the edges, one for each node in the graph:
    private Vector<LinkedList<Integer>> adjWeight;

    // every visited node:
    private Vector<Boolean> visited;

    // list of nodes pre-visit:
    private Vector<Integer> nodeEnum;

    public AdjGraph() {
        init();
    }

    public AdjGraph(boolean ifdigraph) {
        init();
        digraph = ifdigraph;
    }

    public void init() {
        nodeList = new Vector<String>();
        adjList = new Vector<LinkedList<Integer>>();
        adjWeight = new Vector<LinkedList<Integer>>();
        visited = new Vector<Boolean>();
        nodeEnum = new Vector<Integer>();
        totalNodes = totalEdges = 0;
        digraph = false;
    }

    // set vertices:
    public void setVertices(String[] nodes) {
        for (int i = 0; i < nodes.length; i ++) {
            nodeList.add(nodes[i]);
            adjList.add(new LinkedList<Integer>());
            adjWeight.add(new LinkedList<Integer>());
            visited.add(false);
            totalNodes ++;
        }
    }

    // add a vertex:
    public void addVertex(String label) {
        nodeList.add(label);
        visited.add(false);
        adjList.add(new LinkedList<Integer>());
        adjWeight.add(new LinkedList<Integer>());
        totalNodes ++;
    }

    public int getNode(String node) {
        for (int i = 0; i < nodeList.size(); i ++) {
            if (nodeList.elementAt(i).equals(node)) return i;
        }
        return -1;
    }

    // return the number of vertices:
    public int length() {
        return nodeList.size();
    }

    // add edge from v1 to v2:
    public void setEdge(int v1, int v2, int weight) {
        LinkedList<Integer> tmp = adjList.elementAt(v1);
        if (adjList.elementAt(v1).contains(v2) == false) {
            tmp.add(v2);
            adjList.set(v1,  tmp);
            totalEdges ++;
            LinkedList<Integer> tmp2 = adjWeight.elementAt(v1);
            tmp2.add(weight);
            adjWeight.set(v1,  tmp2);
        }
    }

    public void setEdge(String v1, String v2, int weight) {
        if ((getNode(v1) != -1) && (getNode(v2) != -1)) {
            // add edge from v1 to v2:
            setEdge(getNode(v1), getNode(v2), weight);
            // for undirected graphs, add edge from v2 to v1 as well:
            if (digraph == false) {
                setEdge(getNode(v2), getNode(v1), weight);
            }
        }
    }

    // keep track whether a vertex has been visited or not,
    //    for graph traversal purposes:
    public void setVisited(int v) {
        visited.set(v, true);
        nodeEnum.add(v);
    }

    public boolean ifVisited(int v) {
        return visited.get(v);
    }


    public LinkedList<Integer> getNeighbors(int v) {
        return adjList.get(v);
    }

    public int getWeight(int v, int u) {
        LinkedList<Integer> tmp = getNeighbors(v);
        LinkedList<Integer> weight = adjWeight.get(v);
        if (tmp.contains(u)) {
            return weight.get(tmp.indexOf(u));
        } else {
            return Integer.MAX_VALUE;
        }
    }



    // clean up before traversing the graph:
    public void clearWalk() {
        nodeEnum.clear();
        for (int i = 0; i < nodeList.size(); i ++)
            visited.set(i, false);
    }

    public void walk(String method) {
        clearWalk();
        // traverse the graph:
        for (int i = 0; i < nodeList.size(); i ++) {
            if (ifVisited(i) == false) {
                if (method.equals("BFS")) {
                    BFS(i);      // i is the start node
                } else if (method.equals("DFS")) {
                    DFS(i); // i is the start node
                } else {
                    System.out.println("unrecognized traversal order: " + method);
                    System.exit(0);
                }
            }
        }
        System.out.println(method + ":");
        displayEnum();
    }

    // Lab 11 TODO:
    //
    // write your method topologicalSortWithQueue() here.
    //

    public void topologicalSortWithQueue() {

        LinkedList<Integer> sortedVerts = new LinkedList<>();
        int[] indegrees = new int[totalNodes];
        Queue<Integer> eligible = new LinkedList<>();

        for(int i = 0; i < totalNodes; i++) {

            for(int j: adjList.get(i)){
                indegrees[j]++;
            }

        }

        for(int i = 0; i < indegrees.length; i++) {
            if(indegrees[i] == 0) {
                eligible.add(i);
            }
        }

        while(!eligible.isEmpty()) {
            int temp = eligible.peek();
            sortedVerts.add(eligible.poll());
            for(int i: getNeighbors(temp)) {
                indegrees[i]--;
                if(indegrees[i] == 0)
                    eligible.add(i);
            }
        }

        if(sortedVerts.size() == totalNodes) {
            for(int i: sortedVerts){
                System.out.print(nodeList.get(i)+" ");
            }
        }
        else {
            System.out.println("Solution not found");
        }
    }



    public void DFS(int v) {
        setVisited(v);
        LinkedList<Integer> neighbors = adjList.elementAt(v);
        for (int i = 0; i < neighbors.size(); i ++) {
            int v1 = neighbors.get(i);
            if (ifVisited(v1) == false) {
                DFS(v1);
            }
        }
    }

    public void BFS(int s) {
        ArrayList<Integer> toVisit = new ArrayList<Integer>();
        toVisit.add(s);
        while (toVisit.size() > 0) {
            int v = toVisit.remove(0);   // first-in, first-visit
            setVisited(v);
            LinkedList<Integer> neighbors = adjList.elementAt(v);
            for (int i = 0; i < neighbors.size(); i ++) {
                int v1 = neighbors.get(i);
                if ( (ifVisited(v1) == false) && (toVisit.contains(v1) == false) ) {
                    toVisit.add(v1);
                }
            }
        }
    }

    public void display() {
        System.out.println("total nodes: " + totalNodes);
        System.out.println("total edges: " + totalEdges);
    }

    public void displayEnum() {
        for (int i = 0; i < nodeEnum.size(); i ++) {
            System.out.print(nodeList.elementAt(nodeEnum.elementAt(i)) + " ");
        }
        System.out.println();
    }
    public static void main(String argv[]) {
        AdjGraph g1 = new AdjGraph(true);
        String[] nodes1 = {"A", "B", "C", "D", "E","F"};
        int weight = 1;
        g1.setVertices(nodes1);
        g1.setEdge("A", "C", weight);
        g1.setEdge("A", "E", weight);
        g1.setEdge("B", "C", weight);
        g1.setEdge("B", "F", weight);
        g1.setEdge("C", "F", weight);
        g1.setEdge("C", "D", weight);
        g1.setEdge("D", "F", weight);
        g1.setEdge("E", "F", weight);
        g1.walk("BFS");
        g1.displayEnum();

        System.out.println("-----");

        //second example: g2
        AdjGraph g2 = new AdjGraph(true);
        String[] nodes2 = {"a", "b", "c", "d", "e", "f"};
        g2.setVertices(nodes2);
        g2.setEdge("a", "b", 9);
        g2.setEdge("a", "f", 5);
        g2.setEdge("a", "e", 3);
        g2.setEdge("b", "c", 5);
        g2.setEdge("b", "f", 4);
        g2.setEdge("c", "d", 2);
        g2.setEdge("c", "f", 8);
        g2.setEdge("d", "f", 7);
        g2.setEdge("d", "e", 1);
        g2.setEdge("e", "f", 5);

        g2.topologicalSortWithQueue();

        //new graph 1
        AdjGraph g3 = new AdjGraph(true);
        String[] nodes3 = {"J1", "J2", "J3", "J4", "J5", "J6", "J7"};
        weight = 1;
        g3.setVertices(nodes3);
        g3.setEdge("J1", "J2", weight);
        g3.setEdge("J1", "J3", weight);
        g3.setEdge("J2", "J6", weight);
        g3.setEdge("J2", "J5", weight);
        g3.setEdge("J2", "J4", weight);
        g3.setEdge("J3", "J4", weight);
        g3.setEdge("J4", "J5", weight);
        g3.setEdge("J5", "J7", weight);

        System.out.println();
        g3.topologicalSortWithQueue();

        //new graph 2
        AdjGraph g4 = new AdjGraph(true);
        String[] nodes4 = {"A", "B", "C", "D", "E"};
        weight = 1;
        g4.setVertices(nodes4);
        g4.setEdge("A", "C", weight);
        g4.setEdge("B", "C", weight);
        g4.setEdge("C", "D", weight);
        g4.setEdge("C", "E", weight);
        g4.setEdge("D", "E", weight);
        g4.setEdge("A", "C", weight);

        System.out.println();
        g4.topologicalSortWithQueue();

        //new graph 3
        AdjGraph g5 = new AdjGraph(true);
        String[] nodes5 = {"A", "B", "C", "D", "E"};
        weight = 1;
        g5.setVertices(nodes5);
        g5.setEdge("A", "B", weight);
        g5.setEdge("B", "C", weight);
        g5.setEdge("C", "D", weight);
        g5.setEdge("D", "E", weight);
        g5.setEdge("E", "A", weight);

        System.out.println();
        g5.topologicalSortWithQueue();
    }


    // Lab 11 TODO: up above




} // end of class AdjGraph