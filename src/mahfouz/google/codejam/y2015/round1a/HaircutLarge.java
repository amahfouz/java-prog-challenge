package mahfouz.google.codejam.y2015.round1a;

import java.util.Scanner;

public final class HaircutLarge {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int numCases = s.nextInt();
        s.nextLine();

        for (int i = 0; i < numCases; i++) {
            solveCase(i+1, s);
        }
    }

    private static void solveCase(int caseNum, Scanner s) {
        int b = s.nextInt();
        long n = s.nextLong();

        long[] barbers = new long[b];

        for (int i = 0; i < barbers.length; i++)
            barbers[i] = s.nextInt();

        long low = -1;
        long high = n * 10000;
        long mid;
        do {
            mid = (high + low) / 2;
            if (countServed(mid, barbers) < n)
                low = mid;
            else
                high = mid;
        } while (low + 1 < high);

        long time = high;
        long remaining = n - countServed(time, barbers);

        int target = 0;
        for (int i = 0; i < barbers.length; i++) {
            if (time % barbers[i] == 0) {
                remaining--;
                if (remaining == 0) {
                    target = i;
                    break;
                }
            }
        }

        System.out.println("Case #" + caseNum + ": " + (target + 1));
    }

    private static long countServed(long time, long[] barbers) {
        if (time < 0)
            return 0;

        long served = 0;
        for (int i = 0; i < barbers.length; i++) {
            served += (time / barbers[i]) + 1;
        }
        return served;
    }
}
