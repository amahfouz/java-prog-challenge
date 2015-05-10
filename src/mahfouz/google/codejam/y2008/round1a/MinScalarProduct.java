/*
 * CONFIDENTIAL
 * Copyright 2013 Webalo, Inc.  All rights reserved.
 */

package mahfouz.google.codejam.y2008.round1a;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * Solution for "Minimum Scalar Product" (Round 1A 2008)
 */
public final class MinScalarProduct {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int numTestCases = scanner.nextInt();

        for (int i = 0; i < numTestCases; i++) {
            int numItems = scanner.nextInt();

            int[] x = new int[numItems];
            Integer[] y = new Integer[numItems];

            for (int j = 0; j < numItems; j++) {
                x[j] = scanner.nextInt();
            }
            for (int j = 0; j < numItems; j++) {
                y[j] = scanner.nextInt();
            }

            solveCase(i + 1, x, y);
        }
    }

    private static void solveCase(int caseNum, int[] x, Integer[] y) {
        Arrays.sort(x);
        Arrays.sort(y, Collections.reverseOrder());

        long product = 0;
        for (int i = 0; i < x.length; i++) {
            product = product + ((long)x[i] * (long)y[i]);
        }

        System.out.println("Case #" + caseNum + ": " + product);
    }
}
