/*
 * CONFIDENTIAL
 * Copyright 2013 Webalo, Inc.  All rights reserved.
 */

package mahfouz.google.codejam.y2013.round1a;

import java.util.Scanner;

/**
 * Solution of "Manage Your Energy" problem (Round 1A 2013)
 */
public final class ManageYourEnergy {

    private final int[] energyAvailable;
    private final int[] activityGain;
    private final int e;
    private final int r;

    private ManageYourEnergy(Scanner scanner, int caseNum) {
        this.e = scanner.nextInt();
        this.r = scanner.nextInt();
        int numActivities = scanner.nextInt();

        this.activityGain = new int[numActivities];
        this.energyAvailable = new int[numActivities];
        energyAvailable[0] = e;

        for (int i = 1; i < numActivities; i++)
            energyAvailable[i] = -1;

        for (int i = 0; i < numActivities; i++)
            activityGain[i] = scanner.nextInt();

        assignLargest(0, numActivities - 1);

        // compute total gain

        long totalGain = 0;
        for (int i = 0; i < activityGain.length - 1; i++) {

            // energyAvailable[i + 1] = energyAvailable[i] - consumed + r
            int consumed = energyAvailable[i] + r - energyAvailable[i + 1];
            totalGain += activityGain[i] * consumed;
        }

        // consume all is left for last activity
        totalGain += energyAvailable[numActivities - 1]
                   * activityGain[numActivities - 1];

        System.err.println("Case #" + caseNum + ": " + totalGain);
    }

    private void assignLargest(int begin, int end) {
        int max = -1;
        int largestIndex = -1;

        for (int i = begin; i <= end; i++) {
            if (activityGain[i] > max) {
                max = activityGain[i];
                largestIndex = i;
            }
        }

        int maxAssignable = Math.min
            (energyAvailable[begin] + (largestIndex - begin) * r, e);

        energyAvailable[largestIndex] = maxAssignable;

        // consume all energy available on activity with max gain
//        if (largestIndex < energyAvailable.length - 1
//            && energyAvailable[largestIndex + 1] == -1)
//            energyAvailable[largestIndex + 1] = r;

        if (largestIndex > begin + 1)
            assignLargest(begin, largestIndex - 1);

        if (largestIndex < end - 1)
            assignLargest(largestIndex + 1, end);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();

        for (int i = 0; i < numCases; i++) {
            new ManageYourEnergy(scanner, i + 1 );
        }
    }
}
