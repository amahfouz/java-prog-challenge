package mahfouz.google.codejam.y2017.qual;

import java.util.Scanner;

public final class BLarge {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int numCases = s.nextInt();
        s.nextLine();

        for (int i = 0; i < numCases; i++) {
            solveCase(i+1, s);
        }
    }

    private static void solveCase(int caseNum, Scanner s) {
        StringBuffer b = new StringBuffer(s.nextLine());

        int pos = 0;
        while (pos < b.length() - 1) {
            if (b.charAt(pos) <= b.charAt(pos+1))
                pos++;
            else {
                // convert the rest to nines
                for (int i = pos+1; i < b.length(); i++) {
                    b.setCharAt(i, '9');
                }
                // subtract 1 from the prefix
                // see if there is carry over
                // find a sequence of equal digits
                int count = 1;

                while (pos - count >= 0 && b.charAt(pos - count) == b.charAt(pos))
                    count++;

                int startOfSeq = pos - count + 1;

                b.setCharAt(startOfSeq, (char)(b.charAt(startOfSeq) - 1));
                if (b.charAt(startOfSeq) == '0')
                    b.setCharAt(startOfSeq, '9');

                for (int i = startOfSeq + 1; i <= pos; i++)
                    b.setCharAt(i, '9');

                // special case - carry over to leftmost digit
                if (b.charAt(0) == '9')
                    b.deleteCharAt(0);

                // done
                break;
            }
        }

        System.out.println("Case #" + caseNum + ": " + b.toString());
    }
}
