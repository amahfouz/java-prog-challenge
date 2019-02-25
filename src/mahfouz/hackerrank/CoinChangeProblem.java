package mahfouz.hackerrank;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Solution for
 * https://www.hackerrank.com/challenges/coin-change/problem
 */
public final class CoinChangeProblem {

    private static final int MAX_CHANGE = 250;
    
    public static int solve(int change, int[] coins){
        int[] sol = new int[MAX_CHANGE + 1]; // 0 not used
        Arrays.sort(coins);
        

        sol[0] = 1;
        // sum sub solutions
        
        // the two loops have to be in this order so that 
        // we consider each coin separately. This way we
        // avoid duplicate solutions such as 1+2 and 2+1
        for (int coin = 0; coin < coins.length; coin++) {
            for (int i = 1; i <= change; i++) {
            
                if (i - coins[coin] < 0)
                    continue;
                
                sol[i] += sol[i - coins[coin]];
            }
        }
        return sol[(int)change];
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[] c = new int[m];

        String[] cItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            int cItem = Integer.parseInt(cItems[i]);
            c[i] = cItem;
        }
        System.out.println(solve(n, c));
        
        scanner.close();
    }
}
