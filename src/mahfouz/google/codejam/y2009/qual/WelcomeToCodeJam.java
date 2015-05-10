/*
 * CONFIDENTIAL
 * Copyright 2013 Webalo, Inc.  All rights reserved.
 */

package mahfouz.google.codejam.y2009.qual;

import java.util.Scanner;

/**
 * Solution for "Welcome to Code Jam" problem (Qualification 2009)
 */
public final class WelcomeToCodeJam {

    private static final String SUB = "welcome to code jam";
    private static final long MAX_COUNT = 10000;

    private static void solve(int caseNum, String main, String sub) {

        int rows = sub.length() + 1;
        int cols = main.length() + 1;

        // sol[i][j] holds the number of occurrences of the
        // first i chars from 'sub' in the first j chars of
        // 'main' (row 0 and column 0 are extra to make the
        // code simpler)

        long[][] sol = new long[rows][cols];

        for (int j = 0; j < cols; j++)
            sol[0][j] = 0;

        for (int i = 0; i < rows; i++)
            sol[i][0] = 0;

        // if char i of 'sub' matches char j of 'main'
        // then it can be appended to any of the matches
        // in of the first i - 1 chars of sub found in the
        // first j - 1 of main. This in addition to all of
        // occurrences of the first i chars of sub already
        // matched in the j - 1 chars of main

        // edge case is the first char in sub when found in
        // first j chars of main we just add one to the
        // number of occurrences of that char in the first
        // j - 1 chars of main

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                sol[i][j] = (i > j)
                     ? 0
                     : (sub.charAt(i - 1) == main.charAt(j - 1))
                         ? sol[i][j - 1] +
                              ((i - 1 == 0)
                                 ? 1
                                 : sol[i - 1][j - 1])
                         : sol[i][j - 1];

                // keep only 4 least significant digits - avoid overflow

                if (sol[i][j] > MAX_COUNT)
                    sol[i][j] %= MAX_COUNT;
            }
        }

        System.out.format
            ("Case #%d: %04d%n", caseNum, sol[rows - 1][cols - 1] % 10000);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();

        scanner.nextLine();

        for (int i = 0; i < numCases; i++) {
            solve(i + 1, scanner.nextLine(), SUB);
        }
    }
}
