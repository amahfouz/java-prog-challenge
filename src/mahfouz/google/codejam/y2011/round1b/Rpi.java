package mahfouz.google.codejam.y2011.round1b;

import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Solution for "RPI" problem (Round 1B 2011)
 */
public final class Rpi {

    public static void main(String[] args) throws Exception{
        Scanner s = new Scanner
            (new File("C:\\Users\\amahfouz\\Downloads\\cj.in"));
        PrintStream out = new PrintStream
            (new File("C:\\Users\\amahfouz\\Desktop\\solution.txt"));

        int numCases = s.nextInt();

        for (int i = 0; i < numCases; i++) {
            solveCase(i + 1, s, out);
        }

        s.close();
        out.close();
    }

    private static void solveCase(int caseNum, Scanner s, PrintStream out) {
        int num = s.nextInt();
        s.nextLine(); // advance past end of line
        Boolean[][] wins = readMatrix(s, num);

        double[] wp = computeWp(wins);
        double[] owp = computeOwp(wins, wp);
        double[] oowp = computeOowp(wins, owp);

        outputCase(out, caseNum, "");

        for (int i = 0; i < wp.length; i++) {
            double rpi = 0.25 * wp[i] + 0.5 * owp[i] + 0.25 * oowp[i];
            out.println(String.valueOf(rpi));
        }
    }

    private static double[] computeWp(Boolean[][] wins) {
        int numTeams = wins.length;
        double[] result = new double[numTeams];
        for (int team = 0; team < numTeams; team++)
            result[team] = computeWpFor(wins, team, -1);

        return result;
    }

    private static double computeWpFor(Boolean[][]results,
                                      int team,
                                      int indexToSkip) {
        int numTeams = results.length;
        int wins = 0;
        int gamesPlayed = 0;

        for (int opp = 0; opp < numTeams; opp++) {
            if (indexToSkip == opp)
                continue;

            if (results[team][opp] != null) {
                gamesPlayed++;

                if (results[team][opp].equals(Boolean.TRUE))
                    wins++;
            }
        }

        return (gamesPlayed != 0)
            ? (double)wins / gamesPlayed
            : 0;

    }

    private static double[] computeOwp(Boolean[][] results, double[] wp) {
        int numTeams = results.length;
        double[] owp = new double[numTeams];
        for (int team = 0; team < numTeams; team++) {
            int numOpponents = 0;
            for (int opp = 0; opp < numTeams; opp++) {
                if (results[team][opp] != null) {
                    numOpponents++;
                    owp[team] += computeWpFor(results, opp, team);
                }
            }
            owp[team] /= numOpponents;
        }

        return owp;
    }

    private static double[] computeOowp(Boolean[][] results, double[] owp) {
        int numTeams = results.length;
        double[] oowp = new double[numTeams];
        for (int team = 0; team < numTeams; team++) {
            int numOpponents = 0;
            for (int opp = 0; opp < numTeams; opp++) {
                if (results[team][opp] != null) {
                    numOpponents++;
                    oowp[team] += owp[opp];
                }
            }
            oowp[team] /= numOpponents;
        }
        return oowp;
    }

    private static Boolean[][] readMatrix(Scanner s, int num) {
        String row;
        char c;
        Boolean[][] wins = new Boolean[num][num];

        for (int i = 0; i < num; i++) {
            row = s.nextLine();
            for (int j = 0; j < row.length(); j++) {
                c = row.charAt(j);
                switch (c) {
                    case '1':
                        wins[i][j] = Boolean.TRUE;
                        break;

                    case '0':
                        wins[i][j] = Boolean.FALSE;
                        break;

                    case '.':
                        wins[i][j] = null;
                        break;

                    default:
                        throw new IllegalArgumentException();
                }
            }
        }
        return wins;
    }

    private static void outputCase(PrintStream out, int caseNum, String output) {
        out.println("Case #" + caseNum + ": " + output);
    }
}
