package crash.course;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
 * Question
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
 * Define a pair (u, v) which consists of one element from the first array and one element from the second array.
 * Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.
 */
/**
 * Input Output
 * nums1 = [1,7,11], nums2 = [2,4,6], k = 3 => [[1,2],[1,4],[1,6]]
 * nums1 = [1,1,2], nums2 = [1,2,3], k = 2 => [[1,1],[1,1]]
 * nums1 = [1,2], nums2 = [3], k = 3 => [[1,3],[2,3]]
 */
/**
 * Thought process
 * keep two pointers.
 * decide the lowest pair from these two pointer positions.
 * increment one of the pointer to get the next higher pair.
 * keep adding such pairs to output.
 * [[1,2], [1+4 or 7+2] = [1,4], ]... NAAH...
 */
/**
 * Improvements
 * Use Heap.
 * Use min_heap to keep track on next minimum pair sum
 * For every numbers in nums1, its best partner(yields min sum) always starts from nums2[0] since arrays are all sorted; 
 * And for a specific number in nums1, its next candidate should be [this specific number] + nums2[current_associated_index + 1], unless out of boundary;)
 * Time: O(klogk)
 * Space: O(N)
 */
public class Q10_FindKPairsWithSmallestSums {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> que = new PriorityQueue<>((a,b)->a[0]+a[1]-b[0]-b[1]);
        List<int[]> res = new ArrayList<>();
        if (nums1.length==0 || nums2.length==0 || k==0) {
            return res;
        }
        for (int i=0; i<nums1.length && i<k; i++) {
            que.offer(new int[]{nums1[i], nums2[0], 0});
        }
        while(k-- > 0 && !que.isEmpty()) {
            int[] cur = que.poll();
            res.add(new int[]{cur[0], cur[1]});
            if (cur[2] == nums2.length-1) {
                continue;
            }
            que.offer(new int[]{cur[0],nums2[cur[2]+1], cur[2]+1});
        }
        return res;
    }
}
