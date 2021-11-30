package dive.deep.sliding_window;

/**
 * https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/
 * Question
 * Given an array of integers nums and an integer limit, 
 * return the size of the longest non-empty subarray 
 * such that the absolute difference between any two elements of this subarray is less than or equal to limit.
 */
/**
 * Input Output
 * nums = [8,2,4,7], limit = 4 => 2
 * nums = [10,1,2,4,7,2], limit = 5 => 4
 * nums = [4,2,2,2,4,4,2,2], limit = 0 => 3
 */
/**
 * Thought process
 * use the two pointer technique, 
 * moving the right pointer as far as possible to the right until the subarray is not valid (maxValue - minValue > limit), 
 * then moving the left pointer until the subarray is valid again (maxValue - minValue <= limit). 
 * Keep repeating this process.
 */
public class Q6_LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {
    
}
