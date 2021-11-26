package top.k.greedy.num_1_meeting_rooms_II;

import java.util.PriorityQueue;

class Solution1 {
    public int minMeetingRooms(int[][] intervals) {
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
