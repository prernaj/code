package crash.course;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/two-sum/
 * Question
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order.
 */
/**
 * Input Output
 * nums = [2,7,11,15], target = 9 => [0,1]
 * nums = [3,2,4], target = 6 => [1,2]
 * nums = [3,3], target = 6 => [0,1]
 */
/**
 * Thought process
 * find sum of each pair.
 * Time: O(n^2)
 * 
 * store numbers in a map.
 * for each number search its target_sum - num in the map.
 * Time: O(n)
 * Space: O(n)
 */
/**
 * Improvement
 * One pass hash table
 * While we are iterating and inserting elements into the hash table, 
 * we also look back to check if current element's complement already exists in the hash table. 
 * If it exists, we have found a solution and return the indices immediately.
 */
public class Q11_TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        // In case there is no solution, we'll just return null
        return null;
    }
}
