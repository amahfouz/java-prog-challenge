package mahfouz.google.codejam.y2016.qual;

import java.util.Scanner;

public final class RevengeOfPancakes {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int numCases = s.nextInt();
        s.nextLine();

        for (int i = 0; i < numCases; i++) {
            solveCase(i+1, s);
        }
    }

    private static void solveCase(int caseNum, Scanner s) {
        String stack = s.nextLine();
        char cur = '+';
        int flips = 0;
        for (int i = stack.length() -1; i >=0; i--) {
            char next = stack.charAt(i);
            if (cur != next)
                flips++;
            cur = next;
        }

        System.out.println("Case #" + caseNum + ": " + flips);
    }
}
