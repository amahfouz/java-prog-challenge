package mahfouz.google.codejam.y2015.qual;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Solution for Infinite House of Pancakes.
 */
public final class InfiniteHouseOfPancakes {

    private final int caseNum;

    public InfiniteHouseOfPancakes(int caseNum, Scanner s) {
        this.caseNum = caseNum;
        int d = s.nextInt();

        int[] diners = new int[d];

        for (int i=0; i<d; i++) {
            int p = s.nextInt();
            diners[i] = p;
        }
        Arrays.sort(diners);

        int largestPlate = diners[diners.length - 1];
        if (largestPlate <= 3) {
            printSolution(largestPlate);
            return;
        }

        int curBest = largestPlate;

        // try all solutions
        for (int toEat = 2; toEat <= largestPlate; toEat++) {

            // break every column greater than 'toEat'
            int totalBreaks = 0;
            for (int cust = diners.length - 1; cust >= 0; cust--) {
                if (diners[cust] <= toEat)
                    break; // no breaking needed for shorter columns
                totalBreaks += diners[cust] / toEat;
                if (diners[cust] % toEat == 0)
                    totalBreaks--;
            }

            int total = toEat + totalBreaks;

            if (total < curBest)
                curBest = total;
        }
        printSolution(curBest);
    }

    private void printSolution(int solution) {
        System.out.println("Case #" + caseNum + ": " + solution);
    }

    // main

    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("/Users/amahfouz/Desktop/gcj.in"));

        int numCases = s.nextInt();

        for (int i = 0; i < numCases; i++) {
            new InfiniteHouseOfPancakes(i + 1, s);
        }
    }
}
