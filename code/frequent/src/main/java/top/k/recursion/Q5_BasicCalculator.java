package top.k.recursion;

/**
 * Question
 * Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.
 */

/**
 * Input Output
 * s = "1+1" => 2
 * s = "2-1+2" => 3
 * s = "(1+(4+5+2)-3)+(6+8)" => 23
 */

 /**
  * Thought process
  * Stack.
  * - Use stack to find the value of each sub-expression within a parenthesis.
  * Algo:
  * -- push elements one by one onto the stack till a closing bracket
  * -- pop elements from stack one by one and evaluate the expression on the go, till we find ( bracket
  * Note: we are actually processing right to left.
  * Solution: rever the strng and then use above algo.
  * Time: O(n)
  * Space: O(n)
  * 
  * Converting - operator to negative numbers. 
  * Use stack and no strig reversal
  * Time: O(n)
  * Space: O(n)
  */

public class Q5_BasicCalculator {
    
}
