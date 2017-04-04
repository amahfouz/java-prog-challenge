package mahfouz.google.codejam.y2015.qual;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Solution of Ominous Omino.
 */
public final class OminousOmino {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("/Users/amahfouz/Desktop/gcj.in"));

        int numCases = s.nextInt();

        for (int i = 0; i < numCases; i++) {
            int x = s.nextInt();
            int r = s.nextInt();
            int c = s.nextInt();

            final boolean canWin;
            if (x == 1)
                canWin = true;
            else if (x == 2)
                canWin = r*c % 2 == 0;
            else if (x == 3)
                canWin = r*c % 3 == 0 && (r > 1 && c > 1);
            else if (x == 4)
                canWin = r*c % 4 == 0 && (r > 2 && c > 2);
            else
                canWin = false;

            String winner = canWin
                ? "GABRIEL"
                : "RICHARD";

            System.out.println("Case #" + (i+1) + ": " + winner);
        }

        s.close();
    }
}
