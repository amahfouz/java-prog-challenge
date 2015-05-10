/*
 * CONFIDENTIAL
 * Copyright 2013 Webalo, Inc.  All rights reserved.
 */

package mahfouz.google.codejam.y2012.round1c;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Solution of "Box Factory" (Round 1C 2012)
 */
public final class BoxFactory {

    private final List<Batch> toys = new ArrayList<Batch>();
    private final List<Batch> boxs = new ArrayList<Batch>();

    private static final String IN_FILE_NAME
        = "C:\\Users\\amahfouz\\Downloads\\cj.in";
    private static final String OUT_FILE_NAME
        = "C:\\Users\\amahfouz\\Desktop\\solution.txt";

    public static void main(String[] args) throws Exception{
        Scanner s = new Scanner(new File(IN_FILE_NAME));
        PrintStream out = new PrintStream(new File(OUT_FILE_NAME));

        int numCases = s.nextInt();

        for (int i = 0; i < numCases; i++) {
            new BoxFactory(i + 1, s, out);
        }
    }

    private BoxFactory(int caseNum, Scanner s, PrintStream out) {

        int numToys = s.nextInt();
        int numBoxes = s.nextInt();

        long qty;
        int type;
        for (int i = 0; i < numToys; i++) {
            qty = s.nextLong();
            type =  s.nextInt();
            toys.add(new Batch(type, qty));
        }

        for (int i = 0; i < numBoxes; i++) {
            qty = s.nextLong();
            type =  s.nextInt();
            boxs.add(new Batch(type, qty));
        }

        solveCase(caseNum, out);
    }

    private void solveCase(int caseNum, PrintStream out) {
        long best = solveSubset(0, 0);
        outputCase(out, caseNum, String.valueOf(best));
    }

    public long solveSubset(int toyIndex, int boxIndex) {

        if (toyIndex == toys.size() || boxIndex == boxs.size())
            return 0;

        Batch toy = toys.get(toyIndex);
        Batch box = boxs.get(boxIndex);

        if (toy.type == box.type) {
            final long dropResult;

            if (toy.qty > box.qty) {
                long origToys = toy.setQty(toy.qty - box.qty);
                dropResult = box.qty + solveSubset(toyIndex, boxIndex + 1);
                toy.setQty(origToys);
            }
            else if (toy.qty < box.qty) {
                long origBox = box.setQty(box.qty - toy.qty);
                dropResult = toy.qty + solveSubset(toyIndex + 1, boxIndex);
                box.setQty(origBox);
            }
            else
                dropResult = toy.qty + solveSubset(toyIndex + 1, boxIndex + 1);

            return dropResult;
        }
        else {
            long dropBox = solveSubset(toyIndex, boxIndex + 1);
            long dropToy = solveSubset(toyIndex + 1, boxIndex);

            return Math.max(dropBox, dropToy);
        }
    }

    private static void outputCase(PrintStream out, int caseNum, String output) {
        out.println("Case #" + caseNum + ": " + output);
    }

    //
    // nested
    //

    private static final class Batch {

        private final int type;
        private long qty;

        public Batch(int type, long qty) {
            this.type = type;
            this.qty = qty;
        }

        public long setQty(long newQty) {
            long qtyBeforeSet = qty;
            this.qty = newQty;
            return qtyBeforeSet;
        }

        public String toString() {
            return "(" + qty +", " + type + ")";
        }
    }
}
