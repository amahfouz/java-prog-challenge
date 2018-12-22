package mahfouz.algo.dp;

/**
 * Solution for the box stacking problem.
 * https://www.geeksforgeeks.org/box-stacking-problem-dp-22/
 */
public final class BoxStacking {

    private final Box[] boxes;
    
    public BoxStacking(Box[] boxes) {
        this.boxes = boxes;
    }

    // For every box type, 
    //    try each pair of dimensions if any fits
    //    add the third dimension to the height
    //    recurse with the new dimensions
    // return total
    //
    // Exponential
    //
    public int solveRecursive(int curW, int curD) {
        
        int max = 0;
        int temp; 
        
        for (int b = 0; b < boxes.length; b++) {
            
            Box box = boxes[b];
            int w = box.w;
            int h = box.h;
            int d = box.d;
            
            // try w, h
            if (w < curW && h < curD || w < curD && h < curW) {
                temp = d + solveRecursive(w, h);
                if (temp > max)
                    max = temp;
            }
            
            // try w, d
            if (w < curW && d < curD || w < curD && d < curW) {
                temp = h + solveRecursive(w, d);
                if (temp > max)
                    max = temp;
            }
            
            // try d, h
            if (d < curW && h < curD || d < curD && h < curW) {
                temp = w + solveRecursive(d, h);
                if (temp > max)
                    max = temp;
            }
        }
        return max;
    }
    
    public static final class Box {
        
        private final int d, w, h;

        public Box(int d, int w, int h) {
            this.d = d;
            this.w = w;
            this.h = h;
        }
    }
    
    public static void main(String[] args) {
       Box[] boxes = new Box[] { 
            new Box(4, 6, 7), 
            new Box(1, 2, 3), 
            new Box(4, 5, 6), 
            new Box(10, 12, 32) 
       }; 
       
       BoxStacking problem = new BoxStacking(boxes);
        
       int solution = problem.solveRecursive
           (Integer.MAX_VALUE, Integer.MAX_VALUE);
       
       System.out.println(solution);
    }
}
