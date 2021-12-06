package crash.course;

/**
 * https://leetcode.com/problems/paint-fence/
 * Question
 * You are painting a fence of n posts with k different colors. 
 * You must paint the posts following these rules:
 * - Every post must be painted exactly one color.
 * - There cannot be three or more consecutive posts with the same color.
 * Given the two integers n and k, return the number of ways you can paint the fence.
 */
/**
 * Input Output
 * n = 3, k = 2 => 6 {Say red and green, RRG, RGR, RGG, GGR, GRG, GRR}
 * n = 1, k = 1 => 1
 * n = 7, k = 2 => 42
 */
/**
 * Thought process
 * Initialize a colour array. implying the number of conscutive times it is used.
 * Use that to pain the blocks. recursively.
 * bas.
 */
/**
 * Improvements
 * How to realize this is a DP problem?
 * 1. it is asking for "number of ways" to do something.
 * 2. making a decision depends on the previous decision.
 * 
 * How to set the framework:
 * A dynamic programming algorithm typically has 3 components. 
 * Learning these components is extremely valuable, as most dynamic programming problems can be solved this way.
 * #1. 
 * We need some function or array that represents the answer to the problem for a given state. 
 * For this problem, let's say that we have a function totalWays, where totalWays(i) returns the number of ways to paint i posts. 
 * Because we only have one argument, this is a one-dimensional dynamic programming problem.
 * #2. 
 * we need a way to transition between states, such as totalWays(3) and totalWays(4). 
 * This is called a recurrence relation and figuring it out is usually the hardest part of solving a problem with dynamic programming. 
 * We'll talk about the recurrence relation for this problem below.
 * #3. 
 * establishing base cases.
 * If we have one post, there are k ways to paint it. 
 * If we have two posts, then there are k * k ways to paint it (since we are allowed to paint have two posts in a row be the same color).
 * Therefore, totalWays(1) = k, totalWays(2) = k * k.
 * 
 * Finding the Recurrence Relation
 * we need a formula for totalWays(i), where 3 <= i <= n
 * Let's think about how many ways there are to paint the ith post. We have two options:
 * 1. Use a different color than the previous post. 
 * 2. Use the same color as the previous post.
 * 
 * If we use a different color, then there are k - 1 colors for us to use. 
 * This means there are (k - 1) * totalWays(i - 1) ways to paint the ith post a different color than the (i−1)th post.
 * 
 * If we use the same colour as previous. There is only one color for us to use, 
 * so there are 1 * totalWays(i - 1) ways to paint the ith post the same color as the (i−1)th post.
 * However, we have the added restriction of not being allowed to paint three posts in a row the same color.
 * Therefore, we can paint the ith post the same color as the (i−1)th post only if the (i−1)th post is a different color than the (i−2)th post.
 * So, how many ways are there to paint the (i−1)th post a different color than the (i−2)th post? 
 * Well, as stated in the first option, there are (k - 1) * totalWays(i - 1) ways to paint the ith post a different color than the (i−1)th 
 * post, so that means there are 1 * (k - 1) * totalWays(i - 2) ways to paint the (i−1)th post a different color than the (i−2)th post.
 * Adding these two scenarios together gives:
 * totalWays(i) = (k - 1) * (totalWays(i - 1) + totalWays(i - 2))
 * 
 * HAPPY ENDING.
 * 
 * Implementation of DP:
 * - top-down DP (recursion + memoization) Time: O(n) Space:O(n)
 * - bottom-up (tabulation) Time: O(n) Space: O(n)
 * - bottom-up constant space. consider it like fibonaaci
 */
public class Q30_PaintFence {
    public int numWays(int n, int k) {
        int[] dp = new int[n];
        dp[0] = k;
        if (n >= 2) {
            dp[1] = k*k;
        
            for (int i = 2; i < n; i++) {
                dp[i] = (k-1)*(dp[i-1] + dp[i-2]);
            }
        }
        return dp[n-1];
    }
}
