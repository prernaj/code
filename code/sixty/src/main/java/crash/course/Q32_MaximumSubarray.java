package crash.course;


/**
 * https://leetcode.com/problems/maximum-subarray/
 * Question
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 * A subarray is a contiguous part of an array.
 */
/**
 * Input Output
 * nums = [-2,1,-3,4,-1,2,1,-5,4] => 6
 * nums = [1] => 1
 * nums = [5,4,-1,7,8] => 23
 */
/**
 * Thought Process
 * first compute the cummulative sum. then compute sum for each subarray. keep track of the max sum.
 * Time: O(N^2).
 * 
 * The trick is to recognize that all of the subarrays starting at a particular value will share a common prefix.
 * Algo:
 * 1. Initialize a variable maxSubarray = -infinity to keep track of the best subarray. 
 * We need to use negative infinity, not 0, because it is possible that there are only negative numbers in the array.
 * 2. Use a for loop that considers each index of the array as a starting point.
 * 3. For each starting point, create a variable currentSubarray = 0. 
 * Then, loop through the array from the starting index, adding each element to currentSubarray. 
 * Every time we add an element it represents a possible subarray - so continuously update maxSubarray to contain the maximum out of the currentSubarray and itself.
 * 4. Return maxSubarray.
 * Time: O(N^2)
 * Space: O(1)
 * 
 * DP. Kadane's Algorithm
 * We can see that the optimal subarray couldn't possibly involve the first 3 values - the overall sum of those numbers would always subtract from the total.
 * Let's start with an empty array, and iterate through the input, adding numbers to our array as we go along. 
 * However, we don't actually need to build the subarray, we can just keep an integer variable current_subarray and add the values of each element there. 
 * When it becomes negative, we reset it to 0 (an empty array).
 * 
 * Algorithm:
 * Algorithm
 * - Initialize 2 integer variables. Set both of them equal to the first value in the array.
 * --- currentSubarray will keep the running count of the current subarray we are focusing on.
 * --- maxSubarray will be our final return value. Continuously update it whenever we find a bigger subarray.
 * - Iterate through the array, starting with the 2nd element (as we used the first element to initialize our variables). 
 * For each number, add it to the currentSubarray we are building. 
 * If currentSubarray becomes negative, we know it isn't worth keeping, so throw it away. 
 * Remember to update maxSubarray every time we find a new maximum.
 * - Return maxSubarray.
 * 
 * A clever way to update currentSubarray is using currentSubarray = max(num, currentSubarray + num). If currentSubarray is negative, then num > currentSubarray + num.
 * Time: O(N)
 * Space: O(1)
 * 
 * Divide And Conquer
 * To be studied later...
 * 
 */
public class Q32_MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int maxSubarray = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int currentSubarray = 0;
            for (int j = i; j < nums.length; j++) {
                currentSubarray += nums[j];
                maxSubarray = Math.max(maxSubarray, currentSubarray);
            }
        }
        
        return maxSubarray;
    }

    public int maxSubArrayKadane(int[] nums) {
        // Initialize our variables using the first element.
        int currentSubarray = nums[0];
        int maxSubarray = nums[0];
        
        // Start with the 2nd element since we already used the first one.
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            // If current_subarray is negative, throw it away. Otherwise, keep adding to it.
            currentSubarray = Math.max(num, currentSubarray + num);
            maxSubarray = Math.max(maxSubarray, currentSubarray);
        }
        
        return maxSubarray;
    }
}
