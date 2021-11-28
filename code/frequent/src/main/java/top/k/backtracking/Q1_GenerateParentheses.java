package top.k.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Question
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 */

/**
 * n = 3 => ["((()))","(()())","(())()","()(())","()()()"]
 * n = 1 => ["()"]
 */

/**
 * Thought process
 * 
 * Brute force.
 * Generate all possible 2^(2n) sequences of ( and ).
 * Check if each one is valid.
 * Use recursion to generate all. All sequences of length n is just ( plus all sequences of length n-1 and then ) plus all sequences of length n-1.
 * To check whether a sequence is valid or not, we keep track of balance, the net number of opening minus closing brackets.
 * If it falls under zero at any time, or doesn't end in zero - the sequence is invalid - otherwise it is valid.
 * Time: O(2^(2n).n)
 * Space: O(2^(2n).n)
 * 
 * Backtracking.
 * Instead of adding ( or ) everytime, let's add them only when we know it will remain a valid sequence.
 * We can start an opening bracket if we still have one (of n) left to place.
 * And we can start a closing bracket if it would not exceed the number of opening brackets.
 * Time: O(4^n/sqrt(n)). each valid sequence has at most n steps during the backtracking procedure.
 * Space: O(4^n/sqrt(n)). 
 * nth catalan number
 */
public class Q1_GenerateParentheses {
    public List<String> generateParentheses(int n) {
        List<String> combinations = new ArrayList<>();
        generateAll(new char[2*n], 0, combinations);
        return combinations;
    }

    public void generateAll(char[] current, int pos, List<String> result) {
        if (pos == current.length) {
            if (valid(current)) {
                result.add(new String(current));
            } else {
                current[pos] = '(';
                generateAll(current, pos+1, result);
                current[pos] = ')';
                generateAll(current, pos+1, result);
            }
        }
    }

    public boolean valid(char[] current) {
        int balance = 0;
        for (char ch: current) {
            if (c == '(') balance++;
            else balance--;
            if (balance < 0) {
                return false;
            }
        }
        return (balance == 0);
    }

    public List<String> generateParanthesesBacktracking(int n) {
        List<String> ans = new ArrayList<>();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, StringBuilder curr, int open, int close, int max) {
        if (curr.length() == 2*max) {
            ans.add(curr.toString());
            return;
        }
        if (open < max) {
            curr.append("(");
            backtrack(ans, curr, open+1, close, max);
            curr.deleteCharAt(curr.length()-1);
        }
        if (close < max) {
            curr.append(")");
            backtrack(ans, curr, open, close+1, max);
            curr.deleteCharAt(curr.length()-1);
        }

    }
}
