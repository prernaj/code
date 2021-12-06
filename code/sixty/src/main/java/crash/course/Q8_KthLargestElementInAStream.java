package crash.course;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/kth-largest-element-in-a-stream/
 * Question
 * Design a class to find the kth largest element in a stream. 
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * Implement KthLargest class:
 * KthLargest(int k, int[] nums) Initializes the object with the integer k and the stream of integers nums.
 * int add(int val) Appends the integer val to the stream and returns the element representing the kth largest element in the stream.
 */
/**
 * Input Output
 * ["KthLargest", "add", "add", "add", "add", "add"] [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
 * [null, 4, 5, 5, 8, 8]
 */
/**
 * Thought process
 * Maintain a heap of k largest numbers. 
 * - whenever a new element is added, it might qualify to be added to heap, then remove the minimum element from the heap and add this number.
 * - else ignore this number. return the top of the 
 * 
 * dry run
 * 8 5 4 (2)
 * 3 - 8,5,4,(3,2) [4]
 * 5 - 8,5,5,(4,3,2) [5]
 * 10 - 10,8,5,(5,4,3,2)[5]
 * 9 - 10,9,8 (5,5,4,3,2) [8]
 * 4 - 10,9,8 (5,5,4,4,3,2) [8]
 * 
 * Refined Logic:
 * - initializing: Add top k elements to a min heap
 * - whenever a new element is added, compare with the top of min heap, if it is larger then add that, else ignore
 * - return min_heap.top
 * 
 * Time: 
 * Initialize : O(nlogn)
 * Add: O(logk)
 * Space: O(k)
 */
/**
 * Improvement:
 * a heap is a data structure that is capable of giving you the smallest (or largest) element (by some criteria) in constant time, 
 * while also being able to add elements and remove the smallest (or largest) element in only logarithmic time. 
 * In summary, a heap:
 * Stores elements, and can find the smallest (min-heap) or largest (max-heap) element stored in O(1)O(1).
 * Can add elements and remove the smallest (min-heap) or largest (max-heap) element in O(\log(n))O(log(n)).
 * Can perform insertions and removals while always maintaining the first property.
 * 
 * The capability to remove and insert elements in \log(n)log(n) time makes heaps extremely useful.
 * n⋅log(n) is over 6000 times smaller than n^2.
 * Algorithm
 * In the constructor, create a min heap using the elements from nums. Then, pop from the heap until heap.length == k.
 * For every call to add():
 * First, push val into heap.
 * Next, check if heap.length > k. If so, pop from the heap.
 * Finally, return the smallest value from the heap, which we can get in O(1)O(1) time.
 * 
 * Time: O(nlogn + mlogk)
 * Space: O(n)
 */
public class Q8_KthLargestElementInAStream {
    PriorityQueue<Integer> pq; 
    int k;

    public Q8_KthLargestElementInAStream(int k, int[] nums) {
        pq = new PriorityQueue<Integer>();
        this.k = k;
        int n = nums.length;
        if (n > this.k) {
            Arrays.sort(nums);
            for (int i = n-1; i >= n-k; i--) {
                pq.add(nums[i]);
            }
        } else {
            for (int i = 0; i < n; i++) {
                pq.add(nums[i]);
            }
        }
    }

    public int add(int val) {
        if (pq.size() < this.k) {
            pq.add(val);
        } else if (pq.peek() < val) {
            pq.poll();
            pq.add(val);
        }
        return pq.peek();
    }
}
