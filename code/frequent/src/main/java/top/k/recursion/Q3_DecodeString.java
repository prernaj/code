package top.k.recursion;

/**
 * Question
 * Given an encoded string, return its decoded string.
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. k is a positive integer. valid string.
 */

/**
 * Input Output
 * s = "3[a]2[bc]" => "aaabcbc"
 * s = "3[a2[c]]" => "accaccacc"
 * s = "2[abc]3[cd]ef" => "abcabccdcdcdef"
 * s = "abc3[cd]xyz" => "abccdcdcdxyz"
 */

/**
 * Thought process.
 * Stack.
 * s = 3[a2[bc]]
 * - start traversing s
 * - push 3
 * - push [
 * - push a
 * - push 2
 * - push [
 * - push b
 * - push c
 * - on [, pop and add to decoded string until stack pop [ => decodedString = cb
 * - pop and add to k until stack top is a digit => 2
 * - compute K* decdedstring => cbcb
 * - push computed string back to stack in reverse order {3, [, a, b, c, b, c}
 * - on [, decoded_string = cbcba k = 3
 * - cbcbacbcbacbcba
 * Result = abcbcabcbcabcbc
 * 
 * Time: O(maxK^countK.n) // maxK is the maximum value of k and countK is the count of nested k values and n is the maximum length of encoded string.
 * For example, s = 20[a10[bc]], maxK = 20, countK = 2 (as there are two nested values 20 and 10). 
 * Also, there are 2 encoded strings a and bc with maximum length of encoded string, n as 2
 * 
 * Space: O(sum(maxK^countK.n))
 * 
 * Two stacks. countStack.string stack.
 * i = 1, 3[a2[bc]] {3} {""}
 * i = 4, {3,2} {"", a}
 * 
 */
public class Q3_DecodeString {
    
}
