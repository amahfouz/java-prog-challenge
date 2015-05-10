/*
 * CONFIDENTIAL
 * Copyright 2013 Webalo, Inc.  All rights reserved.
 */

package mahfouz.google.codejam.y2012.round1b;

import java.io.File;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Solution for "Safety in Numbers (Round 1A 2012)
 */
public final class SafetyInNumbers {

    public static void main(String[] args) throws Exception{
        Scanner s = new Scanner(new File("C:\\Users\\amahfouz\\Downloads\\cj.in"));
        PrintStream out = new PrintStream(new File("C:\\Users\\amahfouz\\Desktop\\solution.txt"));

        int numCases = s.nextInt();

        for (int i = 0; i < numCases; i++) {
            solveCase(i + 1, s, out);
        }
    }

    private static void solveCase(int caseNum, Scanner in, PrintStream out) {
        int count = in.nextInt();
        double[] judged = new double[count];
        double sum = 0;

        for (int i = 0; i < count; i++) {
            judged[i] = in.nextDouble();
            sum += judged[i];
        }
        double average = sum / count;
        double equi = average + (sum / count);

        outputCase(out, caseNum, "");

        DecimalFormat format = new DecimalFormat("#.######");
        format.setMinimumFractionDigits(5);

        double percent;
        for (int i = 0; i < judged.length; i++) {
            percent = (equi - judged[i]) / sum;
            out.print(format.format(percent * 100) + " ");
        }
        out.println();
    }

    private static void outputCase(PrintStream out, int caseNum, String output) {
        out.print("Case #" + caseNum + ": " + output);
    }
}
