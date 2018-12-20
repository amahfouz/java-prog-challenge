package mahfouz.algo.dp;

/**
 * Solve the min number of coins problem
 * https://www.geeksforgeeks.org/find-minimum-number-of-coins-that-make-a-change/
 */
public final class MinNumberOfCoins {

    public static int findMinCoins(int[] coins, int value) throws Exception {

        // some arbitrary limit so as not to run out of memory
        if (value > 1024*1024)
            throw new Exception("Value too big.");
        
        int[] solutions = new int[value+1];
        
        solutions[0] = 0;
        
        for (int sol = 1; sol < solutions.length; sol++) {

            int minSoFar = Integer.MAX_VALUE;

            for (int i = 0; i < coins.length; i++) {
                
                // check for coins greater than what is left
                int change = value - coins[i];
                if (change < 0)
                    continue;
                
                int candidate = 1 + solutions[change];
                
                if (candidate < minSoFar)
                    minSoFar = candidate;
            }

            // determine solutions[sol]
            if (minSoFar == Integer.MAX_VALUE) 
                solutions[sol] = solutions[sol-1];
            else 
                solutions[sol] = minSoFar;
        }
        return solutions[value];
    }
    
    public static void main(String[] args) throws Exception {
        int[] coins = new int[] {9, 6, 5, 1};
        System.out.println(findMinCoins(coins, 11));
    }
}
