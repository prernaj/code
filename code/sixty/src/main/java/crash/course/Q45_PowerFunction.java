package crash.course;

/**
 * https://leetcode.com/problems/powx-n/
 * Question
 * Implement pow(x, n), which calculates x raised to the power n (i.e., x^n).
 */
/**
 * Input Output
 * x = 2.00000, n = 10 => 1024.00000
 * x = 2.10000, n = 3 => 9.26100
 * x = 2.00000, n = -2 => 0.25000
 */
/**
 * Thought process
 * brute force.
 * multiply x n times.
 * Time: O(n)
 * 
 * divide and conquer with memoization.
 * x^n = x^(n/2) * x^(n/2) if n is even
 * x^n = x^(n/2) * x^(n/2) * x if n is odd
 * 
 * Time:O(logn)
 * Space: O(logn)
 * 
 * 
 * 
 */
public class Q45_PowerFunction {
    
}
