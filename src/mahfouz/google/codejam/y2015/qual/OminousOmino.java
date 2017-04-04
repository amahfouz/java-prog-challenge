package mahfouz.google.codejam.y2015.qual;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Solution of Ominous Omino.
 */
public final class OminousOmino {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(System.in);

        int numCases = s.nextInt();

        for (int i = 0; i < numCases; i++) {
            int x = s.nextInt();
            int r = s.nextInt();
            int c = s.nextInt();

            boolean canWin;
            if (! (r*c % x == 0))
                canWin = false;
            else if (x == 1 || x == 2)
                canWin = true;
            else if (x == 3)
                canWin = (r > 1 && c > 1);
            else if (x == 4)
                canWin = (r > 2 && c > 2);
            else if (x == 5)
                canWin = Math.min(r, c) >= 3 && (r*c != 15);
            else if (x == 6)
                canWin = Math.min(r, c) >= 4;
            else // starting 7 and up no wins
                canWin = false;

            String winner = canWin
                ? "GABRIEL"
                : "RICHARD";

            System.out.println("Case #" + (i+1) + ": " + winner);
        }

        s.close();
    }
}
