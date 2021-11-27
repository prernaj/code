package dive.deep.dp.fibonacci;

/**
 * Question
 * You are a professional robber planning to rob houses along a street. 
 * Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that 
 * adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * Given an integer array nums representing the amount of money of each house, 
 * return the maximum amount of money you can rob tonight without alerting the police.
 * 
 * Input Output
 * nums = [1,2,3,1] 
 * nums = [2,7,9,3,1] 
 * 
 * My stupid thought process:
 * Since, all number are positive, either the robber should rob all even houses or he/she should rob odd houses.
 * 1+3 or 2+1
 * 2+9+1 or 7+3
 * Seemed correct for these two examples.
 * But. failed.
 * [2,1,1,2] => 4
 * 
 * There is no greedy way of dciding if the robber should rob a house or not.
 * The best greedy strategy may be to check the neighbouring houses and only rob a house if it gives them more money than the neighbours combined.
 * Consider [100,150,400,200,100] => Answer is indexes [0,2,4] and not [2,4]
 * Hence, not optimal.
 * 
 * What we need to try all the possibilities and see which one gives us the optimal loot.
 * 
 * Recursion.
 * Technically, a robber can come back and rob a house that they previously rejected.
 * However, since we are trying out all options, we will not go back and rob an unrobber house since that scenario will be covered in a different recursive path.
 * 
 * The basic choice that we make is whether to rob the current house or not. 
 * If the robber decides to rob the current house, they have to skip the next house. 
 * Otherwise, they can evaluate the same choice on the next house i.e. to rob or not to rob.
 * 
 * To approach a problem recursively, we need to make sure that it can be broken down into sub-problems.
 * robFrom(i) = Math.max(robFrom(i+1), robFrom(i+2)+nums[i])
 * 
 * robFrom(0) = Math.max(robFrom(1), robFrom(2)+robs[0])
 * robFrom(1) = Math.max(robFrom(2), robFrom(3)+robs[1])
 * ...
 * Overlapping subproblems in the recursion tree.
 * 
 * Time: O(n). At most n recursive calls.
 * space: O(n). max entries in recursve cache. also storage by cache.
 * 
 * DP with tabular approach.
 * The recursive approach may run into trouble when the recursive stack grows too large.
 * It may also run into trouble, because, for each recursive call, the compiler must do additional work to maintain the call stack which results in unwanted overhead.
 * Every dp solution has a table that we populate starting with base case or the simplest of cases for which we already know the answer.
 * For our problem, bases cases are when there are no more options left, then profit = 0. And if only one house is left, then proft is equal to money in that house.
 * 
 * Time: O(n). single loop
 * Space: O(n). dp table.
 * 
 * Space optimized dp. like fibonacci.
 * To calculate the current value, we just need to rely on the next two consecutive values/recursive calls.
 * Time: O(n)
 * Space: O(1)
 * 
 */
public class Q2_HouseRobber {

    private int[] memo;

    public int rob(int[] nums) {
        int n = nums.length;
        int memo[] = new int[n+1];
        return robFrom(0, nums);
    }

    public int robFrom(int i, int[] nums) {
        // no more houses left to examine
        if (i >= nums.length) {
            return 0;
        }

        if (this.memo[i] > 0) {
            return this.memo[i];
        }

        // Recursive relation evaluation to get the optimal answer.
        int ans = Math.max(this.robFrom(i+1, nums), this.robFrom(i+2, nums) + nums[i]);

        // cache the result for future use.
        this.memo[i] = ans;
        return ans;
    }

    public int rob_dp(int[] nums) {
        int n = nums.length;

        if (n == 0) {
            return 0;
        }

        int[] maxRobberAmount = new int[n+1];

        // base cases
        maxRobberAmount[n] = 0;
        maxRobberAmount[n-1] = nums[n-1];

        for (int i = n-2; i >= 0; i--) {
            maxRobberAmount[i] = Math.max(maxRobberAmount[i+1], maxRobberAmount[i+2]+nums[i]);
        }
        return maxRobberAmount[0];
    }

    public int rob_dp_optimized(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }

        int robNext, robNextPlusOne;

        // base case initialization
        robNextPlusOne = 0;
        robNext = nums[n-1];

        for (int i = n-2; i >= 0; i--) {
            int current = Math.max(robNext, robNextPlusOne+nums[i]);
            robNextPlusOne = robNext;
            robNext = current;
        }
        return robNext;
    }
}
