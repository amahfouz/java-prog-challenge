package mahfouz.hackerrank;

import java.util.Scanner;

public final class GameOfThrones {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String inputString = scanner.nextLine();

        int[] counts = new int['z' - 'a' + 1];

        for (int i = 0; i < inputString.length(); i++) {
            char c = inputString.charAt(i);
            counts[c - 'a']++;
        }

        boolean hasOddNum = false;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] % 2 != 0) {
                if (hasOddNum) {
                    System.out.println("NO");
                    return;
                }
                else
                    hasOddNum = true;
            }
        }
        System.out.println("YES");
    }
}
