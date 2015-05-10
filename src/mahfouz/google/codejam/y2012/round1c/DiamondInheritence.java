/*
 * CONFIDENTIAL
 * Copyright 2013 Webalo, Inc.  All rights reserved.
 */

package mahfouz.google.codejam.y2012.round1c;

import java.io.File;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import mahfouz.util.collection.Bft;
import mahfouz.util.collection.Digraph;
import mahfouz.util.collection.GNode;

/**
 * Solution of "Diamond Inheritence" problem (Round 1C 2012)
 */
public final class DiamondInheritence {

    public static void main(String[] args) throws Exception{
        Scanner s = new Scanner(new File("C:\\Users\\amahfouz\\Downloads\\cj.in"));
        PrintStream out = new PrintStream(new File("C:\\Users\\amahfouz\\Desktop\\solution.txt"));

        int numCases = s.nextInt();

        for (int i = 0; i < numCases; i++) {
            solveCase(i + 1, s, out);
        }
    }

    private static void solveCase(int caseNum, Scanner in, PrintStream out) {
        Digraph<Integer> graph = new Digraph();
        buildGraph(in, graph);

        boolean hasDiamond = false;
        for (int i = 1; i <= graph.getNumNodes(); i++) {
            GNode<Integer> nodeForClass = graph.getNodeByData(i);
            NodeHandler<Integer> handler = new NodeHandler<Integer>();

            Bft<Integer> bft
                = new Bft<Integer>(nodeForClass, handler);
            bft.traverse();

            if (handler.aNodeWasEncounteredTwice) {
                hasDiamond = true;
                break;
            }

            graph.resetFlags();
        }

        outputCase(out, caseNum, (hasDiamond ? "Yes" : "No"));
    }

    private static void buildGraph(Scanner in, Digraph graph) {
        int numClasses = in.nextInt();
        for (int i = 1; i <= numClasses; i++)
            graph.add(new GNode(i));

        for (int i = 1; i <= numClasses; i++) {
            int numParents = in.nextInt();
            GNode node = graph.getNodeByData(i);
            for (int j = 0; j < numParents; j++) {
                GNode parentNode = graph.getNodeByData(in.nextInt());
                node.addNext(parentNode);
            }
        }
    }

    private static void outputCase(PrintStream out, int caseNum, String output) {
        out.println("Case #" + caseNum + ": " + output);
    }

    private static final class NodeHandler<T>
        implements Bft.Receiver<T> {

        private boolean aNodeWasEncounteredTwice = false;

        public boolean handleNode(GNode<T> node) {
            List<GNode<T>> nexts = node.getNextNodes();
            for (GNode<T> next : nexts) {
                if (next.isEnqueued()) {
                    aNodeWasEncounteredTwice = true;
                    return false;
                }
            }
            return true;
        }
    }
 }
