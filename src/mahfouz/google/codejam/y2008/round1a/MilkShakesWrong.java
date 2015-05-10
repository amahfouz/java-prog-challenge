/*
 * CONFIDENTIAL
 * Copyright 2013 Webalo, Inc.  All rights reserved.
 */

package mahfouz.google.codejam.y2008.round1a;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Solution for "Milkshakes" problem (Round 1A 2008)
 */
public final class MilkShakesWrong {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int numTestCases = scanner.nextInt();

        for (int i = 0; i < numTestCases; i++)
            handleTestCase(scanner, i+1);
    }

    private static void handleTestCase(Scanner scanner, int caseNum) {
        int numShakes = scanner.nextInt();

        ShakeRecord[] shakes = new ShakeRecord[numShakes];
        for (int i = 0; i < shakes.length; i++)
            shakes[i] = new ShakeRecord(i);

        int numCustomers = scanner.nextInt();
        Set<Customer> customers = new HashSet<Customer>();
        for (int i = 0; i < numCustomers; i++) {
            int numLikes = scanner.nextInt();
            List<Integer> unmaltedLikes = new ArrayList<Integer>();
            int maltedIdIfAny = -1;

            for (int j = 0; j < numLikes; j++) {
                int shakeIdPlusOne = scanner.nextInt();
                int shakeId = shakeIdPlusOne - 1;

                boolean isMalted = (scanner.nextInt() == 1);

                if (isMalted)
                    maltedIdIfAny = shakeId;
                else {
                    unmaltedLikes.add(shakeId);
                    shakes[shakeId].addLike();
                }
            }
            Collections.sort(unmaltedLikes);

            customers.add(new Customer(unmaltedLikes, maltedIdIfAny));
        }

        solveCase(caseNum, shakes, customers);
    }

    private static void solveCase(int caseNum,
                                  ShakeRecord[] shakes,
                                  Set<Customer> unsatisfiedCust) {

        Arrays.sort(shakes);

        byte[] chosenShakes = new byte[shakes.length];

        // iterate over shakes

        int curShakeIndex = 0;
        while (true) {
            ShakeRecord record = shakes[curShakeIndex];
            int shakeId = record.getId();

            // if any customer's only preference is malted then chose it
            Set<Customer> thoseWhoseOnlePrefIsMaltedShakeFor
                = getAndRemoveCustsWhoseOnlePrefIsMaltedShakeFor
                    (unsatisfiedCust, shakeId);

            if (! thoseWhoseOnlePrefIsMaltedShakeFor.isEmpty())
                chosenShakes[shakeId] = 1;
            else {
                Iterator<Customer> iter = unsatisfiedCust.iterator();
                while (iter.hasNext()) {
                    Customer cust = iter.next();
                    if (cust.likesUnmalted(shakeId))
                        iter.remove();
                }
            }

            for (Customer c : unsatisfiedCust)
                c.removeShake(shakeId);

            curShakeIndex++;

            if (curShakeIndex == shakes.length)
                break;

            if (unsatisfiedCust.isEmpty())
                break;
        }

        StringBuffer output = new StringBuffer();

        output.append("Case #" + caseNum + ": ");

        if (! unsatisfiedCust.isEmpty()) {
            output.append("IMPOSSIBLE");
        }
        else {
            for (int i = 0; i < chosenShakes.length; i++) {
                output.append(chosenShakes[i]);
                if (i < chosenShakes.length - 1)
                    output.append(' ');
            }
        }

        System.out.println(output.toString());
    }

    private static Set<Customer> getAndRemoveCustsWhoseOnlePrefIsMaltedShakeFor
        (Set<Customer> unsatisfiedCust, int shakeId) {

        Set<Customer> result = new HashSet<Customer>();
        Iterator<Customer> iter = unsatisfiedCust.iterator();
        while (iter.hasNext()) {
            Customer customer = iter.next();
            if (customer.likesOnlyMalted(shakeId)) {
                result.add(customer);
                iter.remove();
            }
        }
        return result;
    }

    //
    // nested
    //

    private static final class ShakeRecord implements Comparable {

        private final int shakeId;

        private int numCustWhoLikeNonmalted;

        public ShakeRecord(int shakeId) {
            this.shakeId = shakeId;
        }

        public int getId() {
            return shakeId;
        }

        public void addLike() {
            numCustWhoLikeNonmalted++;
        }

        public int compareTo(Object obj) {
            ShakeRecord other = (ShakeRecord)obj;
            return numCustWhoLikeNonmalted - other.numCustWhoLikeNonmalted;
        }
    }

    private static final class Customer {

        private final List<Integer> shakeIds;
        private int maltedShakeId;

        public Customer(List<Integer> shakeIds, int maltedShakeId) {
            if (shakeIds == null)
                throw new IllegalArgumentException();

            this.shakeIds = shakeIds;
            this.maltedShakeId = maltedShakeId;
        }

        public boolean likesUnmalted(int shakeId) {
            return Collections.binarySearch(shakeIds, shakeId) >= 0;
        }

        public boolean likesOnlyMalted(int shakeId) {
            return shakeIds.isEmpty() && (maltedShakeId == shakeId);
        }

        public void removeShake(int shakeId) {
            if (maltedShakeId == shakeId)
                this.maltedShakeId = -1;
            else {
                int index = Collections.binarySearch(shakeIds, shakeId);
                if (index >= 0)
                    shakeIds.remove(index);
            }
        }
    }
}
