package crash.course;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode.com/problems/top-k-frequent-elements/
 * Question
 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
 */
/**
 * Input Output
 * nums = [1,1,1,2,2,3], k = 2 => [1,2]
 * nums = [1], k = 1 => [1]
 */
/**
 * Thought process
 * create a count map. [[1,3],[2,2],[3,1]]
 * sort the map by value.
 * return top k elements.
 * 
 * Time: O(nlogn)
 */
/**
 * Improvements
 * Heap.
 * Time: O(nlogk)
 * - build a count hashmap.
 * - build a heap of size k using n elements. 
 * To add the first k elements takes a linear time O(k) in average case. O(log1+log2+...logk) = O(logk!) = O(klogk)
 * After this, we push and pop for rest N-k elements.
 * Time: O((n-k)logk)
 * Total time: O(nlogk) + O((n-k)logk) = O(nlogk)
 * - convert heap to output array in O(klogk) time.
 * 
 * Time: O(nlogk). better than O(nlogn)
 * space: O(n+k)
 * 
 * Quickselect.
 * Quickselect is a textbook algorthm typically used to solve the problems "find kth something": 
 * kth smallest, kth largest, kth most frequent, kth less frequent, etc.
 * Time: O(n) avg and O(n^2) worst
 * 
 * Approach is same as quick sort
 * One chooses a pivot and defines its position in a sorted array in a linear time using so-called partition algorithm.
 * As an output, we have an array where the pivot is on its perfect position in the ascending sorted array, sorted by the frequency. 
 * All elements on the left of the pivot are less frequent than the pivot, and all elements on the right are more frequent or have the same frequency.
 * Hence the array is now split into two parts. 
 * If by chance our pivot element took N - kth final position, then kk elements on the right are these top kk frequent we're looking for. 
 * If not, we can choose one more pivot and place it in its perfect position.
 * 
 * Algo:
 * - Build a hash map element -> its frequency
 * - Use a partition scheme (please check the next section) to place the pivot into its perfect position pivot_index in the sorted array, 
 * move less frequent elements to the left of pivot, and more frequent or of the same frequency - to the right.
 * - Compare pivot_index and N - k
 * -- If pivot_index == N - k, the pivot is N - kth most frequent element, and all elements on the right are more frequent or of the same frequency. 
 * Return these top kk frequent elements.
 * -- Otherwise, choose the side of the array to proceed recursively.
 * Code TO do later...
 * 
 */
public class Q9_TopKFrequentelements {
    public int[] topKFrequent(int[] nums, int k) {
        // O(1) time
        if (k == nums.length) {
            return nums;
        }
        
        // 1. build hash map : character and how often it appears
        // O(N) time
        Map<Integer, Integer> count = new HashMap();
        for (int n: nums) {
          count.put(n, count.getOrDefault(n, 0) + 1);
        }

        // init heap 'the less frequent element first'
        Queue<Integer> heap = new PriorityQueue<>((n1, n2) -> count.get(n1) - count.get(n2));

        // 2. keep k top frequent elements in the heap
        // O(N log k) < O(N log N) time
        for (int n: count.keySet()) {
          heap.add(n);
          if (heap.size() > k) heap.poll();    
        }

        // 3. build an output array
        // O(k log k) time
        int[] top = new int[k];
        for(int i = k - 1; i >= 0; --i) {
            top[i] = heap.poll();
        }
        return top;
    }
}
