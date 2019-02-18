package mahfouz.geeks4geeks;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Solution for 
 * https://practice.geeksforgeeks.org/problems/the-painters-partition-problem/0
 */
public final class PaintersPartition {

    private final int[] arr;
    private final int k;
    
    public PaintersPartition(int[] arr, int k) {
        this.arr = arr;
        this.k = k;
    }

    /**
     * Perform binary search over the range of possible solutions.
     */
    public int solve() {
        // upper and lower bounds on solution
        int hi = Arrays.stream(arr).reduce(Integer::sum).getAsInt();
        int lo = hi / k;
        
        int best = Integer.MAX_VALUE;
        
        while (hi >= lo) {

            int mid = lo + (hi - lo) / 2;
            
            if (isFeasible(mid)) {
                if (mid < best)
                    best = mid;
                
                hi = mid;
                
                if (hi == lo)
                    break;
            }
            else {
                lo = mid + 1;
            }
        }
        return best;
    }
    
    /**
     * Check if the specified candidate solution is feasible.
     */
    private boolean isFeasible(int portion) {
        int work = 0;
        int board = 0;
        int painter = 1;
        
        while (work <= portion && board < arr.length && painter <= k) {
            if (work + arr[board] <= portion) {
                // allocate more work to current painter
                work += arr[board];
                board++;
            }
            else {
                // move to next painter
                work = 0;
                painter++;
            }
        }
        // feasible if all boards have been allocated
        return board == arr.length;
    }
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int numCases = s.nextInt();
        
        for (int i = 0; i < numCases; i++) {
            int numPainters = s.nextInt();
            int numBoards = s.nextInt();
            
            int [] boards = new int[numBoards];
            for (int j = 0; j < boards.length; j++) {
                boards[j] = s.nextInt();
            }
            PaintersPartition problem 
                = new PaintersPartition(boards, numPainters);
            
            System.out.println(problem.solve());
        }
    }
}
