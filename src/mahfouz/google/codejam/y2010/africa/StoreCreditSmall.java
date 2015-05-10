/*
 * CONFIDENTIAL
 * Copyright 2013 Webalo, Inc.  All rights reserved.
 */

package mahfouz.google.codejam.y2010.africa;

import java.util.Scanner;

/**
 * Solution for Store Credit Google Code Jam problem.
 */
public final class StoreCreditSmall {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int numTestCases = scanner.nextInt();

        for (int i = 0; i < numTestCases; i++) {
            int credit = scanner.nextInt();
            int numItems = scanner.nextInt();

            int [] itemPrices = new int[numItems];
            for (int j = 0; j < numItems; j++) {
                itemPrices[j] = scanner.nextInt();
            }

            solveCase(i + 1, credit, itemPrices);
        }
    }

    private static void solveCase(int caseNum, int credit, int[] itemPrices) {
        for (int i = 0; i < itemPrices.length - 1; i++) {
            if (itemPrices[i] >= credit)
                continue;

            for (int j = i + 1; j < itemPrices.length; j++) {
                if (itemPrices[j] >= credit)
                    continue;

                if (itemPrices[i] + itemPrices[j] == credit) {
                    System.out.println
                        ("Case #" + caseNum + ": " + (i+1) + " " + (j+1));
                    return;
                }
            }
        }
        throw new IllegalStateException("No solution for case " + caseNum);
    }
}
