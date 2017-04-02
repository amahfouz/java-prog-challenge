package mahfouz.google.codejam.y2016.qual;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Solution of Counting Sheep.
 */
public final class CountingSheep {

    private static final long TARGET = (1 << 10) - 1;

    private final int caseNum;

    private long bits = 0;

    private CountingSheep(int caseNum, Scanner s) {
        this.caseNum = caseNum;

        long n = s.nextLong();
        long init = n;

        if (n == 0) {
            printSolution("INSOMNIA");
            return;
        }

        // main loop for each case
        do {
            // init for current num
            long div = n;

            // loop for each number
            while (div != 0) {
                long mod = div % 10;
                bits |= (1 << mod);
                div /= 10;
            }
            if ((bits ^ TARGET) == 0) {
                printSolution(String.valueOf(n));
                break;
            }
            else
                n += init;

        } while (true);
    }

    private void printSolution(String solution) {
        System.out.println("Case #" + caseNum + ": " + solution);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("/Users/amahfouz/Desktop/gcj.in"));

        int numCases = s.nextInt();

        for (int i = 0; i < numCases; i++) {
            new CountingSheep(i + 1, s);
        }
    }
}
