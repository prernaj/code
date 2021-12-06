package crash.course;

import java.util.Stack;

/**
 * https://leetcode.com/problems/valid-parentheses/
 * Question
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * An input string is valid if:
 * - Open brackets must be closed by the same type of brackets.
 * - Open brackets must be closed in the correct order.
 */
/**
 * Input Output
 * s = "()" => true
 * s = "()[]{}" => true
 * s = "(]" => false
 * s = "([)]" => false
 * s = "{[]}" => true
 */
/**
 * Thought process
 * Stack.
 * traverse string
 * - push opening brackets to the stack. 
 * - in case of closing bracket, pop top opening bracket. and match. if not match return false
 * - return true if stack is empty
 * 
 * Time:O(n)
 * Space: O(n)
 */
/**
 * Improvements
 * Use a hashmap instead of switch case.
 */
public class Q6_ValidParentheses {
    public boolean isValidMy(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            Character ch = s.charAt(i);
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.add(ch);
            } else {
                Character counterCh = null;
                switch(ch) {
                    case ')': counterCh = '('; break;
                    case ']': counterCh = '['; break;
                    case '}': counterCh = '{'; break;
                }
                if (!stack.isEmpty()) {
                    Character stackCh = stack.peek();
                    if ( counterCh != null && stackCh == counterCh) {
                        stack.pop();
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        if (stack.isEmpty()) {
            return true;
        }
        return false;
    }
}
