package mahfouz.google.codejam.y2011.round1c;

import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Solution for "Perfect Harmony" small input (Round 1C 2011)
 */
public final class PerfectHarmonySmall {

    public static void main(String[] args) throws Exception{
        Scanner s = new Scanner
            (new File("C:\\Users\\amahfouz\\Downloads\\cj.in"));
        PrintStream out = new PrintStream
            (new File("C:\\Users\\amahfouz\\Desktop\\solution.txt"));

        int numCases = s.nextInt();

        for (int i = 0; i < numCases; i++) {
            solveCase(i + 1, s, out);
        }
    }

    private static void solveCase(int caseNum, Scanner s, PrintStream out) {
        int numPlayers = s.nextInt();
        int lo = s.nextInt();
        int hi = s.nextInt();

        int[] freqs = new int[numPlayers];
        for (int i = 0; i < numPlayers; i++)
            freqs[i] = s.nextInt();

        int result = 0;
        if (lo <= 1 && hi >= 1)
            result = 1;
        else {
            for (int f = lo; f <= hi; f++) {
                if (checkFreqInHarmony(f, freqs)) {
                    result = f;
                    break;
                }
            }
        }

        outputCase(out, caseNum,
                   (result != 0)
                       ? String.valueOf(result)
                       : "NO");
    }

    private static boolean checkFreqInHarmony(int f, int[] freqs) {
        for (int i = 0; i < freqs.length; i++) {
            if ((f % freqs[i] != 0) && (freqs[i] % f != 0))
                return false;
        }
        return true;
    }

    private static void outputCase(PrintStream out, int caseNum, String output) {
        out.println("Case #" + caseNum + ": " + output);
    }
}
