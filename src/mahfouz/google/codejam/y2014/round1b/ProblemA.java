package mahfouz.google.codejam.y2014.round1b;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Template for GCJ solutions.
 */
public final class ProblemA {

    private final String[] words;
    private final List<Character> chars;
    private final List<Integer>[] freqs;

    private void solveCase(int caseNum, PrintStream out) {

        for (int i = 0; i < words.length; i++) {
            freqs[i] = reduce(words[i]);

            if (freqs[i] == null || (freqs[i].size() != chars.size())) {
                outputCase(out, caseNum, "Fegla Won");
                return;
            }
        }

        int sum = 0;
        for (int i = 0; i < chars.size(); i++) {
            sum += findMedian(i);
        }


//        int maxDist = 0;
//
//        for (int i = 0; i < freqs.length - 1; i++) {
//            for (int j = i + 1; j < freqs.length; j++) {
//
//                int dist = dist(freqs[i], freqs[j]);
//
//                if (dist == -1) {
//                    outputCase(out, caseNum, "Fegla Won");
//                    return;
//                }
//
//                if (dist > maxDist)
//                    maxDist = dist;
//            }
//        }
        outputCase(out, caseNum, sum);
    }

    private int findMedian(int charFreqIndex) {
        int minDist = 101;
        int dist;
        for (int median = 1; median <= 100; median++) {
            dist = 0;
            for (int i = 0; i < freqs.length; i++) {
                dist += Math.abs(median - freqs[i].get(charFreqIndex));
            }
            if (dist < minDist)
                minDist = dist;
        }
        return minDist;
    }

    private List<Character> findChars(String sample) {
        List<Character> chars = new ArrayList();
        chars.add(sample.charAt(0));

        int index = 0;
        while (index < sample.length()) {
            if (sample.charAt(index) == chars.get(chars.size() - 1))
                index++;
            else {
                chars.add(sample.charAt(index));
            }
        }
        return chars;
    }

    private List reduce(String a) {
        List<Integer> freqs = new ArrayList();

        int charIndex = 0;
        int indexA = 0;
        int curCount = 0;

        while (indexA < a.length() && charIndex < chars.size()) {
            if (a.charAt(indexA) == chars.get(charIndex)) {
                curCount++;
                indexA++;
            }
            else {
                charIndex++;
                freqs.add(curCount);
                curCount = 0;

                if (freqs.size() > chars.size())
                    return null;
            }
        }

        if (indexA == a.length()) {
            freqs.add(curCount);
        }
        else // not all chars at end consumed
            return null;

        if (freqs.size() != chars.size())
            return null;

        for (int i = 0; i < freqs.size(); i++)
            if (freqs.get(i) == 0)
                return null;

        return freqs;
    }

    private ProblemA(Scanner s) {
        int num = s.nextInt();
        s.nextLine(); // past end of line
        this.words = new String[num];
        for (int i = 0; i < words.length; i++) {
            words[i] = s.nextLine();
        }
        this.chars = findChars(words[0]);
        this.freqs = new ArrayList[num];
    }

    public static void main(String[] args) throws Exception{
        Scanner s = new Scanner
            (new File("C:\\Users\\amahfouz\\Downloads\\cj.in"));
        PrintStream out = new PrintStream
            (new File("C:\\Users\\amahfouz\\Desktop\\solution.txt"));

        int numCases = s.nextInt();

        for (int i = 0; i < numCases; i++) {
            new ProblemA(s).solveCase(i + 1, out);
            System.out.println("Finished " + (i + 1));
        }

        s.close();
        out.close();
    }

    private static void outputCase(PrintStream out, int caseNum, String output) {
        out.println("Case #" + caseNum + ": " + output);
    }

    private static void outputCase(PrintStream out, int caseNum, long output) {
        out.println("Case #" + caseNum + ": " + output);
    }

}
