package mahfouz.google.codejam.y2017.round1a;

import java.util.Scanner;

public final class BSmall {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int numCases = s.nextInt();
        s.nextLine();

        for (int i = 0; i < numCases; i++) {
            solveCase(i+1, s);
        }
    }

    private static void solveCase(int caseNum, Scanner s) {
        int n = s.nextInt(); // num ingredients
        int p = s.nextInt(); // num packages of each

        int[] recipe = new int[n];
        for (int i = 0; i < recipe.length; i++) {
            recipe[i] = s.nextInt();
        }

        int [][] ing = new int[n][p];
        for (int i = 0; i < ing.length; i++) {
            for (int j = 0; j < ing[i].length; j++) {
                ing[i][j] = s.nextInt();
            }
        }

        for (int i = 0; i < recipe.length; i++) {
            replaceByServings(ing[i], recipe[i]);
        }

        // match

        int totalKits = 0;
        for (int col = 0; col < ing[0].length; col++) {
            int packs = ing[0][col];
            if (packs == 0)
                continue;
            if (othersHaveServings(ing, packs))
                totalKits ++;
        }

        System.out.println("Case #" + caseNum + ": " + totalKits);
    }

    private static boolean othersHaveServings(int[][] ing, int packs) {
        for (int i = 1; i < ing.length; i++) {
            boolean found = false; // assume not found
            for (int j = 0; j < ing[i].length; j++) {
                if (ing[i][j] == packs) {
                    ing[i][j] = 0;
                    found = true;
                    break; // go to next ingredient
                }
            }
            // serving not found in ith ingredient
            if (! found)
                return false;
        }
        return true;
    }

    private static void replaceByServings(int[] ing, int recipe) {
        for (int i = 0; i < ing.length; i++)
            ing[i] = getNumServings(ing[i], recipe);
    }

    private static int getNumServings(int actual, int needed) {
        int whole = actual / needed;
        double needD = needed;

        if (whole > 0) {
            double remainPerPack = (actual % needed) / whole;


            if (remainPerPack <= needD *.1)
                return whole;
        }
        // try next one up

        whole++;
        needD = whole * needed;

        if (actual >= .9 * needD)
            return whole;

        return 0;
    }
}
