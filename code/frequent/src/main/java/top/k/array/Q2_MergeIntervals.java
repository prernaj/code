package top.k.array;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, 
 * and return an array of the non-overlapping intervals that cover all the intervals in the input.
 */

 /**
  * intervals = [[1,3],[2,6],[8,10],[15,18]] Output: [[1,6],[8,10],[15,18]]
  * intervals = [[1,4],[4,5]] Output: [[1,5]]
  */

  /**
   * Approach:
   * sort by start time. O(nlogn)
   * Merge each new element to the last element in the list. O(n)
   * 
   */

public class Q2_MergeIntervals {
    public int[][] mergeIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0], b[0]));
        LinkedList<int[]> mergedList = new LinkedList<>();
        for (int[] interval : intervals) {
            if (mergedList.isEmpty() || mergedList.getLast()[1] < interval[0]) {
                mergedList.add(interval);
            } else {
                mergedList.getLast()[1] = Math.max(mergedList.getLast()[1], interval[1]);
            }
        }
        return mergedList.toArray(new int[mergedList.size()][]);
    }
}

/**
 * Constraints:
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 */
