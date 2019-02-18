package mahfouz.algo.tree;

/**
 * Segment tree for storing the minimum value in a range.
 */
public final class RangeMinSegmentTree {
    
    // original items array
    private final int[] items;
    // holds indices, rather than values, from original items array
    private final int[] seg;
    
    public RangeMinSegmentTree(int[] items, int[] seg) {
        this.items = items;
        this.seg = seg;
    }
    
    /**
     * Queries for the combination of values in the given range.
     */
    public int query(int qLo, int qHi) {
        if (qLo > qHi || qLo < 0 || qHi > items.length - 1)
            throw new IllegalStateException();
        
        // query starting at the root
        return query(qLo, qHi, 0, items.length - 1, 0);
    }
    
    public void print() {
        for (int i = 0; i < seg.length; i++) {
            System.out.print(seg[i] + " ");
        }
        System.out.println();
    }
    
    private int query(int qLo, int qHi, int segLo, int segHi, int index) {
        if (qLo <= segLo && qHi >= segHi)
            return seg[index];
        
        if (qLo > segHi || qHi < segLo)
            return -1;
        
        int mid = findMid(segLo, segHi);
        
        int left = query(qLo, qHi, segLo, mid, 2 * index + 1);
        int right = query(qLo, qHi, mid + 1, segHi, 2 * index + 2);
        
        if (left < 0)
            return right;
        
        if (right < 0)
            return left;
        
        return items[left] < items[right]
            ? left
            : right;
    }
    
    private static int findMid(int lo, int hi) {
        return lo + (hi - lo) / 2;
    }
    
    //
    // Nested
    //

    /**
     * Builds a segment tree from an items array.
     */
    public static final class Builder {
        
        private final RangeMinSegmentTree tree;
        
        public Builder(int[] items) {
            int height = (int) Math.ceil(Math.log(items.length) / Math.log(2));
            int size = 2 * (int) Math.pow(2, height) - 1;
            int[] seg = new int[size];

            this.tree = new RangeMinSegmentTree(items, seg);
            
            populate(0, items.length - 1, 0);
        }
        
        public RangeMinSegmentTree getTree() {
            return tree;
        }
        
        /**
         * "lo" and "hi" are indices in the original items array
         * whereas "index" is the index of the item in the segment
         * tree that is being populated.
         */
        private int populate(int lo, int hi, int index) {
            if (lo == hi) {
                tree.seg[index] = lo;
                return lo;
            }
            
            int mid = RangeMinSegmentTree.findMid(lo, hi);
            
            // populate the segment children
            int left = populate(lo, mid, 2 * index + 1);
            int right = populate(mid + 1, hi, 2 * index + 2);
            tree.seg[index] = tree.items[left] < tree.items[right]
                ? left
                : right;
            
            return tree.seg[index];
        }
    }
    
}
