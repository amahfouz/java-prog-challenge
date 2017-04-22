package mahfouz.google.codejam.y2016.round1b;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public final class Technobabble {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int numCases = s.nextInt();
        s.nextLine();

        for (int i = 0; i < numCases; i++) {
            solveCase(i+1, s);
        }
    }

    private static void solveCase(int caseNum, Scanner s) {
        int n = s.nextInt();
        s.nextLine();

        Graph graph = new Graph();
        String [] pair;
        for (int i = 0; i < n; i++) {
            pair = s.nextLine().split(" ");
            graph.add("X" + pair[0], "Y" + pair[1]);
        }

        ArrayList<Node> nodesByRank = new ArrayList<Node>();
        nodesByRank.addAll(graph.nodes.values());

        Collections.sort(nodesByRank);

        int originalPairs = 0;
        for (Node node : nodesByRank) {
            if (node.visited)
                continue;

            node.visited = true;

            Node pairedNeighbor = null;

            if (node.neighbors.size() == 1) {
                pairedNeighbor = graph.get(node.neighbors.get(0));
            }
            else {
                for (String neighborName : node.neighbors) {
                    Node neighbor = graph.get(neighborName);
                    if (! neighbor.visited) {
                        pairedNeighbor = neighbor;
                        break;
                    }
                }
            }
            if (pairedNeighbor != null) {
                originalPairs++;
                pairedNeighbor.visited = true;
            }
        }

        System.out.println("Case #" + caseNum + ": " + (n - originalPairs));
    }

    private final static class Graph {

        private final HashMap<String, Node> nodes = new HashMap<String, Node>();

        public void add(String node1Name, String node2Name) {
            Node node1 = addOrGet(node1Name);
            node1.addNeighbor(node2Name);

            Node node2 = addOrGet(node2Name);
            node2.addNeighbor(node1Name);
        }

        public Node get(String nodeName) {
            return nodes.get(nodeName);
        }

        private Node addOrGet(String nodeName) {
            Node node = get(nodeName);
            if (node == null) {
                node = new Node(nodeName);
                nodes.put(nodeName, node);
            }
            return node;
        }
    }

    private final static class Node implements Comparable<Node> {

        private final ArrayList<String> neighbors = new ArrayList<String>();
        private final String name;

        private boolean visited = false;

        public Node(String name) {
            this.name = name;
        }

        public void addNeighbor(String neighborName) {
            neighbors.add(neighborName);
        }

        @Override
        public int compareTo(Node other) {
            return neighbors.size() - other.neighbors.size();
        }
    }
}
