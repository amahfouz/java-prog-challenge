package mahfouz.algo.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * Computes the https://en.wikipedia.org/wiki/Longest_alternating_subsequence
 */
public final class LongestAlternatingSubsequence {

    /**
     * Compute the down-up sequence.
     */
    public static List<Integer> compute(int[] seq) {
        List<Integer> result = new ArrayList<Integer>();
        
        
        // flip this initial condition to compute up-down
        boolean isGoingUp = true;
        
        for (int i = 1; i < seq.length; i++) {
            
            if (isGoingUp && seq[i] < seq[i-1]) {
                isGoingUp = false;
                result.add(seq[i-1]);
            }
            else if (!isGoingUp && seq[i] > seq[i-1]) {
                isGoingUp = true;
                result.add(seq[i-1]);
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
     
        int[] input = new int[] { 1, -5, 2, 4, 3, 5, 4, 10, -1, 6, 7, 7, 1, 2, 1, 2 };
        List<Integer> result = compute(input);
        System.out.println(result);
    }
}
