package mahfouz.google.codejam.y2015.round1a;

import java.util.PriorityQueue;
import java.util.Scanner;

public final class HaircutSmall {

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
        int n = s.nextInt();

        long[] barbers = new long[b];

        for (int i = 0; i < barbers.length; i++) {
            barbers[i] = s.nextInt();
        }

        long lcm = barbers[0];
        for (int i = 1; i < barbers.length; i++) {
            lcm = lcm(lcm, barbers[i]);
        }

        int episode = 0;
        for (int i = 0; i < barbers.length; i++) {
            episode += (lcm / barbers[i]);
        }

        n = n % episode;

        PriorityQueue<Slot> queue = new PriorityQueue<Slot>();
        // all barbers are initially available
        for (int i = 0; i < b; i++)
            queue.add(new Slot(0, i));

        int target;
        if (n == 0) {
            long min = Long.MAX_VALUE;
            target = barbers.length;

            for (int i = barbers.length - 1; i >= 0; i--) {
                if (barbers[i] < min) {
                    min = barbers[i];
                    target = i;
                }
            }
        }
        else {
            // service n-1 customers
            for (int i = 0; i < n - 1; i++) {
                Slot slot = queue.poll();
                long nextAvailTime = slot.time + barbers[slot.barber];
                queue.add(new Slot(nextAvailTime, slot.barber));
            }
            // now for the target barber

            target = queue.poll().barber;
        }

        int solution1Based = target + 1;

        System.out.println("Case #" + caseNum + ": " + solution1Based);
    }

    private static long gcd(long a, long b) {
        while (b > 0) {
            long temp = b;
            b = a % b; // % is remainder
            a = temp;
        }
        return a;
    }

    private static long lcm(long a, long b) {
        return a * (b / gcd(a, b));
    }

    private static final class Slot implements Comparable<Slot>{

        private final long time;
        private final int barber;

        public Slot(long time, int barber) {
            this.time = time;
            this.barber = barber;
        }

        @Override
        public int compareTo(Slot other) {
            long timeDiff = time - other.time;
            return (other.time == time)
                ? barber - other.barber
                : (timeDiff > 0)
                    ? 1
                    : -1;
        }

        public String toString() {
            return barber + " : " + time;
        }
    }
}
