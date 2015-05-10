/*
 * CONFIDENTIAL
 * Copyright 2014 Webalo, Inc.  All rights reserved.
 */

package mahfouz.google.codejam.y2011.round1c;

import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * Solution for "Space Emergency" (Round 1C 2011)
 */
public final class SpaceEmergency {

    // distance and speed are multiplied by two to avoid using double

    private final int numBoostes;
    private final long buildTime;
    private final Long[] segments;

    public static void main(String[] args) throws Exception{
        Scanner s = new Scanner
            (new File("C:\\Users\\amahfouz\\Downloads\\cj.in"));
        PrintStream out = new PrintStream
            (new File("C:\\Users\\amahfouz\\Desktop\\solution.txt"));

        int numCases = s.nextInt();

        for (int i = 0; i < numCases; i++) {
            new SpaceEmergency(s).solveCase(i + 1, out);
            System.out.println("Finished " + (i + 1));
        }

//        s.close();
//        out.close();
    }

    private SpaceEmergency(Scanner s) {
        this.numBoostes = s.nextInt();
        this.buildTime = s.nextLong();

        int numSegments = s.nextInt();

        this.segments = new Long[numSegments];

        int numDistances = s.nextInt();
        long[] distances = new long[numDistances];
        for (int d = 0; d < distances.length; d++)
            distances[d] = s.nextLong() * 2;

        for (int i = 0; i < segments.length; i++)
            segments[i] = distances[i % distances.length];
    }

    private void solveCase(int caseNum, PrintStream out) {
        // regular speed is 1 (after having multiplied by two)
        long distanceBeforeBuild = buildTime;
        int potentialBuildSite = 0;
        long timeSoFar = 0;

        while (true) {
            if (distanceBeforeBuild <= 0
                || potentialBuildSite >= segments.length)
                break;


            if (distanceBeforeBuild >= segments[potentialBuildSite]) {
                timeSoFar += segments[potentialBuildSite];
                distanceBeforeBuild -= segments[potentialBuildSite];

                // already traveled - zeroize
                segments[potentialBuildSite] = 0L;

                potentialBuildSite++;
            }
            else {
                // travel part of segment at regular speed
                timeSoFar += distanceBeforeBuild;
                // adjust remaining part of segment
                segments[potentialBuildSite] -= distanceBeforeBuild;

                distanceBeforeBuild = 0;
            }
        }

        // choose the longest L segments for building boosters

        Arrays.sort(segments, Collections.reverseOrder());

        for (int i = 0; i < segments.length; i++) {
            timeSoFar += (i < numBoostes)
                ? segments[i] / 2
                : segments[i];
        }

        // now reverse the adjustment and divide result by 2
        outputCase(out, caseNum, String.valueOf(timeSoFar));
    }

    private static void outputCase(PrintStream out,
                                   int caseNum,
                                   String output) {
        out.println("Case #" + caseNum + ": " + output);
    }
}
