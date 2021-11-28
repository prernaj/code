package top.k.recursion;

/**
 * Question
 * Implement pow(x, n), which calculates x raised to the power n (i.e., x^n).
 */

/**
 * Input Output
 * x = 2.0 and n = 10 => 1024.0
 * x = 2.1 and n = 3 => 9.261
 * x = 2.0 and n = -2 => 0.25
 */

/**
 * Thought process
 * Brute Force.
 * Multiply x n times.
 * if n < 0, substitute x,n with 1/x and -n to make sure n >= 0.
 * we need to take care of the corner cases, especially different range limits for negative and positive integers.
 * 
 * Time: O(n)
 * Space: O(1)
 * 
 * Fast Power Algorithm Recursion.
 * x^n = x^(n/2)*x^(n/2) if n is even
 * x^n = x^(n/2)*x^(n/2)*x if n is odd
 * 
 * Time: O(logn)
 * Space: O(logn)
 * 
 * Recursion with Tabulation.
 * Time: O(logn)
 * Space: O(1)
 */
public class Q7_PowFunction {
    public double myPow(double x, int n) {
        return pow(x, n);
    }
    
    public double pow(double x, int p) {
        if (p == 1) {
            return x;
        }
        if (p == 0) {
            return 1;
        }
        Boolean isNegative = p > 0 ? false : true;
        if (isNegative) {
            p = Math.abs(p);
            if (p == Integer.MIN_VALUE) {
                p = Integer.MAX_VALUE;
                if (x < 0) {
                    x = Math.abs(x);
                }
            }
        }
        double output;
        if (p % 2 == 0) {
            double res = pow(x,p/2);
            output = res*res;
        } else {
            double res = pow(x,(p-1)/2);
            output = res*res*x;
        }
        
        return isNegative ? 1/output : output;
    }
}
