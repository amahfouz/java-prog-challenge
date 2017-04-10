package mahfouz.google.codejam.y2016.womenio;

import java.util.Scanner;

public final class Polynesiaglot {

    private static final long MOD = 1000000007;

    private static void solveCase(int caseNum, Scanner s) {
        int c = s.nextInt();
        int v = s.nextInt();
        int l = s.nextInt();

        // add one letter words

        long startWithV = v; // only vowels can appear alone
        long startWithC = 0;

        for (int i = 2; i <= l ; i++) {
            // append either a vowel or a consonant to the beginning

            long tmp = startWithC;
            startWithC = c * startWithV;
            startWithV = v * (startWithV + tmp);

            if (startWithV > MOD)
                startWithV = startWithV % MOD;

            if (startWithC > MOD)
                startWithC = startWithC % MOD;
        }

        long sum = startWithC + startWithV;

        System.out.println("Case #" + caseNum + ": " + (sum % MOD));
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int numCases = s.nextInt();

        for (int i = 0; i < numCases; i++) {
            solveCase(i+1, s);
        }
    }
}
