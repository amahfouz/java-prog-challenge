package mahfouz.google.codejam.y2016.apac.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public final class BadHorse {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int numCases = s.nextInt();

        for (int i = 0; i < numCases; i++) {
            solveCase(i+1, s);
        }
    }

    private static void solveCase(int caseNum, Scanner s) {
        int m = s.nextInt();

        // adjacency list
        HashMap<String, Record> graph = new HashMap<String, Record>();
        for (int i = 0; i < m; i++) {
            String first = s.next();
            String second = s.nextLine().trim();

            add(graph, first, second);
            add(graph, second, first);
        }

        // traverse each connected component

        Set<String> players = graph.keySet();
        Iterator<String> iter = players.iterator();

        boolean can = true;
        while (iter.hasNext()) {
            String player = iter.next();

            // check if already processed
            Record r = graph.get(player);
            if (r.group != 0)
                continue;

            // assign each connected component
            if (! assignAndTraverse(player, 1, graph)) {
                can = false;
                break;
            }
        }
        System.out.println("Case #" + caseNum + ": " + (can ? "YES" : "NO"));
    }

    private static boolean assignAndTraverse
        (String player, int groupToAssign, HashMap<String, Record> graph) {

        Record r = graph.get(player);
        if (r.group != 0)
            return (groupToAssign == r.group);

        // group unassigned - assign it

        r.group = groupToAssign;

        // assign foes to the other group

        int foeGroup = -groupToAssign;

        for (String foe : r.foes) {
            if (! assignAndTraverse(foe, foeGroup, graph))
                return false;
        }

        // all succeeded
        return true;
    }

    private static void add(HashMap<String, Record> graph,
                            String player,
                            String foe) {
        Record r = graph.get(player);
        if (r == null) {
            r = new Record();
            graph.put(player, r);
        }
        r.foes.add(foe);
    }

    //
    // Nested
    //

    private static final class Record {

        // group is either -1 or 1, and 0 is unassigned
        private int group = 0;
        private ArrayList<String> foes = new ArrayList<String>();

        public String toString() {
            return "Group = " + group + ", " + foes.toString();
        }
    }
}
