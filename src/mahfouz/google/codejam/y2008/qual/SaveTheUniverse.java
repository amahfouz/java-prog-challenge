package mahfouz.google.codejam.y2008.qual;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Solution for Save the Universe.
 */
public class SaveTheUniverse {

    private SaveTheUniverse(int caseNum, Scanner s) {
        int n = s.nextInt();
        s.nextLine();

        String[] engines = new String[n];
        for (int i = 0; i < engines.length; i++) {
            engines[i] = s.nextLine();
        }

        int queries = s.nextInt();
        s.nextLine();
        String[] searches = new String[queries];
        for (int i = 0; i < searches.length; i++) {
            searches[i] = s.nextLine();
        }

        int switches = 0;
        int pos = 0;
        HashSet<String> encountered = new HashSet<String>();
        while (pos < searches.length) {
            encountered.add(searches[pos]);
            if (encountered.size() == n) {
                // encountered all - need to switch
                switches++;
                encountered.clear();
                encountered.add(searches[pos]);
            }
            pos++;
        }

        System.out.println("Case #" + caseNum + ": " + switches);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("/Users/amahfouz/Desktop/gcj.in"));

        int numCases = s.nextInt();

        for (int i = 0; i < numCases; i++) {
            new SaveTheUniverse(i + 1, s);
        }
    }
}
