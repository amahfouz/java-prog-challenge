package mahfouz.algo.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Solution for 
 * https://practice.geeksforgeeks.org/problems/n-meetings-in-one-room/0
 */
public final class MeetingsInOneRoom {

    // keep picking the meetings that ends the soonest
    // exclude meetings that overlap with it
    public static List<Integer> schedule(Meeting[] meetings) {
        List<Integer> result = new ArrayList<Integer>();
        Arrays.sort(meetings);
        int toAdd = 0;
        while (toAdd < meetings.length) {
            result.add(meetings[toAdd].id);
            
            int candidate = toAdd + 1;
            while (candidate < meetings.length) {
                if (meetings[candidate].start >= meetings[toAdd].finish) {
                    // non-conflicting. Add it.
                    toAdd = candidate;
                    break;
                }
                else {
                    candidate++;
                }
            }
            if (candidate >= meetings.length)
                break;
        }
        return result;
    }
    
    public static void main(String[] args) {

        int[] s = new int[] 
            {75250, 50074, 43659, 8931, 11273, 27545, 50879, 77924};
        
        int[] f = new int[]
            {112960, 114515, 81825, 93424, 54316, 35533, 73383, 160252};
        
        if (s.length != f.length)
            throw new IllegalArgumentException();
        
        Meeting [] meetings = new Meeting[s.length];
        for (int i = 0; i < meetings.length; i++) {
            meetings[i] = new Meeting(i+1, s[i], f[i]);
        }
        
        System.out.println(schedule(meetings));
    }
    
    
    private static final class Meeting implements Comparable<Meeting> {
        
        private final int id, start, finish;

        public Meeting(int id, int start, int finish) {
            this.id = id;
            this.start = start;
            this.finish = finish;
        }

        @Override
        public int compareTo(Meeting other) {
            return this.finish - other.finish;
        }
    }
}
