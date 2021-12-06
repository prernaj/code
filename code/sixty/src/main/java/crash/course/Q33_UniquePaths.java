package crash.course;

/**
 * https://leetcode.com/problems/unique-paths/
 * Question
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * The robot can only move either down or right at any point in time. 
 * The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * How many possible unique paths are there?
 */
/**
 * Input Output
 * m = 3, n = 7 => 28
 * m = 3, n = 2 => 3
 * m = 7, n = 3 => 28
 * m = 3, n = 3 => 3
 */
/**
 * Thought process
 * DP.
 * why dp ?
 * - asking possible unique paths
 * - the value at the current state is dependent on the previous state.
 * 
 * dp[i][j] = number of unqiue ways ending at (i,j)
 * 
 * dp[i][j] = dp[i-1][j] + dp[i][j-1] // move one step down and one step right
 * 
 * base case:
 * dp[i][0] = 1 // all down
 * dp[0][j] = 1 // all right
 * 
 * Time: O(M*N)
 * Space: O(M*N)
 */
public class Q33_UniquePaths {
    public int uniquePaths(int m, int n) {
        int dp[][] = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }
}
