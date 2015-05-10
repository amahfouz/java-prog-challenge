package mahfouz.google.codejam.y2011.round1c;

import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Solution for "Square Tiles" (Round 1A 2011)
 */
public final class SquareTiles {

    private final int rows;
    private final int cols;
    private final char[][] board;

    public static void main(String[] args) throws Exception{
        Scanner s = new Scanner
            (new File("C:\\Users\\amahfouz\\Downloads\\cj.in"));
        PrintStream out = new PrintStream
            (new File("C:\\Users\\amahfouz\\Desktop\\solution.txt"));

        int numCases = s.nextInt();

        for (int i = 0; i < numCases; i++) {
            new SquareTiles(s).solveCase(i + 1, out);
        }
    }

    private SquareTiles(Scanner s) {
        this.rows = s.nextInt();
        this.cols = s.nextInt();

        s.nextLine(); // advance past end of line
        String row;

        this.board = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            row = s.nextLine();
            for (int j = 0; j < cols; j++) {
                board[i][j] = row.charAt(j);
            }
        }
    }

    private void solveCase(int caseNum, PrintStream out) {
        boolean possible = true;

        // scan from the top left corner
        done:
        for (int i = 0; i < rows + cols - 1; i++) {
            for (int j = 0; j <= i; j++) {

                if (i - j >= rows || j >= cols)
                    continue;

                if (board[i - j][j] == '#') {
                    if ((j + 1 < cols) && (i - j + 1 < rows)
                        && (board[i - j][j + 1] == '#')
                        && (board[i - j + 1][j] == '#')
                        && (board[i - j + 1][j + 1] == '#')) {

                        board[i - j][j] = '/';
                        board[i - j][j + 1] = '\\';
                        board[i - j + 1][j] = '\\';
                        board[i - j + 1][j + 1] = '/';
                    }
                    else {
                        possible = false;
                        break done;
                    }
                }
            }
        }

        outputCase(out, caseNum, "");

        if (possible) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    out.print(board[i][j]);
                }
                out.println();
            }
        }
        else
            out.println("Impossible");
    }

    private static void outputCase(PrintStream out, int caseNum, String output) {
        out.println("Case #" + caseNum + ": " + output);
    }

}
