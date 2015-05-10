/*
 * CONFIDENTIAL
 * Copyright 2013 Webalo, Inc.  All rights reserved.
 */

package mahfouz.google.codejam.y2009.qual;

import java.util.Scanner;

/**
 * Solution for "Watersheds" problem (Qualification 2009)
 */
public final class Watersheds {

    private final int[][] elevations;
    private final char[][] basins;

    private final int rows;
    private final int cols;

    private char nextChar = 'a';

    private Watersheds(int caseNum, int height, int width, Scanner scanner) {
        this.rows = height;
        this.cols = width;

        this.elevations = new int[rows][cols];
        this.basins = new char[rows][cols];

        solve(caseNum, scanner);
    }

    private void solve(int caseNum, Scanner scanner) {

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++) {
                elevations[i][j] = scanner.nextInt();
                basins[i][j] = 0;
            }

        // label each cell

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                basins[i][j] = getLabelForCell(i, j);

        // output

        System.out.println("Case #" + caseNum + ":");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(basins[i][j]);
                if (j < cols - 1)
                    System.out.print(' ');
            }
            System.out.println();
        }
    }

    private char getLabelForCell(int i, int j) {
        if (basins[i][j] > 0)
            return basins[i][j];

        int min = elevations[i][j];
        int nextI = -1, nextJ = -1;

        if ((i > 0) && (elevations[i - 1][j] < min)) {
            min = elevations[i - 1][j];
            nextI = i - 1;
            nextJ = j;
        }
        if ((j > 0) && (elevations[i][j - 1] < min)) {
            min = elevations[i][j - 1];
            nextI = i;
            nextJ = j - 1;
        }
        if (i < rows - 1 && (elevations[i + 1][j] < min)) {
            min = elevations[i + 1][j];
            nextI = i + 1;
            nextJ = j;
        }
        if (j < cols - 1 && (elevations[i][j + 1] < min)) {
            min = elevations[i][j + 1];
            nextI = i;
            nextJ = j + 1;
        }

        basins[i][j] = (nextI < 0)
            ? nextChar++   // sink
            : getLabelForCell(nextI, nextJ);
        
        return basins[i][j]; 
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();

        for (int i = 0; i < numCases; i++) {
            int height = scanner.nextInt();
            int width = scanner.nextInt();

            new Watersheds(i + 1, height, width, scanner);
        }
    }
}
