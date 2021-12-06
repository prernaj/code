package crash.course;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/subarray-sum-equals-k/
 * Question
 * Given an array of integers nums and an integer k, return the total number of continuous subarrays whose sum equals to k.
 */
/**
 * Input Output
 * nums = [1,1,1], k = 2 => 2
 * nums = [1,2,3], k = 3 => 2
 */
/**
 * Thought process
 * Brute force.
 * compute sum of each subarray.
 * Time: O(n^3) Space: O(1)
 * 
 * compute the left continuous sum.
 * them compute sum of each subarray.
 * Time: O(n^2) Space: O(n)
 */
/**
 * Improvements
 * Without extra space.
 * We can choose a particular startstart point and while iterating over the endend points, 
 * we can add the element corresponding to the endend point to the sum formed till now. 
 * Whenever the sumsum equals the required kk value, we can update the countcount value. 
 * We do so while iterating over all the endend indices possible for every startstart index. 
 * Whenever, we update the startstart index, we need to reset the sumsum value to 0.
 * Time: O(n^2)
 * Space: O(1)
 * 
 * Using HashMap
 * If the cumulative sum up to two indices is the same, the sum of the elements lying in between those indices is zero.
 * Extending the same thought further, 
 * if the cumulative sum up to two indices, say i and j is at a difference of k i.e. if 
 * sum[i]−sum[j]=k, the sum of elements lying between indices i and j is k.
 * Time: O(n)
 * Space: O(n)
 */
public class Q16_SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        HashMap<Integer,Integer> map = new HashMap <>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k))
                count += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
