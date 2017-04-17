package mahfouz.google.codejam.y2016.qual;

import java.util.ArrayList;
import java.util.Scanner;

public final class Fractiles {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int numCases = s.nextInt();
        s.nextLine();

        for (int i = 0; i < numCases; i++) {
            solveCase(i+1, s);
        }
    }

    private static void solveCase(int caseNum, Scanner scan) {
        int k = scan.nextInt();
        int c = scan.nextInt();
        int s = scan.nextInt();

        ArrayList<Long> tiles = new ArrayList<Long>();
        if (c == 1) {
            for (long i = 0; i < k; i++) {
                tiles.add(i + 1);
            }
        }
        else {
            long tileOrd = 1;
            do {
                long tileNum = tileOrd;

                int curLevel = 1;
                while (curLevel < c) {
                    curLevel++;
                    if (tileOrd < k)
                        tileOrd++;

                    tileNum = (tileNum - 1) * k + tileOrd;
                }
                tiles.add(tileNum);
            }
            while (tileOrd < k);
        }

        if (tiles.size() > s) {
            System.out.println("Case #" + caseNum + ": " + "IMPOSSIBLE");
            return;
        }

        StringBuffer b = new StringBuffer();
        for (int i = 0; i < tiles.size(); i++) {
            b.append(tiles.get(i));
            if (i < tiles.size() - 1)
                b.append(' ');
        }
        System.out.println("Case #" + caseNum + ": " + b.toString());
    }

}
