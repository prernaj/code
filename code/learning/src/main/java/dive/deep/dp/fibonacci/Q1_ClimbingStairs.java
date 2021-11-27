package dive.deep.dp.fibonacci;

/**
 * Question
 * You are climbing a staircase. It takes n steps to reach the top.
 * Each time you can either climb 1 or 2 steps. 
 * In how many distinct ways can you climb to the top?
 */

 /**
  * Input Output
  * n = 2 => 2
  * n = 3 => 3
  */

  /**
   * Thought Process
   * 
   * Consider all possible combinations. 
   * climb_stairs(i) = climb_stairs(i+1) + climb_stairs(i+2)
   * 
   * Time Complexity: O(2^n). Size of recursion tree will be 2^n.
   * Space Complexity: O(n). The depth of the recursion tree can go upto n.
   * 
   * 
   * How to start from 0 and reach n?
   * Recursion
   * - start from 0
   * - move to next 1 and next 2
   * - if you reach the end return 1
   * - if you reah greater than end return 0
   * 
   * - do this recursively.
   * - add memoization
   * 
   * Dry Run:
   * n = 6
   * i = 0
   * i = 1 and i = 2
   * i = 2 and i = 3
   * i = 3 and i = 4
   * i = 4 and i = 5
   * i = 5 and i = 6
   * i == 6 => 1
   * i == 7 => 0
   * 
    * Time : O(n). Size of recursion tree can grow upto n
    * Space: O(n). Depth of recursion can grow upto n.
    * 
    * To reach nth stair, what could have been previous steps ?
    * DP
   * We can reach nth step via (n-1)th step or (n-2)th step.
   * So, the total number of ways to reach n = ways to reach n-1 + ways to reach n-2
   * dp[i] = dp[i-1] + dp[i-2]
   * 
   * Time: O(n). Single loop upto n
   * Space: O(n). dp array os size n is used.
   * 
   * Fibonacci 
   * As seen in the dp, dp[i] is nothing but the ith fibonacci number.
   * Fib(n) = Fib(i-1) + Fib(i-2)
   * Now, we just have to find nth fibonacci number of fibonacci series having 1 and 2 their first and second term respectively.
   * Fib(1) = 1 and Fib(2) = 2.
   * 
   * Time: O(n). Single loop upto n is required to calculate nth fibonacci number.
   * Space: O(1). Constant space is used.
   * 
   * There is more to this... refer leetcode link. 
   * 
    */

public class Q1_ClimbingStairs {
    public int climbStairs(int n) {
        int memo[] = new int[n+1];
        return climb_stairs_recur(0, n, memo);
    }

    public int climb_stairs(int i, int n) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        return climb_stairs(i+1, n) + climb_stairs(i+2, n);
    }

    public int climb_stairs_recur(int i, int n, int[] memo) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }

        if (memo[i] > 0) {
            return memo[i];
        }

        memo[i] = climb_stairs_recur(i+1, n, memo) + climb_stairs_recur(i+2, n, memo);
        return memo[i];
    }

    public int climb_stairs_dp(int i, int n, int[] memo) {
        int[] dp = new int[n+1];
        if (n == 1) {
            return 1;
        }
        dp[0] = 0;
        dp[1] = 1;
        if (n >= 2) {
            dp[2] = 2;
        }
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    public int climb_stairs_fib(int n) {
        if (n == 1) {
            return 1;
        }
        int first = 1;
        int second = 2;
        for (int i = 3; i <= n; i++) {
            int third = first + second;
            first = second;
            second = third;
        }
        return second;
    }

}

/**
 * https://leetcode.com/problems/climbing-stairs/
 */
