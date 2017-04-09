package mahfouz.google.codejam.y2017.qual;

import java.util.Scanner;

public final class BSmall {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int numCases = s.nextInt();
        s.nextLine();

        for (int i = 0; i < numCases; i++) {
            solveCase(i+1, s);
        }
    }

    private static void solveCase(int caseNum, Scanner s) {
        int max = s.nextInt();

        for (int i = max; i >= 1; i--) {

            boolean tidy = true;

            int num = i;
            int cur = 10;
            int next;
            while (num != 0) {
                next = num % 10;
                if (next > cur) {
                    tidy = false;
                    break; // not tidy
                }

                num = num / 10;
                cur = next;
            }
            if (tidy) {
                System.out.println("Case #" + caseNum + ": " + i);
                return;
            }
        }
    }
}
