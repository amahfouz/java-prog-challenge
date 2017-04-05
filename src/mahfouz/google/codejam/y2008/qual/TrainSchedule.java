package mahfouz.google.codejam.y2008.qual;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public final class TrainSchedule {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int numCases = s.nextInt();

        for (int i = 0; i < numCases; i++) {
            solveCase(i+1, s);
        }
    }

    private static void solveCase(int caseNum, Scanner s) {
        int turnaround = s.nextInt();
        int na = s.nextInt();
        int nb = s.nextInt();

        ArrayList<Event> a = new ArrayList<Event>();
        ArrayList<Event> b = new ArrayList<Event>();

        for (int i = 0; i < na; i++) {
            int depart = readTime(s);
            int arrive = readTime(s) + turnaround;
            s.nextLine();

            a.add(new Event(depart, false));
            b.add(new Event(arrive, true));
        }

        for (int i = 0; i < nb; i++) {
            int depart = readTime(s);
            int arrive = readTime(s) + turnaround;
            s.nextLine();

            b.add(new Event(depart, false));
            a.add(new Event(arrive, true));
        }

        Collections.sort(a);
        Collections.sort(b);

        int numA = solve(a);
        int numB = solve(b);

        System.out.println("Case #" + caseNum + ": " + numA + " " + numB);
    }

    private static int solve(ArrayList<Event> events) {

        int curTrainsAvail = 0;
        int requiredAtStart = 0;

        for (Event e : events) {
            if (e.isArrival)
                curTrainsAvail++;
            else {
                if (curTrainsAvail == 0)
                    requiredAtStart++;
                else
                    curTrainsAvail--;
            }
        }
        return requiredAtStart;
    }

    private static int readTime(Scanner s) {
        String time = s.next();
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

    private static final class Event implements Comparable<Event> {

        private final int time;
        private final boolean isArrival;

        public Event(int time, boolean isArrival) {
            this.time = time;
            this.isArrival = isArrival;
        }

        @Override
        public int compareTo(Event o) {
            if (!(o instanceof Event))
                throw new IllegalArgumentException();

            int diff = time - o.time;
            return diff != 0
                ? diff
                : Boolean.compare(o.isArrival, isArrival);
        }


    }
}
