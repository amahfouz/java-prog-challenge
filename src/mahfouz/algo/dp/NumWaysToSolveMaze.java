package mahfouz.algo.dp;

/**
 * Solution(s) to maze traversal problem.
 * https://www.geeksforgeeks.org/count-number-ways-reach-destination-maze/
 */
public final class NumWaysToSolveMaze {
    
    /**
     * Solve the maze recursively starting from position x and y.
     */
    public static int solveRecursive(int[][]maze, int x, int y) {
        
        // check termination (we arrived)
        if (x == maze.length - 1 && y == maze.length - 1)
            return 1;
        
        int right 
            = (x < maze.length - 1 && (maze[x+1][y] == 0))
            ? solveRecursive(maze, x+1, y)
            : 0;
            
        int down
            = (y < maze[x].length - 1 && maze[x][y+1] == 0)
            ? solveRecursive(maze, x, y+1)
            : 0;
        
        return right + down;
    }
    
    /**
     * Using dynamic programming.
     */
    public static int solveUsingDp(int[][]maze, int x, int y) {
        if (maze[0][0] == -1)
            return 0;
        
        int[][] paths = new int[maze.length][maze[0].length];
        paths[0][0] = 1;
        
        for (int i = 1; i < paths.length; i++) {
            paths[i][0] 
                = maze[i][0] == 0    
                    ? maze[i-1][0] == 0
                        ? 1
                        : 0
                    : 0;
        }
        
        for (int j = 1; j < paths[0].length; j++) {
            paths[0][j]
                = maze[0][j] == 0
                    ? maze[0][j-1] == 0
                        ? 1
                        : 0
                    : 0;
        }
        
        for (int i = 1; i < paths.length; i++) {
            for (int j = 1; j < paths[i].length; j++) {
                paths[i][j] 
                    = maze[i][j] == 0 
                    ? paths[i][j-1] + paths[i-1][j]
                    : 0;
            }
        }
        
        return paths[maze.length - 1][maze[maze.length-1].length - 1];
    }

    public static void main(String[] args) {
        int [][] maze = 
            {{0,  0, 0, 0},
             {0, -1, 0, 0},
             {-1, 0, 0, 0},
             {0,  0, 0, 0}};
        
        System.out.println(solveUsingDp(maze, 0, 0));
        System.out.println(solveRecursive(maze, 0, 0));
    }
    
//    private static void printMatrix(int [][] paths) {
//        for (int i = 0; i < paths.length; i++) {
//            for (int j = 0; j < paths[i].length; j++) {
//                System.out.print(paths[i][j] + " ");
//            }
//            System.out.println();
//        }
//    }
}
