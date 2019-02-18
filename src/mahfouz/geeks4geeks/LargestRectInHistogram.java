package mahfouz.geeks4geeks;

import java.util.Scanner;

import mahfouz.algo.tree.RangeMinSegmentTree;

/**
 * Solution for 
 * https://www.geeksforgeeks.org/largest-rectangular-area-in-a-histogram-set-1/
 */
public final class LargestRectInHistogram {

    private final int[] hist;
    private final RangeMinSegmentTree segTree;
    
    public LargestRectInHistogram(int[] hist) {
        this.hist = hist;
        this.segTree = new RangeMinSegmentTree.Builder(hist).getTree();
    }

    public long solve(int lo, int hi) {
        
        if (lo > hi)
            return 0;
        
        // termination condition - one element
        if (lo == hi)
            return hist[lo];
        
        int minIndex = findMinIndex(lo, hi);
        
        long minRect = hist[minIndex] * (hi - lo + 1);
        long left = solve(lo, minIndex-1);
        long right = solve(minIndex+1, hi);
        
        return Math.max(minRect, Math.max(left, right));
    }
    
    private int findMinIndex(int lo, int hi) {
        return segTree.query(lo, hi);
    }
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        // num test cases
        int n = s.nextInt();
        for (int i = 0; i < n; i++) {
            // num items
            int numItems = s.nextInt();
            int[] items = new int[numItems]; 
            for (int j = 0; j < items.length; j++) {
                items[j] = s.nextInt();
            }
            LargestRectInHistogram sol = new LargestRectInHistogram(items);
            System.out.println(sol.solve(0, items.length - 1));
            System.out.println();
        }
    }
}
