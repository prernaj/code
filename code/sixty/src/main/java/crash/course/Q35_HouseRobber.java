package crash.course;

/**
 * https://leetcode.com/problems/house-robber/
 * Question
 * You are a professional robber planning to rob houses along a street. 
 * Each house has a certain amount of money stashed, 
 * the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and 
 * it will automatically contact the police if two adjacent houses were broken into on the same night.
 * Given an integer array nums representing the amount of money of each house, 
 * return the maximum amount of money you can rob tonight without alerting the police.
 */
/**
 * Input Output
 * nums = [1,2,3,1] => 4
 * nums = [2,7,9,3,1] => 12
 */
/**
 * Thought process
 * Include exclude.
 * DP. why dp ?
 * - maxium amount
 * - answer to current state depends on previous state
 * 
 * dp[i] is the amount of money that is robbed by robber till ith house
 * 
 * dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1])
 */
/**
 * Improvements
 * A series of choices essentially gives us a subset of houses from the original list. 
 * We need to make these choices in such a way that the overall profit is maximized.
 * There is no greedy solution.
 * The best greedy strategy may be to check the neighboring houses and only rob a house if it gives them more money than the neighbors combined.
 * But no it is also not right. 100+150+400+200+100 !!
 * 
 * What we need is to try all the possibilities and see which one gives us (the robber) the optimal loot.
 * 
 * Recursion with memoization.
 * try all possible combinations of house choices and then use the combination that gives the maximum amount of money to the robber.
 * The basic choice that we make is whether to rob the current house or not. 
 * If the robber decides to rob the current house, they have to skip the next house. 
 * Otherwise, they can evaluate the same choice on the next house i.e. to rob or not to rob.
 * 
 * A suffix of the initial set of houses simply means a smaller set of houses that the robber has to consider. 
 * We will be working with the assumption that in the function call robFrom(i), the robber has to maximize their profit from i..N houses.
 * 
 * At each step, the robber has two options. 
 * If he chooses to rob the current house, he will have to skip the next house on the list by moving two steps forward. 
 * If he chooses not to rob the current house, he can simply move on to the next house in the list.
 * 
 * robFrom(i)=max(robFrom(i+1),robFrom(i+2)+nums(i))
 * 
 * base case:
 * if out of index return 0;
 * if only one house, return the amount in that house.
 * 
 * Time: O(N)
 * Space: O(N)
 * 
 * DP.
 * space optimized dp. like fibonacci. time: O(N) space: O(1)
 */
public class Q35_HouseRobber {
    public int rob(int[] nums) {
        
        int N = nums.length;
        
        // Special handling for empty array case.
        if (N == 0) {
            return 0;
        }
        
        int robNext, robNextPlusOne;
        
        // Base case initializations.
        robNextPlusOne = 0;
        robNext= nums[N - 1];
        
        // DP table calculations. Note: we are not using any
        // table here for storing values. Just using two
        // variables will suffice.
        for (int i = N - 2; i >= 0; --i) {
            
            // Same as the recursive solution.
            int current = Math.max(robNext, robNextPlusOne + nums[i]);
            
            // Update the variables
            robNextPlusOne = robNext;
            robNext = current;
        }
        
        return robNext;
    }
}
