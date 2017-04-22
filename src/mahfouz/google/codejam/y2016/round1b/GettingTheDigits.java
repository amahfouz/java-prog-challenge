package mahfouz.google.codejam.y2016.round1b;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public final class GettingTheDigits {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int numCases = s.nextInt();
        s.nextLine();

        for (int i = 0; i < numCases; i++) {
            solveCase(i+1, s);
        }
    }

    private static void solveCase(int caseNum, Scanner s) {
        String str = s.nextLine();
        char[] alpha = new char[26];

        for (int i = 0; i < str.length(); i++) {
            alpha[str.charAt(i) - 'A']++;
        }

        ArrayList<Integer> out = new ArrayList<Integer>();

        String[] words = {
          "ZERO",
          "WTO",
          "UFOR",
          "XIS",
          "GEIHT",
          "ONE",
          "FIVE",
          "VESEN",
          "THREE",
          "INEN"
        };
        int[] nums = {0, 2, 4, 6, 8, 1, 5, 7, 3, 9};

        // look for the key letter
        for (int w = 0; w < words.length; w++) {
            String word = words[w];
            char key = word.charAt(0);

            // find the key letter
            int occurs = alpha[key - 'A'];
            alpha[key - 'A'] = 0;

            // add to the output
            for (int i = 0; i < occurs; i++)
                out.add(nums[w]);

            // account for the rest of letters
            for (int c = 1; c < word.length(); c++)
                alpha[word.charAt(c) - 'A'] -= occurs;
        }

        Collections.sort(out);
        StringBuffer solution = new StringBuffer();
        for (int digit : out)
            solution.append(digit);

        System.out.println("Case #" + caseNum + ": " + solution);
    }
}
