package mahfouz.algo.tree;

import java.util.Arrays;

/**
 * Implementation of a Fenwick, Binary Indexed Tree (BIT). 
 */
public final class FenwickTree {

    // 1-based array
    private final int[] tree;
    
    public FenwickTree(int numNodes) {
        
        // tree[0] unused
        this.tree = new int[numNodes + 1];
    }
    
    // add 'value' at the specified index 
    public void updateNode(int index, int value) {
        
        while (index <= numElements()) {
            tree[index] += value;
            index += (index & -index);
        }
    }

    public int query(int index) {
        int sum = 0;
        for(; index > 0; index -= index&-index)
            sum += tree[index];
        return sum;        
    }
    
    //
    // private
    //

    private int numElements() {
        // exclude unused first element
        return tree.length - 1;
    }
    
    private void print() {
        System.out.println(Arrays.asList(tree));
    }
    
    //
    // static
    //
    
    public static void main(String[] args) {
        int[] elements = new int[]  {1, 17, 23, 24};
        int numElements = elements.length;
        FenwickTree tree = new FenwickTree(numElements);
        for (int i = 1; i <= numElements; i++) {
            tree.updateNode(i, elements[i-1]);
        }
        tree.print();
        
        System.out.println(tree.query(2));
        System.out.println(tree.query(3));
        System.out.println(tree.query(4));
    }
}
