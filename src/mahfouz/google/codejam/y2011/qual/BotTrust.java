/*
 * CONFIDENTIAL
 * Copyright 2013 Webalo, Inc.  All rights reserved.
 */

package mahfouz.google.codejam.y2011.qual;

import java.io.File;
import java.util.Scanner;

/**
 * Solution of "Bot Trust" problem (Qualification 2011).
 */
public final class BotTrust {

    private final ButtonPress[] sequence;
    private final int caseNum;
    private final Bot orange = new Bot(Color.O);
    private final Bot blue = new Bot(Color.B);

    private int nextButPressIndex = 0;
    private int numSteps = 0;

    private BotTrust(int caseNum, Scanner s) {
        this.caseNum = caseNum;
        this.sequence = new ButtonPress[s.nextInt()];
        for (int i = 0; i < sequence.length; i++)
            sequence[i] = new ButtonPress
                (Color.valueOf(s.next()),
                 s.nextInt());

        solveCase();
    }

    //
    // methods
    //

    private void solveCase() {
        while (nextButPressIndex < sequence.length)
            performIteration();

        System.out.println("Case #" + caseNum + ": " + numSteps);
    }

    private void performIteration() {
        numSteps++;

        boolean bPress = blue.performMove(sequence, nextButPressIndex);
        boolean oPress = orange.performMove(sequence, nextButPressIndex);

        if (oPress && bPress)
            throw new IllegalArgumentException();

        if (oPress || bPress)
            nextButPressIndex++;
    }

    //
    // types
    //

    private enum Color { B, O };

    private final class ButtonPress {

        private final Color color;
        private final int pos;

        public ButtonPress(Color color, int pos) {
            this.color = color;
            this.pos = pos;
        }
    }

    private final class Bot {

        private final Color color;

        private int targetIndexForMe = -1;
        private int curPos = 1;

        private Bot(Color color) {
            this.color = color;
        }

        public boolean performMove(ButtonPress[] seq, int nextPressIndex) {

            // init target first time
            if (targetIndexForMe < 0)
                findNextTarget(seq);

            // check if there is anything to do
            if (targetIndexForMe >= seq.length)
                return false; // no more presses for me

            ButtonPress nextPress = seq[nextPressIndex];
            if (nextPress.color == this.color && curPos == nextPress.pos) {
                // press button and find next
                findNextTarget(seq);
                return true;
            }

            // button not pressed
            ButtonPress myNextTarget = seq[targetIndexForMe];

            if (curPos > myNextTarget.pos)
                curPos--;
            else if (curPos < myNextTarget.pos)
                curPos++;
            else {
                // wait at same position
            }

            return false;
        }

        private void findNextTarget(ButtonPress[] seq) {
            if (targetIndexForMe >= seq.length)
                return;

            for (int i = targetIndexForMe + 1; i < seq.length; i++) {
                if (seq[i].color == this.color) {
                    targetIndexForMe = i;
                    return;
                }
            }

            // no next target found
            targetIndexForMe = seq.length; // i am done
        }
    }

    public static void main(String[] args) throws Exception{
        Scanner s = new Scanner(new File("C:\\Users\\amahfouz\\Downloads\\cj.in"));

        int numCases = s.nextInt();

        for (int i = 0; i < numCases; i++) {
            new BotTrust(i + 1, s);
        }
    }
}
