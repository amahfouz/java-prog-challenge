/*
 * CONFIDENTIAL
 * Copyright 2013 Webalo, Inc.  All rights reserved.
 */

package mahfouz.google.codejam.y2008.round1a;

import java.util.BitSet;
import java.util.Scanner;

/**
 * Solution for "Milkshakes" problem (Round 1A 2008)
 */
public final class MilkShakes {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int numTestCases = scanner.nextInt();

        for (int i = 0; i < numTestCases; i++)
            handleTestCase(scanner, i+1);
    }

    private static void handleTestCase(Scanner scanner, int caseNum) {
        int numShakes = scanner.nextInt();

        int numCustomers = scanner.nextInt();
        Customer[] customers = new Customer[numCustomers];

        for (int i = 0; i < numCustomers; i++) {
            int numLikes = scanner.nextInt();
            BitSet plainLikes = new BitSet(numShakes);
            int maltedIdIfAny = -1;

            for (int j = 0; j < numLikes; j++) {
                int shakeIdPlusOne = scanner.nextInt();
                int shakeId = shakeIdPlusOne - 1;

                boolean isMalted = (scanner.nextInt() == 1);

                if (isMalted)
                    maltedIdIfAny = shakeId;
                else
                    plainLikes.set(shakeId);
            }
            customers[i] = new Customer(plainLikes, maltedIdIfAny);
        }

        solveCase(caseNum, numShakes, customers);
    }

    private static void solveCase
        (int caseNum, int numShakes, Customer[] customers) {

        BitSet plain = new BitSet(numShakes);

        // start with all malted

        plain.set(0, numShakes, true);

        // iterate over shakes

        boolean isImpossible = false;
        boolean needToCheck;
        do  {
            needToCheck = false;

            for (Customer customer : customers) {
                if (! customer.isSatisfied(plain)) {
                    int maltedId = customer.getMaltedIdIfAny();
                    if (maltedId < 0) {
                        isImpossible = true;
                        break;
                    }

                    // has malted option

                    plain.clear(maltedId);

                    // made a change, check again

                    needToCheck = true;
                }
            }
        } while (needToCheck);

        StringBuffer output = new StringBuffer();

        output.append("Case #" + caseNum + ": ");

        if (isImpossible) {
            output.append("IMPOSSIBLE");
        }
        else {
            for (int i = 0; i < numShakes; i++) {
                output.append(plain.get(i) ? 0 : 1);
                if (i < numShakes - 1)
                    output.append(' ');
            }
        }

        System.out.println(output.toString());
    }

    //
    // nested
    //

    private static final class Customer {

        private final BitSet shakeIds;
        private final int maltedShakeId;

        public Customer(BitSet shakeIds, int maltedShakeId) {
            if (shakeIds == null)
                throw new IllegalArgumentException();

            this.shakeIds = shakeIds;
            this.maltedShakeId = maltedShakeId;
        }

        public boolean isSatisfied(BitSet plain) {
            return shakeIds.intersects(plain)
                || (maltedShakeId >= 0 && ! plain.get(maltedShakeId));
        }

        public int getMaltedIdIfAny() {
            return maltedShakeId;
        }
    }
}
