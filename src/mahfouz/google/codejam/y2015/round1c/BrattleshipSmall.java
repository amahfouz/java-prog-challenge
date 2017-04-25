package mahfouz.google.codejam.y2015.round1c;

import java.util.Scanner;

public final class BrattleshipSmall {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int numCases = s.nextInt();
        s.nextLine();

        for (int i = 0; i < numCases; i++) {
            solveCase(i+1, s);
        }
    }

    private static void solveCase(int caseNum, Scanner s) {
        int r = s.nextInt();
        int c = s.nextInt();
        int w = s.nextInt();

        int solution = (c / w) + w;
        if (c % w == 0)
            solution--;

        System.out.println("Case #" + caseNum + ": " + solution);
    }
}
