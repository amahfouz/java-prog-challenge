/*
 * CONFIDENTIAL
 * Copyright 2013 Webalo, Inc.  All rights reserved.
 */

package mahfouz.google.codejam.y2010.qual;

import java.io.File;
import java.util.Scanner;

/**
 * Solution of "Snapper Chain" problem (Qualification 2010).
 */
public final class SnapperChain {

    public static void main(String[] args) throws Exception{
        Scanner s = new Scanner(new File("C:\\Users\\amahfouz\\Downloads\\cj.in"));

        int numCases = s.nextInt();

        for (int i = 0; i < numCases; i++) {
            solveCase(i + 1, s);
        }
    }

    private static void solveCase(int caseNum, Scanner s) {
        long n = s.nextInt();
        long k = s.nextInt();

        boolean divides = (k + 1) % (1 << n) == 0;
        String result = divides
            ? "ON"
            : "OFF";

        System.out.println("Case #" + caseNum + ": " + result);
    }
}

