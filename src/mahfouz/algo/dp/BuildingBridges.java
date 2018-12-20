package mahfouz.algo.dp;

/**
 * Solution to building bridges problem.
 * https://www.geeksforgeeks.org/dynamic-programming-building-bridges/
 * The solution is the same as the one for the 
 * https://www.geeksforgeeks.org/weighted-job-scheduling/
 */
public final class BuildingBridges {
    
    public int constructBridges(Pair[] pairs) throws Exception {
        
        // start by the east-most city in the north
        // assume a bridge is constructed for that city
        // determine all cities that will be cut-off by
        // going back and finding the east-most city that
        // to the west of the south counterpart and 
        // recurse from there
        
        int[] numBridges = new int[pairs.length];
        
        numBridges[0] = 1;
        
        for (int i = 1; i < pairs.length; i++) {
            int numIfExcluded = numBridges[i-1];
            int eastMostWestOfCurStart = findEastMostEnd(pairs, i);
            int numIfIncluded 
                = (eastMostWestOfCurStart > 0) 
                ? 1 + numBridges[eastMostWestOfCurStart]
                : 1; // no more can be constructed, just count this one
            numBridges[i] = Math.max(numIfExcluded, numIfIncluded);
        }
        
        return numBridges[pairs.length - 1];
    }

    /**
     * Find index of city whose east end is closest to the west 
     * of the start of the pair corresponding to the specified 
     * index.
     */
    private int findEastMostEnd(Pair[] pairs, int startIndex) {
        int i = startIndex - 1;
        while (i >= 0) {
            if (pairs[i].end <= pairs[startIndex].start)
                return i;
            i--;
        }

        // none found
        return -1;
    }

//    private void checkValidity(int[] north, int[] south) throws Exception {
//        // do nothing
//    }
//    
//    private constructPairs(int[] north, int[] south) {
//        checkValidity(north, south);
//        // north is sorted in ascending order
//        
//    }
    
    //
    // Nested
    //
    
    private static final class Pair implements Comparable {
        
        private final int start, end;
        
        public Pair(int firstX, int secondX) {
            if (firstX > secondX) {
                end = firstX;
                start = secondX;
            }
            else {
                end = secondX;
                start = firstX;
            }
        }
        
        public int compareTo(Object obj) {
            if (!(obj instanceof Pair))
                throw new IllegalArgumentException();
            
            Pair other = (Pair)obj;
            return this.end - other.end;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BuildingBridges bb = new BuildingBridges();
        int [] north = new int[] { 8, 1, 4, 3, 5, 2, 6, 7};
        int [] south = new int[] { 1, 2, 3, 4, 5, 6, 7, 8};
        Pair[] pairs = new Pair[north.length]; 
        for (int i = 0; i < pairs.length; i++) {
            pairs[i] = new Pair(north[i], south[i]);
        }
        
        int numBridges = bb.constructBridges(pairs);
        System.out.println(numBridges);
    }
}
