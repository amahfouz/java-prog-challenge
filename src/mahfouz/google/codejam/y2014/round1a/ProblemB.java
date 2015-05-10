package mahfouz.google.codejam.y2014.round1a;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import mahfouz.util.collection.GNode;
import mahfouz.util.collection.Graph;

/**
 * Solution for problem B in round 1A.
 */
public final class ProblemB {

    public static void main(String[] args) throws Exception{
        Scanner s = new Scanner
            (new File("C:\\Users\\amahfouz\\Downloads\\cj.in"));
        PrintStream out = new PrintStream
            (new File("C:\\Users\\amahfouz\\Desktop\\solution.txt"));

        int numCases = s.nextInt();

        for (int i = 0; i < numCases; i++) {
            solveCase(i + 1, s, out);
        }
    }

    private static void solveCase(int caseNum, Scanner s, PrintStream out) {
        int numNodes = s.nextInt();

        Graph g = readGraph(s, numNodes);

        int min = numNodes;
        for (int i = 1; i <= numNodes; i++) {
            GNode node = g.getNodeByData(i);
            int trimScore = trimTree(node, null);
            if (trimScore < min) {
                min = trimScore;
            }
        }
        outputCase(out, caseNum, "" + min);
    }

    private static Graph readGraph(Scanner s, int numNodes) {
        Graph<Integer> graph = new Graph<Integer>();
        for (int i = 1; i <= numNodes; i++)
            graph.add(new GNode(i));

        for (int i = 0; i < numNodes - 1; i++) {
            int fromId = s.nextInt();
            int toId = s.nextInt();

            GNode<Integer> from = graph.getNodeByData(fromId);
            GNode<Integer> to   = graph.getNodeByData(toId);

            from.addNext(to);
            to.addNext(from);
        }
        return graph;
    }

    /**
     * Returns min number of nodes that need to be trimmed from (sub)tree
     * rooted at the specified node in order to turn it into a complete
     * binary tree.
     */
    private static int trimTree(GNode node, GNode parentIfNotRoot) {
        List<GNode> children
            = computeChildren(node.getNextNodes(), parentIfNotRoot);

        int numChildren = children.size();

        switch (numChildren) {
            case 0:  // no children, no trimming
                return 0;

            case 1: // trim the single child and remove all its descendants
                return 1 + countDescendants(children.get(0), node);

            case 2: // keep the two children but trim their descendants
                return trimTree(children.get(0), node)
                    +  trimTree(children.get(1), node);

            default: // trim all nodes except two

                List<Integer> trimScores = new ArrayList<Integer>();
                List<Integer> trimChilds = new ArrayList<Integer>();
                for (GNode child : children) {
                    trimScores.add(trimTree(child, node));
                    trimChilds.add(countDescendants(child, node));
                }

                // choose to keep

                int minSum = Integer.MAX_VALUE;
                for (int i = 0; i < numChildren - 1; i++) {
                    for (int j = i + 1; j < numChildren; j++) {

                        int sum = 0;
                        for (int k = 0; k < numChildren; k++) {
                            sum += ((k == i) || (k == j))
                                ? trimScores.get(k)
                                : trimChilds.get(k);
                        }
                        if (sum < minSum)
                            minSum = sum;
                    }
                }

                return (numChildren - 2) + minSum;
        }
    }

    private static int countDescendants(GNode node, GNode parent) {
        List<GNode> children
            = computeChildren(node.getNextNodes(), parent);

        int descendantsCount = children.size();
        for (GNode child : children)
            descendantsCount += countDescendants(child, node);

        return descendantsCount;
    }

    private static final List<GNode> computeChildren
        (List<GNode> adjacent, GNode parentIfNotRoot) {

        if (parentIfNotRoot == null)
            return adjacent;

        ArrayList<GNode> children = new ArrayList<GNode>();

        children.addAll(adjacent);
        children.remove(parentIfNotRoot);

        return children;
    }

    private static void outputCase(PrintStream out, int caseNum, String output) {
        out.println("Case #" + caseNum + ": " + output);
    }
}
