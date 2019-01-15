package mahfouz.hackerrank;

/**
 * Solution for 
 * https://www.geeksforgeeks.org/policemen-catch-thieves/
 */
public final class PolicemenCatchThieves {

    private final char[] lineup;
    private final int k;
    
    public PolicemenCatchThieves(char[] lineup, int k) {
        this.lineup = lineup;
        this.k = k;
    }

    public int solve() {
        int thief = findNext(0, 'T');
        int police = findNext(0, 'P');
        
        int caught = 0;
        
        while (police >= 0 && thief >= 0) {
            if (Math.abs(police - thief) <= k) {
                caught++;
                
                // advance both
                thief = findNext(thief + 1, 'T');
                police = findNext(police + 1, 'P');
            }
            else {
                // advance the min of the two
                if (thief < police)
                    thief = findNext(thief + 1, 'T');
                else 
                    police = findNext(police + 1, 'P');
            }
        }
        
        return caught;
    }
    
    private int findNext(int start, char target) {
        int current = start;
        while (current < lineup.length) {
            if (lineup[current] == target)
                return current;
            current++;
        }
        return -1;
    }
    
    public static void main(String[] args) {
        PolicemenCatchThieves solution = new PolicemenCatchThieves
            (new char[]{'P', 'T', 'P', 'T', 'T', 'P'}, 3);
        
        System.out.println(solution.solve());
    }
}
