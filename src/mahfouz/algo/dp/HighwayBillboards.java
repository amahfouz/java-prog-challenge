package mahfouz.algo.dp;

/**
 * Solution for 
 * https://www.geeksforgeeks.org/highway-billboard-problem/
 */
public final class HighwayBillboards {

    // locs is assumed to be in sorted in increasing order
    // revenue arrays is parrallel to locs and equal in length
    public int solve(int[] locs, int[] revenue, int t) {
        
        int[] accumRevenue = new int[locs.length];
        
        accumRevenue[locs.length - 1] = revenue[locs.length - 1];
        
        for (int i = locs.length - 2; i >= 0; i--) {
            int nearestNonConflictingToTheRight
                = findNonConflicting(locs, i, t);
            int revOfNextNonConflicting 
                = nearestNonConflictingToTheRight < 0
                ? 0
                : accumRevenue[nearestNonConflictingToTheRight];
            accumRevenue[i] = Math.max
               (accumRevenue[i+1], revenue[i] + revOfNextNonConflicting);
        }
        
        return accumRevenue[0];
    }
    
    private int findNonConflicting(int[] locs, int i, int t) {
        int loc = i+1;
        while (loc < locs.length) {
            if (locs[loc] - locs[i] > t)
                return loc;
            
            loc++;
        }
        return -1;
    }

    public static void main(String[] args) {
        HighwayBillboards problem = new HighwayBillboards();
        int solution = problem.solve
            (new int[] {6, 9, 12, 14}, 
             new int[] {5, 6, 3, 7}, 
             2);
        
        System.out.println(solution);
    }
}
