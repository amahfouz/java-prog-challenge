package mahfouz.google.codejam.y2008.qual;

import java.util.Random;
import java.util.Scanner;

/**
 * Probabilistic approximation of the solution to Fly Swatter.
 */
public final class FlySwatter {

    private static final double TWO_PI = 2 * Math.PI;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int numCases = s.nextInt();

        for (int i = 0; i < numCases; i++) {
            solveCase(i+1, s);
        }
    }

    private static void solveCase(int caseNum, Scanner s) {
        double f = s.nextFloat();
        double R = s.nextFloat();
        double t = s.nextFloat();
        double r = s.nextFloat();
        double g = s.nextFloat();

        Random randomRadius = new Random();
        Random randomAngle = new Random();

        // simulate the fly position
        int hits = 0;
        for (int i = 0; i < 100000000; i++) {

            // generate random position with origin at center
            double angle = randomAngle.nextDouble() * TWO_PI;
            double radius = randomRadius.nextDouble() * R;
            double x = radius * Math.cos(angle);
            double absx = Math.abs(x);
            double y = radius * Math.sin(angle);
            double absy = Math.abs(y);

            // evaluate the position wrt the raquet

            // 1. Is it on the outer rim
            if (radius + f > R - t) {
                hits++;
                continue;
            }

            // 2. Is it on the center chords
            if (absx - f < r || absy - f < r) {
                hits++;
                continue;
            }

            // 3. General case
            double localX = (absx - r) % (g + 2 * r);
            if (localX - f < 0 || localX + f > g) {
                hits++;
                continue;
            }
            double localY = (absy - r) % (g + 2 * r);
            if (localY - f < 0 || localY + f > g) {
                hits++;
                continue;
            }
        }

        double probability = (double)hits / 100000000.0;

        System.out.println("Case #" + caseNum + ": " + probability);
    }
}
