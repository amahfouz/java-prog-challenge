package mahfouz.google.codejam.y2017.round1a;

import java.util.HashSet;
import java.util.Scanner;

public final class ASmall {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int numCases = s.nextInt();
        s.nextLine();

        for (int i = 0; i < numCases; i++) {
            solveCase(i+1, s);
        }
    }

    private static void solveCase(int caseNum, Scanner s) {
        int rows = s.nextInt();
        int cols = s.nextInt();
        s.nextLine();

        char[][] cake = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            String rowStr = s.nextLine();
            for (int j = 0; j < rowStr.length(); j++) {
                cake[i][j] = rowStr.charAt(j);
            }
        }

        HashSet<Character> done = new HashSet<Character>();
        Cell curCell = new Cell(rows - 1, cols - 1);
        do {
            Cell cell = scanForNextLetter(curCell, cake, done);
            if (cell == null)
                break;

            char initial = cake[cell.row][cell.col];

            done.add(initial);

            Block block = new Block(cell, cell, initial);
            block = extendRight(block, cake);
            block = extendDown(block, cake);
            block = extendLeft(block, cake);
            block = extendUp(block, cake);

            fillBlock(block, cake);
        } while (true);


        System.out.println("Case #" + caseNum + ": ");
        printCake(cake);
    }

    private static void printCake(char[][] cake) {
        for (int i = 0; i < cake.length; i++) {
            for (int j = 0; j < cake[i].length; j++) {
                System.out.print(cake[i][j]);
            }
            System.out.println();
        }
    }

    private static void fillBlock(Block block, char[][] cake) {
        for (int i = block.topLeft.row; i <= block.botRight.row; i++) {
            for (int j = block.topLeft.col; j <= block.botRight.col; j++) {
                cake[i][j] = block.c;
            }
        }
    }

    private static Cell leaveBlock(Block block, char[][] cake) {

        for (int r = block.botRight.row; r >= 0; r--) {
            for (int c = block.topLeft.col - 1; c >= 0; c--) {
                if (cake[r][c] != block.c)
                    return new Cell(r, c);
            }
        }

        for (int c = block.botRight.col; c >= 0; c--) {
            for (int r = block.topLeft.row - 1; r >= 0; r--) {
                if (cake[r][c] != block.c)
                    return new Cell(r, c);
            }
        }
        return null;
    }

    private static Block extendLeft(Block block, char[][] cake) {
        int endCol = block.topLeft.col;
        boolean done = false;

        while (! done) {
            if (endCol == 0)
                break;

            int candidateCol = endCol - 1;
            for (int r = block.topLeft.row; r <= block.botRight.row; r++) {
               if (cake[r][candidateCol] != '?') {
                   done = true;
                   break;
               }
            }

            if (! done)
                endCol = candidateCol;
        }
        return new Block(new Cell(block.topLeft.row, endCol), block.botRight, block.c);
    }

    private static Block extendRight(Block block, char[][] cake) {
        int endCol = block.botRight.col;
        boolean done = false;

        while (! done) {
            if (endCol == cake[0].length - 1)
                break;

            int candidateCol = endCol + 1;
            for (int r = block.topLeft.row; r <= block.botRight.row; r++) {
               if (cake[r][candidateCol] != '?') {
                   done = true;
                   break;
               }
            }

            if (! done)
                endCol = candidateCol;
        }
        return new Block(block.topLeft, new Cell(block.topLeft.row, endCol), block.c);

    }

    private static Block extendUp(Block block, char[][] cake) {
        int endRow = block.topLeft.row;
        boolean done = false;

        while (! done) {
            if (endRow == 0)
                break;

            int candidateRow = endRow - 1;
            for (int c = block.topLeft.col; c <= block.botRight.col; c++) {
                if (cake[candidateRow][c] != '?') {
                    done = true;
                    break;
                }
            }

            if (! done)
                endRow = candidateRow;
        }
        return new Block(new Cell(endRow, block.topLeft.col), block.botRight, block.c);
    }

    private static Block extendDown(Block block, char[][]cake) {

        int endRow = block.botRight.row;

        boolean done = false;
        while (! done) {
            if (endRow == cake.length - 1)
                break;

            int candidateRow = endRow + 1;
            for (int c = block.topLeft.col; c <= block.botRight.col; c++) {
                if (cake[candidateRow][c] != '?') {
                    done = true;
                    break;
                }
            }

            if (! done)
                endRow = candidateRow;
        }
        return (endRow == block.botRight.row)
            ? block
            : new Block(block.topLeft, new Cell(endRow, block.botRight.col), block.c);
    }

    private static Cell scanForNextLetter(Cell curCell,
                                          char[][] cake,
                                          HashSet<Character> done) {
        int curRow = curCell.row;
        int curCol = curCell.col;
        if (curCol < 0) {
            curCol = cake[0].length - 1;
            curRow--;
        }

        while (curRow >= 0) {
            char letter = cake[curRow][curCol];
            if (letter != '?' && ! done.contains(letter))
                return new Cell(curRow, curCol);

            curCol--;
            if (curCol < 0) {
                curCol = cake[0].length - 1;
                curRow--;
            }
        }
        return null;
    }

    private static final class Cell {

        private final int row;
        private final int col;

        public Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static final class Block {

        private final Cell topLeft;
        private final Cell botRight;
        private final char c;

        public Block(Cell topLeft, Cell botRight, char c) {
            this.topLeft = topLeft;
            this.botRight = botRight;
            this.c = c;
        }
    }
}
