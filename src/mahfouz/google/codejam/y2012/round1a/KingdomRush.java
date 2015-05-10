/*
 * CONFIDENTIAL
 * Copyright 2013 Webalo, Inc.  All rights reserved.
 */

package mahfouz.google.codejam.y2012.round1a;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Solution to Kingdom Rush (Round 1A 2012)
 */
public final class KingdomRush {

    private static final boolean DEBUG = false;

    private final ArrayList<Level> levels = new ArrayList();

    private void solveCase(int caseNum, Scanner scanner) {

        int numLevels = scanner.nextInt();
        for (int i = 0; i < numLevels; i++)
            levels.add(new Level(scanner.nextInt(), scanner.nextInt()));

        int numTimes = 0;
        int curStars = 0;
        while (true) {
            int numStarsGained
                = pickLevelWhose2StarRequiresLeastStars(curStars);
            if (numStarsGained == 0)
                // pick level whose 1-star requires least stars
                numStarsGained
                    = pick1StarForLevelWithMax2StarRequirement(curStars);

            if (numStarsGained > 0) {
                numTimes++;
                curStars += numStarsGained;

                if (DEBUG)
                    System.out.println("Stars in hand = " + curStars);

                if (levels.isEmpty())
                    break;
            }
            else {
                numTimes = -1;
                break;
            }
        }

        System.out.println("Case #" + caseNum + ": "
                         + ((numTimes < 0)
                             ? "Too Bad"
                             : numTimes));
    }

    private int pickLevelWhose2StarRequiresLeastStars(int starsInHand) {
        int minStars = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < levels.size(); i++) {
            Level level = levels.get(i);
            int required = level.numStarsRequiredForTwoStar;
            if (required <= starsInHand && required < minStars) {
                minStars = required;
                minIndex = i;
            }
        }
        if (minIndex < 0)
            return 0;

        Level level = levels.remove(minIndex);

        if (level.state == Level.State.PASSED)
            throw new IllegalStateException();

        if (DEBUG)
            System.out.println("Pass 2 for ("
                                + level.numStarsRequiredForOneStar + " , "
                                + level.numStarsRequiredForTwoStar + ")");

        return (level.state.equals(Level.State.PASSED_1))
            ? 1
            : 2;
    }

    private int pick1StarForLevelWithMax2StarRequirement(int starsInHand) {
        int maxStars = -1;
        int targetIndex = -1;
        for (int i = 0; i < levels.size(); i++) {
            Level level = levels.get(i);
            if (level.state != Level.State.NOT_ATTEMPTED)
                continue;

            int required = level.numStarsRequiredForOneStar;
            if (required > starsInHand)
                continue;

            int future = level.numStarsRequiredForTwoStar;
            if (future > maxStars) {
                maxStars = future;
                targetIndex = i;
            }
        }
        if (targetIndex < 0)
            return 0;

        Level level = levels.get(targetIndex);

        level.state = Level.State.PASSED_1;

        if (DEBUG)
            System.out.println("Pass 1 for ("
                                + level.numStarsRequiredForOneStar + " , "
                                + level.numStarsRequiredForTwoStar + ")");

        return 1;
    }

    private static final class Level {

        enum State { NOT_ATTEMPTED, PASSED_1, PASSED };

        private final int numStarsRequiredForOneStar;
        private final int numStarsRequiredForTwoStar;

        private State state = State.NOT_ATTEMPTED;

        public Level(int numStarsRequiredForOneStar,
                     int numStarsRequiredForTwoStar) {
            this.numStarsRequiredForOneStar = numStarsRequiredForOneStar;
            this.numStarsRequiredForTwoStar = numStarsRequiredForTwoStar;
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(new File("C:\\Users\\amahfouz\\Downloads\\cj.in"));

        int numCases = s.nextInt();

        for (int i = 0; i < numCases; i++) {
            new KingdomRush().solveCase(i + 1, s);
        }
    }
}
