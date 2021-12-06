package crash.course;

/**
 * https://leetcode.com/problems/house-robber-ii/
 * Question
 * You are a professional robber planning to rob houses along a street. 
 * Each house has a certain amount of money stashed. 
 * All houses at this place are arranged in a circle. 
 * That means the first house is the neighbor of the last one. 
 * Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
 */
/**
 * Input Output
 * nums = [2,3,2] => 3
 * nums = [1,2,3,1] => 4
 * nums = [1,2,3] => 3
 */
/**
 * Thought process
 * all houses are arranged in a circle. hmm !!
 * this means i can revisit the nodes back.
 */
/**
 * Improvements
 * if the thief has robbed the first house, they cannot steal the last house and vice versa. 
 * "the problem becomes to rob either House[1]-House[n-1] or House[2]-House[n]"
 * 
 * DP.
 * For example nums = [7,4,1,9,3,8,6,5]
 * Therefore, the final solution that we are looking for is to take the maximum value the thief can rob between houses of list [7,4,1,9,3,8,6] and [4,1,9,3,8,6,5].
 * For each of the lists, all we need to do is to figure the maximum value the thief can get using the approach in the original House Robber Problem.
 */
public class Q36_HouseRobberII {
    
}
