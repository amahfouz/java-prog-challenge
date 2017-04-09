package mahfouz.google.codejam.y2017.qual;

import java.util.Scanner;

public final class A {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int numCases = s.nextInt();
        s.nextLine();

        for (int i = 0; i < numCases; i++) {
            solveCase(i+1, s);
        }
    }

    private static void solveCase(int caseNum, Scanner s) {
        String cakesStr = s.next().trim();
        StringBuffer cakes = new StringBuffer(cakesStr);
        int k = s.nextInt();

        boolean possible = true;
        int flips = 0;
        int pos = 0;
        while (possible && pos < cakes.length()) {
            if (cakes.charAt(pos) == '+') {
                pos++;
                continue;
            }

            // check room to flip
            if (pos+k > cakes.length()) {
                possible = false;
                break;
            }

            flips++;

            // do actual flip
            for (int i = pos; i < pos + k; i++) {
                char flipped = cakes.charAt(i) == '-' ? '+' : '-';
                cakes.setCharAt(i, flipped);
            }
        }

        String solution = possible ? String.valueOf(flips) : "IMPOSSIBLE";
        System.out.println("Case #" + caseNum + ": " + solution);
    }
}
