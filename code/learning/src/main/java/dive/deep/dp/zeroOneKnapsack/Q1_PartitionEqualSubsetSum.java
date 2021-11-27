package dive.deep.dp.zeroOneKnapsack;

/**
 * Given a non-empty array nums containing only positive integers, 
 * find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 */

 /**
  * Input Output
  * [1,5,11,5] => true
  * [1,2,3,5] => false
  */

  /**
   * Thought process
   * totalSum = subSetsum * 2
   * => subSetSum = totalSum/2
   * Now, the problem is to find subset with a given sum.
   * 
   * Point to be noted: the totalSum should be even.
   * 
   * Brute force:
   * Generate all possible subsets of an array and return true if we find a subset with the required sum.
   * For a given element in array, there are two possibilities:
   * 1. it is included in the subSet
   * 2. it is not included in the subSet
   * 
   * We can use DFS and recursively calculate the subSetSum for each case and check if either of them is true.
   * isSum(subSetSum, n) = isSum(subSetSum - nums[n], n-1) || isSum(subSetSum, n-1)
   * 
   * Base Cases:
   * - if subSetSum == 0, return true
   * - if subSetSum < 0, return false
   * 
   * Time: O(2^n)
   * Space: O(n). recursive stack. We cannot have more than n recursive calls on the stack at any time.
   * 
   * Memoization. BEST approach
   * In recursion tree, there are overlapping cases.
   * Let n be the number of array elements and m be the subSetSum.
   * Time: O(m.n)
   * Space: O(m.n) matrix size and O(n) recursive stack size
   * 
   * Bottom up. Tabulation. I did not understand this one.
   * We maintain a dp[n][subSetSum].
   * 
   * dp[i][s] = dp[i-1][s] || dp[i-1][s-nums[i]]
   * 
   * dry run: [1,5,11,5]
   * dp[][] = new Boolean[4][12]
   * dp[0][0] = true;
   * i = 1, j = 0, curr = 1: j < curr => dp[1][0] = dp[0][0] = true
   * i = 1, j = 1, curr = 1; dp[1][1] = dp[0][1] || dp[0][0] = true
   * i = 1, j = 11, curr = 1; dp[1][11] = dp[0][11] || dp[0][10] = false
   * 
   * i = 2 IDK how it works !!
   * 
   * if n is the number of elements and m is the subSetSum
   * Time: O(m.n)
   * Space: O(m.n)
   * 
   * Optimized DP. using 1d array.
   * 
   */


public class Q1_PartitionEqualSubsetSum {
    
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        if (sum % 2 != 0) {
            return false;
        }
        
        /** 1 */
        //return isSum(sum/2, n-1, nums);

        /** 2 */
        int subSetSum = sum/2;
        Boolean[][] memo = new Boolean[n+1][subSetSum+1];
        return isSumMemo(sum/2, n-1, nums, memo);


    }

    public boolean isSum(int targetSum, int i, int[] nums) {
        if (targetSum == 0) {
            return true;
        }
        if (targetSum < 0) {
            return false;
        }
        return isSum(targetSum-nums[i], i-1, nums) || isSum(targetSum, i-1, nums);
    }

    public Boolean isSumMemo(int targetSum, int i, int[] nums, Boolean[][] memo) {
        if (targetSum == 0) {
            return true;
        }
        if (i == 0 || targetSum < 0) {
            return false;
        }
        if (memo[i][targetSum] != null) {
            return memo[i][targetSum];
        }
        Boolean res = isSum(targetSum-nums[i], i-1, nums) || isSum(targetSum, i-1, nums);
        memo[i][targetSum] = res;
        return res;
    }

    public Boolean canPartitionDP(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        if (sum % 2 != 0) {
            return false;
        }
        int subSetSum = sum/2;
        Boolean[][] dp = new Boolean[n+1][subSetSum+1];
        dp[0][0] = Boolean.TRUE;

        for (int i = 1; i <= n; i++) {
            int curr = nums[i-1];
            for (int j = 0; j <= subSetSum; j++) {
                dp[i][j] = dp[i-1][j] || dp[i-1][j-curr];
            }
        }
        return dp[n][subSetSum];
    }

}
