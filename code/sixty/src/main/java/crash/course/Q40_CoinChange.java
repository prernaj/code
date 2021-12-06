package crash.course;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/coin-change/
 * Question
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
 * Return the fewest number of coins that you need to make up that amount. 
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 * You may assume that you have an infinite number of each kind of coin.
 */
/**
 * Input Output
 * coins = [1,2,5], amount = 11 => 3
 */
/**
 * Thought process
 * sort coins in descending order.
 * recursively decrease amount from coins till you reach zero. 
 * Naah this is greedy approach.
 * WA.
 * [186,419,83,408] => 6249
 */
/**
 * Improvements
 * enumerate all subsets of coin frequencies
 * use backtracking to generate all combinations of coin frequencies in the range (0, S/coins[i]) such that the sum of x[i]*coins[i] for i in 0 to n is equal to S.
 * the xi can vary from 0 to total_amount/coins[i].
 * for each combination keep rack of minimum number of coins. 
 * 
 * Time: O(S^n)
 * Reason is that every coin denomination could have at most S/ci values.
 * Therefore the number of possible combinatons is S/c1*S/c2*S/c3*...*S/cn = (S^n)/c1*c2*...*cn
 * 
 * Space: O(n)
 * Therefore we need O(n) space used by the system recursive stack.
 * 
 * DP. top-down 
 * The problem could be solved in polynomial time using DP technique.
 * Definition:
 * F(s): minimum number of coins needed to make change for amount S using coin denominations (c0...cn-1).
 * How to split the problem into subproblems? 
 * Let's assume that we know F(S) where some change val_1, val_2,... for S which is optimal and the last coin's denomination is C.
 * We compute F(S−ci) for each possible denomination c_0, c_1, c_2,...Cn-1 and choose the minimum among them.
 * 
 * Recurrence relation:
 * F(S)=min i=0...n−1 F(S−ci)+1 subject to S−ci ≥ 0.
 * 
 * Base cases:
 * F(S)=0,when S=0
 * F(S)=−1,when n=0
 * 
 * In the recursion tree above, we could see that a lot of subproblems were calculated multiple times.
 * Therefore we should cache the solutions to the subproblems in a table and access them in constant time when necessary.
 * 
 * Time: O(S*n)
 * Space: O(S)
 * 
 * DP. bottom-up. tabular.
 * Before calculating F(i), we have to compute all minimum counts for amounts up to i.
 * On each iteration i of the algorithm F(i) is computed as min j=0…n−1F(i−cj)+1 
 * 
 * Time:O(S*n)
 * Space: O(S)
 */
public class Q40_CoinChange {
    public int coinChange(int[] coins, int amount) {
        return coinChange(0, coins, amount);
      }
    
      private int coinChange(int idxCoin, int[] coins, int amount) {
        if (amount == 0)
          return 0;
        if (idxCoin < coins.length && amount > 0) {
          int maxVal = amount/coins[idxCoin];
          int minCost = Integer.MAX_VALUE;
          for (int x = 0; x <= maxVal; x++) {
            if (amount >= x * coins[idxCoin]) {
              int res = coinChange(idxCoin + 1, coins, amount - x * coins[idxCoin]);
              if (res != -1)
                minCost = Math.min(minCost, res + x);
            }
          }
          return (minCost == Integer.MAX_VALUE)? -1: minCost;
        }
        return -1;
      }

      public int coinChangeBottomUp(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
          for (int j = 0; j < coins.length; j++) {
            if (coins[j] <= i) {
              dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
            }
          }
        }
        return dp[amount] > amount ? -1 : dp[amount];
      }

      public int coinChangeTopDown(int[] coins, int amount) {
        if (amount < 1) return 0;
        return coinChangeTopDown(coins, amount, new int[amount]);
      }
    
      private int coinChangeTopDown(int[] coins, int rem, int[] count) {
        if (rem < 0) return -1;
        if (rem == 0) return 0;
        if (count[rem - 1] != 0) return count[rem - 1];
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
          int res = coinChangeTopDown(coins, rem - coin, count);
          if (res >= 0 && res < min)
            min = 1 + res;
        }
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
      }
}
