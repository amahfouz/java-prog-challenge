/*
 * CONFIDENTIAL
 * Copyright 2013 Webalo, Inc.  All rights reserved.
 */

package mahfouz.google.codejam.y2013.qual;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Solution for "Treasure" problem (Qualification 2013)
 */
public final class Treasure {

    private static final int MAX_NUM_KEYS = 400;

    private final List<Integer>[] boxContents;
    private final int[] keyForBox;
    private final boolean[] boxIsOpen;
    private final int[] keysInHand;
    private final List<Integer> solution = new ArrayList();
    private final Set unsolvable = new HashSet();
    private final State mutableState = new State();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();

        System.out.println("Num = " + numCases);

        for (int i = 0; i < numCases; i++) {
            System.out.println("Starting " + (i + 1));
            new Treasure(i + 1, scanner);
            System.out.println("Completed " + (i + 1));
        }
    }

    private Treasure (int caseNum, Scanner scanner) {
        int numKeysInHand = scanner.nextInt();
        int numBoxesToOpen = scanner.nextInt();

        this.boxIsOpen = new boolean[numBoxesToOpen];
        this.keyForBox = new int[numBoxesToOpen];
        this.boxContents = new List[numBoxesToOpen];
        this.keysInHand = new int[MAX_NUM_KEYS];

        for (int i = 0; i < keysInHand.length; i++)
            keysInHand[i] = 0;

        // transform all keys from 1-based to 0-based

        for (int i = 0; i < numKeysInHand; i++) {
            int key = scanner.nextInt() - 1;
            keysInHand[key]++;
        }

        for (int i = 0; i < numBoxesToOpen; i++) {
            keyForBox[i] = scanner.nextInt() - 1;
            boxIsOpen[i] = false;
            int numKeysInBox = scanner.nextInt();
            boxContents[i] = new ArrayList<Integer>(numKeysInBox);

            for (int j = 0; j < numKeysInBox; j++)
                boxContents[i].add(scanner.nextInt() - 1);
        }

        boolean succeeded = false;
        for (int i = 0; i < numBoxesToOpen; i++) {
            if (isCurStateUnsolvable())
                continue;

            succeeded = tryOpenBox(i);
            if (succeeded)
                break;
        }

        StringBuffer output = new StringBuffer();
        output.append("Case #").append(caseNum).append(": ");
        if (! succeeded)
            output.append("IMPOSSIBLE");
        else
            for (int i = numBoxesToOpen - 1; i >= 0; i--) {
                output.append(' ').append(solution.get(i) + 1);
        }
        System.out.println(output.toString());
    }

    private boolean tryOpenBox(int curBoxIndex) {
        int keyForCurBox = keyForBox[curBoxIndex];
        if (keysInHand[keyForCurBox] == 0)
            return false;

        // box can be opened, open it and grab enclosed keys

        flipBox(curBoxIndex, true);

        // try each box

        int nextBoxIndex = 0;
        boolean atLeastOneBoxLeftClosed = false;
        while (true) {
            while (nextBoxIndex < boxIsOpen.length && boxIsOpen[nextBoxIndex])
                nextBoxIndex++;

            if (nextBoxIndex == boxIsOpen.length) {
                if (atLeastOneBoxLeftClosed) {
                    flipBox(curBoxIndex, false);
                    return false;
                }

                solution.add(curBoxIndex);
                return true;
            }

            // open the box

            if (! isCurStateUnsolvable()) {
                if (tryOpenBox(nextBoxIndex)) {
                    solution.add(curBoxIndex);
                    return true;
                }
            }

            atLeastOneBoxLeftClosed = true;
            nextBoxIndex++;
        }
    }

    private void flipBox(int boxIndex, boolean open) {
        int keyForCurBox = keyForBox[boxIndex];
        List<Integer> keysInBox = boxContents[boxIndex];
        if (open) {
            keysInHand[keyForCurBox]--;
            for (Integer key : keysInBox)
                keysInHand[key]++;
        }
        else {
            unsolvable.add(new State(keysInHand, boxIsOpen));
            keysInHand[keyForCurBox]++;
            for (Integer key : keysInBox)
                keysInHand[key]--;
        }
        boxIsOpen[boxIndex] = open;
    }

    private boolean isCurStateUnsolvable() {
        mutableState.set(keysInHand, boxIsOpen);
        return unsolvable.contains(mutableState);
    }

    private final static class State {

        private int[] keysInHand;
        private boolean[] isBoxOpen;

        public State() { }

        public State(int[] keysInHand, boolean[] isBoxOpen) {
            this.keysInHand = keysInHand.clone();
            this.isBoxOpen = isBoxOpen.clone();
        }

        public void set(int[] keysInHand, boolean[] isBoxOpen) {
            this.keysInHand = keysInHand;
            this.isBoxOpen = isBoxOpen;
        }

        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + Arrays.hashCode(this.isBoxOpen);
            result = prime * result + Arrays.hashCode(this.keysInHand);
            return result;
        }

        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            State other = (State)obj;
            if (! Arrays.equals(this.isBoxOpen, other.isBoxOpen))
                return false;
            if (! Arrays.equals(this.keysInHand, other.keysInHand))
                return false;
            return true;
        }
    }
}
