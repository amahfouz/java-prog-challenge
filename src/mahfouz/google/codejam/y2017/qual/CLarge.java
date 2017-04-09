package mahfouz.google.codejam.y2017.qual;

import java.util.Scanner;

public final class CLarge {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int numCases = s.nextInt();
        s.nextLine();

        for (int i = 0; i < numCases; i++) {
            solveCase(i+1, s);
        }
    }

    private static void solveCase(int caseNum, Scanner s) {
        double n = s.nextDouble();
        double k = s.nextDouble();

        // find largest power of 2 smaller than k

        double exp = Math.floor(Math.log10(k) / Math.log10(2.0));
        double pow = Math.pow(2.0, exp);
        double largestSeg = Math.ceil((n - k + 1) / pow);

        double ls, rs;
        if (largestSeg % 2 == 0.0) {
            rs = largestSeg / 2.0;
            ls = rs - 1;
        }
        else {
            ls = rs = (largestSeg - 1) / 2.0;
        }

        double max = Math.max(ls, rs);
        double min = Math.min(ls, rs);

        System.out.printf("Case #" + caseNum + ": %.0f %.0f%n", max, min);
    }
}
