package top.k.recursion;

/**
 * Question
 * Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:
 * '.' Matches any single character.​​​​
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 */

 /**
  * Input Output
  * s = "aa", p = "a" => false
  * s = "aa", p = "a*" => true
  * s = "ab", p = ".*" => true
  * s = "aab", p = "c*a*b" => true
  * s = "mississippi", p = "mis*is*p*." => false
  */

/**
 * Thought process
 * Recursion.
 * If notmal characters, just compare left to right.
 * When a dot is prsent, consider it as equal character and proceed.
 * When a star is present, we may need to check many different suffixes of the text and see if they match the rest of the pattern. Recursively.
 * 
 * Time:
 * Let T and P be the lengths of text and pattern respectively.
 * O({i:[0..T]}{j:[0..p/2][i..i+j]}O(T+P-i-2j)
 * O((T+P)2^(T+P/2))
 * 
 * Space:
 * O(T^2 + P^2)
 * 
 * DP.
 * As the problem has optimal substructures, we should cache immediate results.
 * dp[i,j] does text[i:] and pattern[j:] match?
 * Time: O(TP). The work for every call to dp(i,j) for i = 0..T, j = 0..P is done once, and it is O(1) work.
 * 
 * Space: O(TP)
 * 
 */

public class Q6_RegularExpressionMatching {
    
    public boolean isMatch(String text, String pattern) {
        if (pattern.isEmpty()) {
            return text.isEmpty();
        }
        boolean firstMatch = pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.';
        if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
            return (isMatch(text, pattern.substring(2)) || (firstMatch && isMatch(text.substring(1), pattern)))
        } else {
            return firstMatch && isMatch(text.substring(1), pattern.substring(1));
        }
    }

    private Boolean[][] memo;

    public boolean isMatchDP(String text, String pattern) {
        memo = new Boolean[text.length()+1][pattern.length()+1];
        return recurDP(0, 0, text, pattern);
    }

    public boolean recurDP(int i, int j, String text, String pattern) {
        if (memo[i][j] != null) {
            return memo[i][j] == Boolean.TRUE;
        }
        boolean ans;
        if (j == pattern.length()) {
            ans = i == text.length();
        } else {
            boolean first_match = (i < text.length() && pattern.charAt(j) == text.charAt(i) || pattern.charAt(j) == '.');   

            if (j+1 < pattern.length() && pattern.charAt(j+1) == '*') {
                ans = (recurDP(i, j+2, text, pattern) || first_match && recurDP(i+1, j, text, pattern));
            } else {
                ans = first_match && recurDP(i+1, j+1, text, pattern);
            }
        }
        memo[i][j] = ans;
        return ans;
    }

}