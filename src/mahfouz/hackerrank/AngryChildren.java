package mahfouz.hackerrank;

import java.util.Arrays;
import java.util.Scanner;

public final class AngryChildren {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int numPacks = s.nextInt();
        int k = s.nextInt();

        int[] packs = new int[numPacks];

        for (int i = 0; i < numPacks; i++) {
            packs[i] = s.nextInt();
        }

        Arrays.sort(packs);

        int minDiff = Integer.MAX_VALUE;

        for (int i = 0; i <= numPacks - k; i++) {
            int diff = packs[i + k - 1] - packs[i];
            if (diff < minDiff) {
                minDiff = diff;
            }
        }
        System.out.println(minDiff);
    }
}
