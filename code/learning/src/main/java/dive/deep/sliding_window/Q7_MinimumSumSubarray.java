package dive.deep.sliding_window;

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
 * target = 7, nums = [2,3,1,2,4,3] => 2 {4,3}
 * target = 4, nums = [1,4,4] => 1 [4]
 * target = 11, nums = [1,1,1,1,1,1,1,1] => 0 {not possible}
 */
/**
 * Thought process
 * Brute force.
 * find sum of all possible subarrays and update the ans if it gets better.
 * Time: O(n^3)
 * Space: O(1)
 * 
 * Optimized brute force.
 * the sum is caulculated for each inted pair in O(n) time.
 * we could easily find the sum by storing the cummulative sum from the beginning.
 * 
 * time: O(n^2)
 * Space: O(n)
 * 
 * Binary search.
 * we find the subarray with sum>=s starting with an index i in O(n) time. 
 * we could reduce the time to O(log(n)) using binary search.
 * 
 * Time: O(nlogn)
 * Space: O(n)
 * 
 * Using two pointers.
 * Until now, we have kept the starting index of subarray fixed, and found the last position.
 * Instead, we could move the starting index of the current subarray as soon as we know 
 * that no better could be done with this index as the starting index.
 *  
 * Time: O(n)
 * Space: O(1)
 * 
 */
public class Q7_MinimumSumSubarray {
    public int minSubArrayLenBrute(int target, int[] nums) {
        int n = nums.length;
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += nums[k];
                }
                if (sum >= target) {
                    ans = Math.min(ans, (j - i + 1));
                    break; //Found the smallest subarray with sum>=s starting with index i, hence move to next index
                }   
            }
        }
        return (ans != Integer.MIN_VALUE) ? ans : 0;
    }

    public int minSubArrayLenBruteMemoized(int target, int[] nums) {
        int n = nums.length;
        if (n == 0)
            return 0;
        int ans = Integer.MAX_VALUE;
        int[] sums = new int[n];
        sums[0] = nums[0];
        for (int i = 1; i < n; i++)
            sums[i] = sums[i - 1] + nums[i];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int sum = sums[j] - sums[i] + nums[i];
                if (sum >= target) {
                    ans = Math.min(ans, (j - i + 1));
                    break; //Found the smallest subarray with sum>=s starting with index i, hence move to next index
                }
            }
        }
        return (ans != Integer.MAX_VALUE) ? ans : 0;
    }

    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        
        int ans = Integer.MAX_VALUE;
        int[] sums = new int[n+1]; //size = n+1 for easier calculations
        //sums[0]=0 : Meaning that it is the sum of first 0 elements
        //sums[1]=A[0] : Sum of first 1 elements
        //ans so on...
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        for (int i = 1; i <= n; i++) {
            int to_find = target + sums[i - 1];
            //int bound = lower_bound(sums.begin(), sums.end(), to_find);
            //if (bound != sums.end()) {
            //    ans = Math.min(ans, (bound - (sums.begin() + i - 1)));
            //}
        }
        return (ans != Integer.MAX_VALUE) ? ans : 0;
    }

    public int minSubArrayLenSlidingWindow(int target, int[] nums) {
        int n = nums.length;
        int ans = Integer.MAX_VALUE;
        int left = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            while (sum >= target) {
                ans = Math.min(ans, i + 1 - left);
                sum -= nums[left++];
            }
        }
        return (ans != Integer.MAX_VALUE) ? ans : 0;
    }
}
