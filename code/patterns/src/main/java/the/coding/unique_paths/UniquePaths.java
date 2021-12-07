package the.coding.unique_paths;

/**
 * https://leetcode.com/problems/unique-paths/
 * Question
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * The robot can only move either down or right at any point in time. 
 * The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * How many possible unique paths are there?
 */
/**
 * m = 3, n = 7 => 28
 * m = 3, n = 2 => 3
 * m = 7, n = 3 => 28
 * m = 3, n = 3 => 6
 */
/**
 * Solution
 * dp. bottoms up.
 * dp[i][j] = dp[i-1][j] + dp[i][j-1]
 * 
 * base case
 * dp[i][0] = 1 // only move right
 * dp[0][j] = 1 // only move down
 */
/** Approach
 * start with recursion.
 * traverse one path.
 * then recurisn with memoization.
 * bottom up tabular dp.
 * 
 * Time: O(m*n)
 * Space: O(m*n)
 */
/**
 * Improvements
 * The problem is a classical combinatorial problem: there are h + vh+v moves to do from start to finish
 * h=m−1 horizontal moves, and v = n - 1v=n−1 vertical ones.
 * One could choose when to move to the right, i.e. to define hh horizontal moves, and that will fix vertical ones. 
 * Or, one could choose when to move down, i.e. to define vv vertical moves, and that will fix horizontal ones.
 * In other words, we're asked to compute in how many ways one could choose pp elements from p + kp+k elements.
 * ... to be continued... (refer leetcode).
 */
public class UniquePaths {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
}
