// Lab 12 starter file

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShortestPath{
    private HashMap<String, Integer> nodeValues;
    private HashMap<String, HashMap<String, Integer>>  edges;
    private ArrayList<String> visited;

    public ShortestPath() {
        nodeValues = new HashMap<>();
        edges = new HashMap<>();
        visited = new ArrayList<>();
    }

    public void reset(){
        visited.clear();
        for(Map.Entry<String, Integer> current: nodeValues.entrySet()){
            nodeValues.put(current.getKey(), Integer.MAX_VALUE);
        }
    }

    public void setNodes(String[] nodes) {
        for (int i = 0; i < nodes.length; i ++) {
            nodeValues.put(nodes[i],Integer.MAX_VALUE);
            edges.put(nodes[i],new HashMap<>());
        }
    }

    public void setEdge(String n1, String n2, int weight) {
        edges.get(n1).put(n2,weight);
        edges.get(n2).put(n1,weight);
    }

    public void graphOutput(String node){
        nodeValues.put(node,0);
        shortestPath(node);
        System.out.println("shortestPath outputs:");
        System.out.println(nodeValues);
        System.out.println(visited);
        reset();
        nodeValues.put(node, 0);
        MST(node);
        System.out.println("MST outputs:");
        System.out.println(nodeValues);
        System.out.println(visited);
        System.out.println();
    }

    public void shortestPath(String node){
        // TODO: TASK A
        int tempMin = Integer.MAX_VALUE;
        String tempKey = "";

        visited.add(node);

        for(Map.Entry<String, Integer> set2: edges.get(node).entrySet()) {
            if(nodeValues.get(node) + set2.getValue() < nodeValues.get(set2.getKey())) {
                nodeValues.put(set2.getKey(), nodeValues.get(node) + set2.getValue());
            }
        }

        for(Map.Entry<String,Integer> set: nodeValues.entrySet()) {
            if(set.getValue() < tempMin && !visited.contains(set.getKey())) {
                tempMin = set.getValue();
                tempKey = set.getKey();
            }
        }

        if (!tempKey.equals("")) {
            shortestPath(tempKey);
        }
    }

    public void MST(String node){
        // TODO: TASK B
        int tempMin = Integer.MAX_VALUE;
        String tempKey = "";

        visited.add(node);

        for(Map.Entry<String, Integer> set2: edges.get(node).entrySet()) {
            if(set2.getValue() < nodeValues.get(set2.getKey())) {
                nodeValues.put(set2.getKey(), set2.getValue());
            }
        }

        for(Map.Entry<String,Integer> set: nodeValues.entrySet()) {
            if(set.getValue() < tempMin && !visited.contains(set.getKey())) {
                tempMin = set.getValue();
                tempKey = set.getKey();
            }
        }

        if (!tempKey.equals("")) {
            MST(tempKey);
        }

    }

    public static void main(String args[]) {
        ShortestPath g1 = new ShortestPath();
        String[] nodes1 = {"A", "B", "C", "D", "E"};
        g1.setNodes(nodes1);
        g1.setEdge("A", "B", 1);
        g1.setEdge("B", "C", 3);
        g1.setEdge("C", "D", 5);
        g1.setEdge("A", "C", 2);
        System.out.println("Graph g1");
        g1.graphOutput("A");

        ShortestPath g2 = new ShortestPath();
        String[] nodes2 = {"a", "b", "c", "d", "e", "f"};
        g2.setNodes(nodes2);
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
        System.out.println("Graph g2");
        g2.graphOutput("a");

        // TODO: TASK C - 2 NON-TRIVIAL ADDITIONAL TEST CASES
        // ONE MUST HAVE AT LEAST 6 NODES AND ANOTHER MUST HAVE AT LEAST 8 NODES

        ShortestPath g3 = new ShortestPath();
        String[] nodes3 = {"A", "B", "C", "D","E","F"};
        g3.setNodes(nodes3);
        g3.setEdge("A", "C", 7);
        g3.setEdge("A", "E", 9);
        g3.setEdge("C", "D", 1);
        g3.setEdge("C", "B", 5);
        g3.setEdge("C", "F", 2);
        g3.setEdge("D", "F", 2);
        g3.setEdge("F", "B", 6);
        g3.setEdge("E", "F", 1);
        System.out.println("Graph g3");
        g3.graphOutput("A");

        ShortestPath g4 = new ShortestPath();
        String[] nodes4 = {"A", "B", "C", "D", "E","F","G","H"};
        g4.setNodes(nodes4);
        g4.setEdge("A", "C", 7);
        g4.setEdge("A", "B", 3);
        g4.setEdge("A", "E", 9);
        g4.setEdge("C", "D", 1);
        g4.setEdge("C", "B", 5);
        g4.setEdge("C", "F", 2);
        g4.setEdge("D", "F", 2);
        g4.setEdge("F", "B", 6);
        g4.setEdge("E", "F", 1);
        g4.setEdge("E", "G", 3);
        g4.setEdge("C", "G", 1);
        g4.setEdge("G", "H", 2);
        g4.setEdge("B", "H", 5);
        g4.setEdge("H", "C", 2);

        System.out.println("Graph g4");
        g4.graphOutput("A");
    }
}