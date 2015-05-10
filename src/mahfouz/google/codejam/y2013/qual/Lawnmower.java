/*
 * CONFIDENTIAL
 * Copyright 2013 Webalo, Inc.  All rights reserved.
 */

package mahfouz.google.codejam.y2013.qual;

import java.util.Scanner;

/**
 * Solution for the "Lawnmower" problem (Qualification 2013)
 */
public final class Lawnmower {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();

        for (int i = 0; i < numCases; i++) {
            boolean result = solveCase(scanner);
            System.out.println
                ("Case #" + (i + 1) + ": " + (result ? "YES" : "NO"));
        }
    }

    private static boolean solveCase(Scanner scanner) {
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();

        int [][] lawn = new int[rows][cols];

        int maxRowHeights[] = new int[rows];
        int maxColHeights[] = new int[cols];

        // compute max height for each row

        for (int i = 0; i < rows; i++) {
            int maxValInRow = -1;
            for (int j = 0; j < cols; j++) {
                lawn[i][j] = scanner.nextInt();
                if (lawn[i][j] > maxValInRow)
                    maxValInRow = lawn[i][j];
            }
            maxRowHeights[i] = maxValInRow;
        }

        // compute max height for each col

        for (int j = 0; j < cols; j++) {
            int maxValInCol = -1;
            for (int i = 0; i < rows; i++) {
                if (lawn[i][j] > maxValInCol)
                    maxValInCol = lawn[i][j];
            }
            maxColHeights[j] = maxValInCol;
        }

        // check that each cell is no less than
        // the min of the max of its row and the
        // max of its col heights

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int minPossible = Math.min(maxRowHeights[i], maxColHeights[j]);
                if (lawn[i][j] < minPossible)
                    return false;
            }
        }

        return true;
    }
}
