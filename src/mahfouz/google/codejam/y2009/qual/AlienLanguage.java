/*
 * CONFIDENTIAL
 * Copyright 2013 Webalo, Inc.  All rights reserved.
 */

package mahfouz.google.codejam.y2009.qual;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Solution for Alien Language problem (Qualification Round 2009)
 */
public final class AlienLanguage {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int wordLength = scanner.nextInt();
        int numWords = scanner.nextInt();
        int numTestCases = scanner.nextInt();

        scanner.nextLine();

        Node root = new Node();

        for (int i = 0; i < numWords; i++) {
            String word = scanner.nextLine();
            root.add(word, 0);
        }

        for (int i = 0; i < numTestCases; i++)
            solveTestCase(i + 1, root, scanner.nextLine(), wordLength);
    }

    private static void solveTestCase
        (int caseNum, Node root, String pattern, int lettersPerWord) {

        Set<Node> cur = new HashSet<Node>();
        Set<Node> next = new HashSet<Node>();
        cur.add(root);
        int readIndex = 0;
        int tokens = 0;

        while (tokens < lettersPerWord) {

            char letter = pattern.charAt(readIndex);
            if (letter == '(')
                while ((letter = pattern.charAt(++readIndex)) != ')')
                    populateNextSet(cur, next, letter);
            else
                populateNextSet(cur, next, letter);

            ++readIndex;
            ++tokens;

            cur.clear();
            cur.addAll(next);
            next.clear();

            if (cur.isEmpty())
                break;
        }
        System.out.println("Case #" + caseNum + ": " + cur.size());
    }

    private static void populateNextSet
        (Set<Node> curLevel, Set<Node> nextLevel, char letter) {

        for (Node node : curLevel) {
            Node child = node.getFor(letter);
            if (child != null)
                nextLevel.add(child);
        }
    }

    //
    // nested types
    //

    static final class Node {

        private final Node[] children = new Node['z' - 'a' + 1];

        public void add(String word, int indexInWord) {
            int letter = word.charAt(indexInWord);
            int letterIndex = letter - 'a';

            int lettersRemaining = word.length() - indexInWord - 1;

            if (children[letterIndex] == null)
                children[letterIndex] = new Node();
//                children[letterIndex] = (lettersRemaining == 0)
//                    ? this // do not create children for leaves
//                    : new Node();

            if (lettersRemaining > 0)
                children[letterIndex].add(word, indexInWord + 1);
        }

        public Node getFor(char letter) {
            int letterIndex = letter - 'a';
            return children[letterIndex];
        }
    }
}
