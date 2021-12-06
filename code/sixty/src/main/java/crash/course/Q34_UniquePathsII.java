package crash.course;

/**
 * https://leetcode.com/problems/unique-paths-ii/
 * Question
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * The robot can only move either down or right at any point in time. 
 * The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * Now consider if some obstacles are added to the grids. 
 * How many unique paths would there be?
 * An obstacle and space is marked as 1 and 0 respectively in the grid.
 */
/**
 * obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]] => 2
 * obstacleGrid = [[0,1],[0,0]] => 1
 */
/**
 * Thought process
 * dp. 
 */
/**
 * Improvements
 * Since we are making use of pre-computed values along the iteration, this becomes a dynamic programming problem.
 * if obstacleGrid[i][j] is not an obstacle
 *   obstacleGrid[i,j] = obstacleGrid[i,j - 1]  + obstacleGrid[i - 1][j]
 * else
 *   obstacleGrid[i,j] = 0
 * 
 * We will be iterating the array from left-to-right and top-to-bottom. 
 * Thus, before reaching any cell we would have the number of ways of reaching the predecessor cells. 
 * This is what makes it a Dynamic Programming problem. 
 * We will be using the obstacleGrid array as the DP array thus not utilizing any additional space.
 * 
 * Time: O(M*N)
 * Space: O(1)
 */
public class Q34_UniquePathsII {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int dp[][] = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else if (i == 0 && j != 0 && dp[i][j-1] == 0) {
                    dp[i][j] = 0;
                } else if (j == 0 && i != 0 && dp[i-1][j] == 0) {
                    dp[i][j] = 0;
                } else if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }
}
