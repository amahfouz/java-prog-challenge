/*
 * CONFIDENTIAL
 * Copyright 2013 Webalo, Inc.  All rights reserved.
 */

package mahfouz.algo.competition.projeuler;

import java.util.ArrayList;
import java.util.List;

/**
 * Prints all "Pandigital" numbers as specified in
 * http://projecteuler.net/problem=38
 *
 * Quick and dirty solution.
 */
public final class LargestPandigital {

    private static final int MAX = 10000;

    public static void main(String[] args) {
        int max = -1;
        int number = -1;
        for (int candidate = MAX; candidate >= 9; candidate--) {
            int concat = getPandigitalForNumIfExists(candidate);
            if (concat > max) {
                max = concat;
                number = candidate;
            }
        }
        System.out.format("Largest pandigital is = %d for %d.", max, number);
    }

    /**
     * Returns the pandigital for the specified number if
     * one exists, or -1 if none exists.
     */
    private static int getPandigitalForNumIfExists(int candidate) {
        boolean[] flags = new boolean[10];
        List products = new ArrayList();

        int product;
        for (int i = 1; i < 9; i++) {
           product = candidate * i;
           products.add(product);
           while (product > 0) {
               int digit = product % 10;

               if (digit == 0)
                   return -1; // not pandigital if contains a zero

               if (flags[digit])
                   return -1; // repeated digit

               flags[digit] = true;
               product /= 10;
           }

           if (allDigitsOccur(flags)) {
               StringBuffer concat = new StringBuffer(9);
               for (Object prod: products) {
                   concat.append(prod);
               }
               return Integer.parseInt(concat.toString());
           }
        }
        // fell through, none of the trials had all digits
        return -1;
    }

    private static boolean allDigitsOccur(boolean[] flags) {
        for (int i = 1; i < flags.length; i++) {
            if (! flags[i])
                return false;
        }
        return true;
    }
}
