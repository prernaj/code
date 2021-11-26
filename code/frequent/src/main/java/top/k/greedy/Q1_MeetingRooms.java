package top.k.greedy;

import java.util.PriorityQueue;

/**
 * Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], 
 * return the minimum number of conference rooms required.

*/

/** 
[[0,30],[5,10],[15,20]] => 2
[[7,10],[2,4]] => 1
 */

class Q1_MeetingRooms {
    public  int minMeetingRooms(int[][] intervals) {
        PriorityQueue<Integer> endTimePQ = new PriorityQueue<>();
        java.util.Arrays.sort(intervals, new java.util.Comparator<int[]>() {
                                                public int compare(int[] a, int[] b) {
                                                        return Integer.compare(a[0], b[0]);
                                                }
                                        });
        for (int i = 0; i < intervals.length; i++) {
            if (endTimePQ.isEmpty()) {
                endTimePQ.add(intervals[i][1]);
            } else {
                int minTime = endTimePQ.peek();
                if (minTime <= intervals[i][0]) {
                    endTimePQ.poll();
                }
                endTimePQ.add(intervals[i][1]);
            }
        }
        return endTimePQ.size();
    }
}

/**
 * 
- Process the meetings in increasing order of their start time. 
- Maintain a min priority queue of rooms sorted by end time to check if any room is free or not. 
- The size of the heap will tell the number of rooms allocated.

Time Complexity:
Time: O(nlogn) 
Sorting O(nlogn)
Also in worst case all n meetings will collide. In that case n adds and n extract-mins. O(nlogn)
Space :O(n) 

Dry Run:
[[0,30],[5,10],[15,20]]
Add room 30
Add room 10
Allocated 15,20 to room 10
End.
Heap size = 2

[[1,10],[2,7],[3,19],[8,12],[10,20],[11,30]]
Add room 1,10
Add room 2,7
Add room 3,19
Replace room 2,7 with 8,12
Replace room 1,10 with 10,20
Add room 11,30
Heap size = [3,19],[8,12],[10,20],[11,30] = 4

 */
