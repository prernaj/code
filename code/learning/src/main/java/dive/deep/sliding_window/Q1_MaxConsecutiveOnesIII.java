package dive.deep.sliding_window;

/**
 * https://leetcode.com/problems/max-consecutive-ones-iii/
 * Question
 * Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
 */

/**
 * nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2 => 6
 * nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3 => 10
 */

/**
 * Thought process
 * longest contiguous subarray that contains only 1s.
 * k flips are allowed from 0 to 1. 
 * this means contiguous subarray of 1s might not just contain 1s but also may contan some 0s.
 * 
 * Sliding window.
 * 
 * in any sliding window based problem we have two pointers.
 * one right pointer whose job is to expand the current window and then we have the left pinter whose job is to contract a given window.
 * at any point in time only one of the pointers move and the other remains fixed.
 * 
 * We will flip a zero if it extends an existing window of 1s.
 * basic construct: in a given window, we can never have greater than K zeros.
 * we don't have a fixed size window. it can grow or shrink depending upon the number of zeros.
 * 
 * solution.
 * we keep on expanding the window by moving the right pointer.
 * when the window has reached the limits of 0s allowed, we contract and save the longest window till now.
 * the answer is the longest desirable window.
 * 
 * Algo:
 * 1. Initialize: left = 0, right = 0, window_size = 0
 * 2. increase right till we have desirable number of zeros.
 * 3. once we have a window which has more than the allowed number of zeros, move left pointer one by one until we encounter 0 on the left too. this step ensures we are throwing out extra zeros.
 * 
 * litte more efficiency:
 * since we have found the maximum size window, we never reduce the size, we move both pointers together.
 * 
 * Time: O(n)
 * Space: O(1)
 * 
 */
public class Q1_MaxConsecutiveOnesIII {
    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int count = 0;
        int result = Integer.MIN_VALUE;
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                count++;
            }
            while(count > k && left < nums.length) {
                if (nums[left] == 0) {
                    count--;
                }
                left++;
            }
            result = Math.max(result, right-left+1);
         }
        return result == Integer.MIN_VALUE ? ((count <= k) ? nums.length : 0) : result;
    }
}
