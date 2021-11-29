package top.k.array;
/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
 */

 /**
  * [0,1,0,2,1,0,1,3,2,1,2,1] => 6
  * [4,2,0,3,2,5] => 9 
  */

  /**
   * Brute Force:
   * For each element in the array, we find the maaximum water it can trap, which is equal to the minimum of maximum heights of bars on both the sides minus its own hieght. 
   * O(n^2)
   * 
   * Dynamic Programming:
   * in brute force, we iterate over the left and right parts again and again just to find the highest bar size upto that index.
   * But this could be stored.
   * 1. find max height upto the given point from left end.
   * 2. find max height upto the given point from right end.
   * 3. add min(left_max[i], right_max[i])-height[i] to answer
   */
public class Q3_TrappingRainWater {
    public int getTrappingRainWater(int[] height) {
        int n = height.length;
        int[] leftMax = new int[n+1];
        int[] rightMax = new int[n+1];
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i-1], height[i]);
        }
        rightMax[n] = height[n-1];
        for (int i = n; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i+1], height[i]);
        }
        int ans = 0;
        for (int i = 0; i <= n; i++) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans;
    }
    
}
