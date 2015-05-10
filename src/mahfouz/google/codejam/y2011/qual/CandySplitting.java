/*
 * CONFIDENTIAL
 * Copyright 2013 Webalo, Inc.  All rights reserved.
 */

package mahfouz.google.codejam.y2011.qual;

import java.io.File;
import java.util.Scanner;

/**
 * Solution of "Candy Splitting" problem (Qualification 2011).
 */
public final class CandySplitting {

    public static void main(String[] args) throws Exception{
        Scanner s = new Scanner(new File("C:\\Users\\amahfouz\\Downloads\\cj.in"));

        int numCases = s.nextInt();

        for (int i = 0; i < numCases; i++) {
            solveCase(i + 1, s);
        }
    }

    private static void solveCase(int caseNum, Scanner s) {
        int numCandies = s.nextInt();

        int xor = 0;
        int minValue = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 0; i < numCandies; i++) {
            int candy = s.nextInt();

            xor ^= candy;
            sum += candy;

            if (candy < minValue)
                minValue = candy;
        }

        // split is only possible if there are two equal binary
        // values that have exact same bits (i.e. whose xoring
        // would result in a zero). If so, the biggest pile is
        // achieved by excluding just the candy with smallest
        // value from the full list

        String result = (xor != 0)
            ? "NO"
            : String.valueOf(sum - minValue);

        System.out.println("Case #" + caseNum + ": " + result);
    }
}
