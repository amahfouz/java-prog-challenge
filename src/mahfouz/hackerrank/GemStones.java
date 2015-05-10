package mahfouz.hackerrank;

import java.util.Scanner;

public final class GemStones {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int numStones = s.nextInt();
        s.nextLine(); // skip end of line

        String[] stones = new String[numStones];

        for (int i = 0; i < numStones; i++)
            stones[i] = s.nextLine();

        int result = 0;

        nextElement:
        for (char element = 'a'; element <= 'z'; element++) {

            for (int i = 0; i < stones.length; i++) {
                if (stones[i].indexOf(element) < 0)
                    continue nextElement;
            }
            result++;
        }

        System.out.println(result);
    }
}
