package mahfouz.hackerrank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public final class ManasaAndStones {

    private final int a;
    private final int b;
    private final int n;

    public ManasaAndStones(Scanner s) {
        this.n = s.nextInt();
        this.a = s.nextInt();
        this.b = s.nextInt();
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int numCases = s.nextInt();

        for (int i = 0; i < numCases; i++) {
            new ManasaAndStones(s).solveCase();
        }
    }

    private void solveCase() {

        Set<Integer> prev = new HashSet<Integer>();
        prev.add(0);
        Set<Integer> cur = new HashSet<Integer>();
        Set<Integer> tmp;

        int index = 1;

        while (index < n) {
            for (Integer possibility : prev) {
                cur.add(possibility + a);
                cur.add(possibility + b);
            }
            tmp = prev;
            prev = cur;
            cur = tmp;

            cur.clear();

            index++;
        }

        ArrayList<Integer> sortedSol = new ArrayList<Integer>();
        sortedSol.addAll(prev);
        Collections.sort(sortedSol);

        int numPossibilities = sortedSol.size();
        for (int i = 0; i < numPossibilities; i++) {
            System.out.print(sortedSol.get(i));
            if (i < numPossibilities - 1)
                System.out.print(' ');
        }
        System.out.println();
    }
}
