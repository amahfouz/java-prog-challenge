package mahfouz.hackerrank;

import java.util.Scanner;

/**
 * Solution for 
 * https://www.hackerrank.com/challenges/bowling-pins/problem
 */
public final class BowlingPins {
    
    private static final int MAX_PINS = 300;
    
    private static final class Solution {
        
        // number of contiguous pin segments 
        private final int numSegments;
        // number of segments that are formed of a single pin
        private final int numSingles;
        
        public Solution(Scanner s) {
            // consume number of pins, not needed and go to next line
            String s1 = s.nextLine();
            String config = s.nextLine();
            
//            System.out.println("s1="+s1);
//            System.out.println("conf="+config);
            
            int countSegments = 0;
            int countSingles = 0;
            
            boolean inSegment = false;
            int curSegmentLength = 0;
            
            for (int i = 0; i < config.length(); i++) {
                char c = config.charAt(i);
                if (c == 'I') {
                    if (! inSegment) {
                        inSegment = true;
                        countSegments++;
                    }
                    curSegmentLength++;
                }
                if (c == 'X' && inSegment) {
                    inSegment = false;
                    if (curSegmentLength == 1)
                        countSingles++;
                    curSegmentLength = 0;
                }
            }
            this.numSegments = countSegments;
            this.numSingles = countSingles;
        }
        
        // we win if there is an odd number of segments (to prove,
        // start with one segment --> win, two segments --> lose,
        // 3 segments --> win, etc.) Also, the number of single 
        // pins has to be odd. Again, to prove, start with a config
        // such as IXXIXXII --> lose, but IXXIXXI --> win, and so on)
        public boolean isWinning() {
            return numSegments % 2 == 0
                && numSingles % 2 != 0;
        }
    }
    
    /**
     * Algorithm to determine the win/lose outcome when a
     * single block of pins remain. The result of running
     * the algorithm is that when a single block remains,
     * regardless of the number of pins, it is a winning
     * position.
     */
    private static final class OfflineComputation {
        
        private final Boolean[] memoizedResults = new Boolean[MAX_PINS];
        
        /**
         * Determines whether the configuration of one block of pins of 
         * the specified length is winning or losing if it is our turn
         */
        private boolean isWinning(int numPins) {
            
            // you lose if nothing is left
            if (numPins == 0)
                return false;
            
            // take the remaining 1 or 2 pins and win
            if (numPins == 1 || numPins == 2) 
                return true;
            
            // assume no winning move has been found
            boolean foundWinningMove = false;
            
            // partition the row of pins into all possible two partitions
            
            // try partitions resulting from picking 1 pin and 2 pins
            outer:
            for (int pinsToPick = 1; pinsToPick <= 2 ; pinsToPick++) {
                int pinsAfterMove = numPins - pinsToPick;    
                int half = pinsAfterMove / 2;
                for (int i = 0; i <= half; i++) {
                    int fragment1 = i;
                    int fragment2 = pinsAfterMove - i;
                    
                    if (fragment1 == 1 && fragment2 == 2
                        || fragment2 == 1 && fragment1 == 2)
                        continue;
                    
                    boolean outcome1 = evalAndMemo(fragment1);
                    boolean outcome2 = evalAndMemo(fragment2);
                 
                    // heart of the logic: if we leave the opponent 
                    // with two winning positions (e.g. II and II) then
                    // he loses and we win. Similarly, if we leave him with
                    // two losing positions, he loses both and we win. 
                    // Finally, if we leave him with a winning plus losing
                    // positions (e.g. II and III) he wins and we lose
                    // hence the XNOR
                    foundWinningMove = ! (outcome1 ^ outcome2);
                    
                    if (foundWinningMove)
                        break outer; 
                }
            }
            
            return foundWinningMove;
        }
    
        // return the memoized results or compute it and store it
        private boolean evalAndMemo(int numPins) {
            if (memoizedResults[numPins] == null)
                memoizedResults[numPins] = isWinning(numPins);    
            return memoizedResults[numPins];
        }
    }
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int numCases = s.nextInt();
        s.nextLine(); // skip EOL chars
        
        System.out.println();
        for (int i = 0; i < numCases; i++) {
            Solution sol = new Solution(s);
            System.out.println(sol.isWinning() ? "WIN" : "LOSE");
        }
    }
//    public static void main(String[] args) {
//        OfflineComputation problem = new OfflineComputation();
//        int maxPins = 20;
//        problem.isWinning(maxPins);
//        
//        for (int i = 3; i < maxPins; i++) {
//            System.out.println(i + " " + problem.memoizedResults[i]);
//        }
//    }
}
