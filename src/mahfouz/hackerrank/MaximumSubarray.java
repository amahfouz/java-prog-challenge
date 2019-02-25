package mahfouz.hackerrank;

/**
 * Solution for 
 * https://www.hackerrank.com/challenges/maxsubarray/problem
 */
public final class MaximumSubarray {

    public static int[] maxSubarray(int[] arr) {

        // find max if all are negative
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];
            
            if (max > 0)
                break;
        }
        if (max <= 0) 
            return new int[] { max, max };

        // some positive number was found
        
        int maxArrSeenSoFar = 0;
        int sumOfCurArray = 0;
        int sumOfAllPositive = 0;
        
        for (int i = 0; i < arr.length; i++) {
            int value = arr[i];
            sumOfCurArray += value;
            if (sumOfCurArray > maxArrSeenSoFar)
                maxArrSeenSoFar = sumOfCurArray;
            
            if (sumOfCurArray <= 0) 
                sumOfCurArray = 0; // start over
            
            if (value > 0) {
                sumOfAllPositive += value;
            }
        }
        
        return new int[] { maxArrSeenSoFar, sumOfAllPositive};
    }    
}
