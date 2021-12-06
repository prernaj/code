package crash.course;

/**
 * https://leetcode.com/problems/minimum-size-subarray-sum/
 * Question
 * Given an array of positive integers nums and a positive integer target, 
 * return the minimal length of a contiguous subarray [numsl, numsl+1, ..., numsr-1, numsr] 
 * of which the sum is greater than or equal to target. 
 * If there is no such subarray, return 0 instead.
 */
/**
 * Input Output
 * target = 7, nums = [2,3,1,2,4,3] => 2
 * target = 4, nums = [1,4,4] => 1
 * target = 11, nums = [1,1,1,1,1,1,1,1] => 0
 */
/**
 * Thought process
 * sliding window.
 * keep left to 0 and right to 1.
 * if sum < target, move right
 * if sum >= target, move left
 * keep track of max sum.
 * Time: O(n)
 * Space: O(1)
 */
/**
 * Improvements
 * brute force.
 * find the sum of all possible subarrays and update the answer as and when we get a better subarray that fulfills the requirement.
 * Time: O(n^3)
 * Space: O(1)
 * 
 * better brute force.
 * 
 */
public class Q49_MinimumSizeSubarraySum {
    
}
