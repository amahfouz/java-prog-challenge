package mahfouz.google.codejam.y2015.round1a;

import java.util.Scanner;

public final class MushroomMonster {

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

        int[] seq = new int[n];
        for (int i = 0; i < n; i++) {
            seq[i] = s.nextInt();
        }

        int infinite = 0;
        int largestDrop = 0;

        int last = 0;
        for (int i = 0; i < n; i++) {
            int cur = seq[i];
            if (cur < last) {
                infinite += last - cur;
            }
            // record largest drop
            if (last - cur > largestDrop)
                largestDrop = last - cur;

            last = cur;
        }

        int rate = 0;

        for (int i = 0; i < n - 1; i++) {
            rate += Math.min(largestDrop, seq[i]);
        }

        System.out.println("Case #" + caseNum + ": " + infinite + " " + rate);
    }
}
