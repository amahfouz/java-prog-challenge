package mahfouz.hackerrank;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Solution for 
 * https://www.hackerrank.com/challenges/angry-children-2/problem
 */
public final class AngryChildren2 {

    /**
     * Assumption k>=2
     */
    public static long angryChildren(int k, int[] packets) {

        int numPacks = packets.length;

        Arrays.sort(packets);
        
        int[] diffs = new int[packets.length];
        // turn the array to an array of diffs between packets
        for (int i = packets.length - 1; i > 0 ; i--) {
            diffs[i] = Math.abs(packets[i] - packets[i-1]); 
        }
        diffs[0] = 0;
        
        if (k == 2) 
            return Arrays.stream(diffs).min().getAsInt();
        
        
        // the ith element in this array is the unfairness sum
        // from the beginning of the packets array to i (0-based)
        long[] cumulativeUnfairness = new long[numPacks];
        
        // first entry ignored 1 kid is a trivial problem
        cumulativeUnfairness[0] = 0;
        
        long runningSum = 0; 
        for (int i = 1; i < cumulativeUnfairness.length; i++) {
            runningSum += i * diffs[i];
            cumulativeUnfairness[i] 
                = cumulativeUnfairness[i - 1] + runningSum;
        }
        checkForNegative("cumu", cumulativeUnfairness);
        // construct a Fenwick tree to compute the prefix sums 
        
        long[] fenwick = new long[numPacks + 1];
        for (int i = 1; i < fenwick.length; i++) {
            
            int index = i;
            // add each packet to the tree
            while (index <= numPacks) {
                fenwick[index] += packets[i - 1];
                index += (index & -index);
            }
        }
        checkForNegative("fenwick", fenwick);
        
        // pre-compute all prefix sums (now 0-based)
        long [] prefixSums = new long[numPacks];
        for (int i = 0; i < prefixSums.length; i++) {
            prefixSums[i] = 0;
            for(int index = i+1; index > 0; index -= index&-index)
                prefixSums[i] += fenwick[index];
        }
        checkForNegative("prefixsums", prefixSums);
        
        // now that we have computed prefix sums we compute
        // sums of all contiguous subsequences of k-1 length
        // element i contains sum from i to i + k - 2
        long[] kStretchSums = new long[numPacks - k + 1];
        kStretchSums[0] = prefixSums[k-2];
        for (int i = 1; i < kStretchSums.length; i++) {
            kStretchSums[i] 
                = prefixSums[i+k-2] - prefixSums[i-1];
        }
        checkForNegative("k-stretch", kStretchSums);
        
        // element i contains unfairness if packets from
        // i to i + k - 1 are included
        BigInteger[] kApartUnfairnesss = new BigInteger[numPacks - k + 1];
        kApartUnfairnesss[0] 
            = BigInteger.valueOf(cumulativeUnfairness[k - 1]);
        for (int i = 0; i < kApartUnfairnesss.length - 1; i++) {
            kApartUnfairnesss[i+1] 
                = kApartUnfairnesss[i]
                .subtract(BigInteger.valueOf(2 * kStretchSums[i+1]))
                .add(BigInteger.valueOf(packets[i+k] + packets[i])
                     .multiply(BigInteger.valueOf(k-1)));
        }
        //checkForNegative("k-apart", kApartUnfairnesss);
        
        // now find the min in the k-apart unfairness
        
        long minUnfairness = Long.MAX_VALUE;
        for (int i = 0; i < kApartUnfairnesss.length; i++) {
            if (kApartUnfairnesss[i].longValue() < minUnfairness)
                minUnfairness = kApartUnfairnesss[i].longValue();
        }
        return minUnfairness;
    }
    
    public static void checkForNegative(String label, long[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 0) {
                System.out.println(label + " " + i);
                return;
            }
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException {
//        int[] packets = new int[] {10, 100, 300, 200, 1000, 20, 30};
//        System.out.println(angryChildren(3, packets));
        
        int[] packets = {4504,1520,5857,4094,4157,3902,822,6643,2422,7288,8245,9948,2822,1784,7802,3142,9739,5629,5413,7232};
        System.out.println(angryChildren(5, packets));

//      int[] packets = new int[] {1, 17, 23, 24};
//      System.out.println(angryChildren(2, packets));

//        Scanner scanner 
//            = new Scanner(new FileInputStream(new File("/Users/amahfouz/temp/input07.txt")));
//        int n = scanner.nextInt();
//        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
//
//        int k = scanner.nextInt();
//        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
//
//        int[] packets = new int[n];
//
//        for (int i = 0; i < n; i++) {
//            int packetsItem = scanner.nextInt();
//            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
//            packets[i] = packetsItem;
//        }
//
//        long result = angryChildren(k, packets);
//
//        System.out.println(result);
    }
}
