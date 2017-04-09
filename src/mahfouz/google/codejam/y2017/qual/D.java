package mahfouz.google.codejam.y2017.qual;

import java.util.Scanner;

public final class D {

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
        int m = s.nextInt();
        s.nextLine();

        char[] firstLine = new char[n+1];
        int[] preCols = new int[m];

        // read first line
        for (int i = 0; i < m; i++) {
            String line = s.nextLine();
            String[] triad = line.split(" ");
            char model = triad[0].charAt(0);
            int r = Integer.parseInt(triad[1]);
            int c = Integer.parseInt(triad[2]);

            if (r != 1)
                throw new IllegalArgumentException();

            preCols[i] = c;
            firstLine[c] = model;
        }

        char[][] a = new char[n+1][n+1];

        int maxValue = -1;
        StringBuffer solutionBuf = new StringBuffer();

        if (preCols.length == 0) {
            // no pre-filled cells
            for (int anchorCol = 1; anchorCol < a.length; anchorCol++) {
                a[1][anchorCol] = 'o';
                solve(a, anchorCol);

                int value = eval(a);

                if (value > maxValue) {
                    maxValue = value;
                    solutionBuf.setLength(0);
                    solutionBuf.append(maxValue).append(" ");
                    printSolution(a, firstLine, solutionBuf);
                }
                clear(a);
            }
        }
        else
             // consider all possibilities of first row
            for (int perm = 0; perm < preCols.length; perm++) {

                // copy first row selecting position for 'o'
                for (int j = 1; j < firstLine.length; j++) {
                    if (firstLine[j] != 0)
                        a[1][j] = (j == preCols[perm])
                            ? 'o'
                            : '+';
                }
                // check validity of first row
                int numOandX = 0;
                for (int col = 1; col < a.length; col++) {
                    if (a[1][col] == 'o' || a[1][col] == 'x')
                        numOandX++;
                }

                // skip if more than one
                if (numOandX > 1)
                    continue;

                // solve
                solve(a, preCols[perm]);

                int value = eval(a);

                if (value > maxValue) {
                    maxValue = value;
                    solutionBuf.setLength(0);
                    solutionBuf.append(maxValue).append(" ");
                    printSolution(a, firstLine, solutionBuf);
                }

                clear(a);
            }

        System.out.println("Case #" + caseNum + ": " + solutionBuf.toString());
    }

    private static void printSolution(char[][] a, char[] firstLine,
                                      StringBuffer solutionBuf) {
        StringBuffer rowsBuf = new StringBuffer();
        int numMods = 0;

        // count mods in first line
        for (int c = 1; c < firstLine.length; c++) {
            if (firstLine[c] != a[1][c]) {
                numMods++;
                rowsBuf.append(a[1][c]).append(" 1 ").append(c).append("\n");
            }
        }

        // count mods in rest of array
        for (int i = 2; i < a.length; i++) {
            for (int j = 1; j < a.length; j++) {
                if (a[i][j] != 0) {
                    numMods++;
                    rowsBuf.append(a[i][j]).append(' ').append(i).append(' ').append(j).append("\n");
                }
            }
        }

        solutionBuf.append(numMods).append("\n");
        if (rowsBuf.length() > 0)
            rowsBuf.deleteCharAt(rowsBuf.length() - 1);
        solutionBuf.append(rowsBuf);
    }

    private static void clear(char[][] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = 1; j < a.length; j++) {
                a[i][j] = 0;
            }
        }
    }

    private static int eval(char a[][]) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (a[i][j] == 'x' || a[i][j] == '+')
                    sum++;
                else if (a[i][j] == 'o')
                    sum += 2;
            }
        }
        return sum;

    }

    private static void solve(char a[][], int anchorOCol) {
        int l = a.length;

        populateOs(a, anchorOCol);

        // traverse diagonals adding +'s
        for (int i = 1; i < l; i++) {
            for (int j = 1; j < l; j++) {
                if (a[i][j] != 0)
                   continue;

                if (freeDiag(a, i, j))
                    a[i][j] = '+';
            }
        }

        // place x in intersection of empty rows x columns
        for (int i = 1; i < l; i++) {
            if (! allXRow(a, i))
                continue;

            for (int j = 1; j < l; j++) {
                if (! allXCol(a, j))
                    continue;

                // intersection of empty row and empty column
                a[i][j] = 'x';
            }
        }
    }

    private static void populateOs(char[][] a, int anchorOCol) {
        int l = a.length;

        int cStart = anchorOCol + 2;
        // add an o to every row if possible
        for (int r = 2; r < l; r++) {
            // try every column in the row
            for (int c = cStart; c < l + cStart; c++) {
                int cMod = c % l;

                if (cMod == 0)
                    continue;

                if (freeCol(a, cMod) && freeDiag(a, r, cMod)) {
                    a[r][cMod] = 'o';
                    cStart = cMod+2;
                    break;
                }
            }
        } // we have maxed out the o's
    }

    private static boolean freeCol(char a[][], int c) {
        for (int i = 1; i < a.length; i++) {
            if (a[i][c] != 0)
                return false;
        }
        return true;
    }

    private static boolean allXCol(char a[][], int c) {
        for (int i = 1; i < a.length; i++) {
            if (a[i][c] != 0 && a[i][c] != '+')
                return false;
        }
        return true;
    }

    private static boolean allXRow(char a[][], int r) {
        for (int j = 1; j < a.length; j++) {
            if (a[r][j] != 0 && a[r][j] != '+')
                return false;
        }
        return true;
    }

    private static boolean freeDiag(char a[][], int r, int c) {
        int i = r+1; int j = c+1;

        // down right
        while (i < a.length && j < a.length) {
            if (a[i][j] != 0)
                return false;

            i++; j++;
        }

        // up right
        i = r - 1;
        j = c + 1;

        while (i >= 1 && j < a.length) {
            if (a[i][j] != 0)
                return false;

            i--; j++;
        }

        // down left
        i = r + 1;
        j = c - 1;

        while (i < a.length && j >= 1) {
            if (a[i][j] != 0)
                return false;

            i++; j--;
        }

        // up left
        i = r - 1;
        j = c - 1;

        while (i >= 1 && j >= 1) {
            if (a[i][j] != 0)
                return false;

            i--; j--;
        }

        return true;
    }
}
