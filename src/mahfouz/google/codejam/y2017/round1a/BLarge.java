package mahfouz.google.codejam.y2017.round1a;

import java.util.Scanner;

public final class BLarge {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int numCases = s.nextInt();
        s.nextLine();

        for (int i = 0; i < numCases; i++) {
            solveCase(i+1, s);
        }
    }

    private static void solveCase(int caseNum, Scanner s) {
        int solution = 0;
        System.out.println("Case #" + caseNum + ": " + solution);
    }

}
