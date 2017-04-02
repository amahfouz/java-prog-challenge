package mahfouz.google.codejam.y2015.qual;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Solution for Infinite House of Pancakes.
 *
 * This solution does not handle all cases as it assumes that it is
 * always best to split the stack in half when doing a special minute.
 */
public final class InfiniteHouseOfPancakesWrong {

    private final int caseNum;
    private final ArrayList<Integer> diners;

    public InfiniteHouseOfPancakesWrong(int caseNum, Scanner s) {
        this.caseNum = caseNum;
        int d = s.nextInt();
        this.diners = new ArrayList<Integer>(d+1);

        // add an empty plate
        insert(0);

       // sort the array
        int p;
        for (int i=0; i<d; i++) {
            p = s.nextInt();
            insert(p);
        }

        solve();
    }

    private void solve() {

        int minutes = 0;

        // main loop
        do {
            int largestPlate = getLargestPlate();
            if (largestPlate % 2 != 0 || largestPlate <= 2) {
                // don't bother splitting odd numbers or plates of 2 cakes
                minutes++;
                eat();
            }
            else {
                int specialMinutes = attemptSpecial();
                if (specialMinutes > 0)
                    minutes += specialMinutes;
                else {
                    minutes++;
                    eat();
                }
            }

            // run until largest plate is empty
            if (getLargestPlate() == 0)
                break;

        } while (true);

        printSolution(String.valueOf(minutes));
    }

    private int attemptSpecial() {
        int special = 0;
        while (true) {
            int cutPoint = findCutPoint();

            // no useful cut
            if (cutPoint < 0)
                return special;

            // otherwise there is a saving cut
            int sizeBeforeSplit = diners.size();
            special += sizeBeforeSplit - cutPoint;

            for (int i= cutPoint; i<sizeBeforeSplit; i++) {
                int stack = diners.get(i);
                int toRemove = stack / 2;

                // remove from plate
                diners.set(i, stack - toRemove);

                // add to an empty plate
                diners.add(toRemove);
            }
            Collections.sort(diners);
        }
    }

    private int findCutPoint() {
        if (getLargestPlate() % 2 !=0)
            return -1;

        int halfTallest = getLargestPlate() / 2;
        int halfPlusOne = halfTallest + 1;

        int index = Collections.binarySearch(diners, halfPlusOne);

        if (index > 0) {
            // find the last one of them

            while (index < diners.size() - 1 && diners.get(index) == halfPlusOne)
                index++;
        }
        else {
            index = -(index + 1); // not found
        }

        int width = (diners.size() - index);

        return width >= halfTallest
            ? -1
            : index;
    }

    private void eat() {
        int size = diners.size();

        // skip the empty plate - start at 1
        for (int i = 1; i<size; i++) {
            diners.set(i, diners.get(i) - 1);
        }
    }

    private int getLargestPlate() {
        return diners.get(diners.size() - 1);
    }

    private void insert(int p) {
        int index;
        index = Collections.binarySearch(diners, p);
        if (index < 0)
            index = -(index + 1); // not found - see JDK JavaDocs

        diners.add(index, p);
    }

    private void printSolution(String solution) {
        System.out.println("Case #" + caseNum + ": " + solution);
    }

    // main

    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("/Users/amahfouz/Desktop/gcj.in"));

        int numCases = s.nextInt();

        for (int i = 0; i < numCases; i++) {
            new InfiniteHouseOfPancakesWrong(i + 1, s);
        }
    }

}
