package dive.deep.sliding_window;

import java.util.Collections;
import java.util.HashMap;

/**
 * https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/submissions/
 * Question
 * Given a string s, return the length of the longest substring that contains at most two distinct characters.
 */

/**
 * s = "eceba" => 3 {e,c,e}
 * s = "ccaabbb" => 2 {aa,bbb}
 */
/**
 * Thought process
 * to solve the problem in one pass, use sliding window with two set pointers left and right serving as window boundaries.
 * The idea is to set both pointers in the position 0 and then move right pointer to the right 
 * while the window contains not more than two distinct characters. 
 * If at some point we've got 3 distinct characters, let's move left pointer to keep not more than 2 distinct characters in the window.
 * how to move the left pointer to keep only 2 distinct characters in the string?
 * use a hashmap containing all characters in the sliding window as keys and their rightmost positions as values. 
 * For example, using this hashmap one knows that the rightmost position of character e in "eeeeeeeet" window is 8 
 * and so one has to move left pointer in the position 8 + 1 = 9 to exclude the character e from the sliding window.
 * 
 * Time: O(n)
 * Space: O(1). Hashmap of at most 3 characters.
 * 
 * Algo:
 * - Return N if the string length N is smaller than 3.
 * - Set both set pointers in the beginning of the string left = 0 and right = 0 and init max substring length max_len = 2.
 * - While right pointer is less than N:
 * If hashmap contains less than 3 distinct characters, add the current character s[right] in the hashmap and move right pointer to the right.
 * If hashmap contains 3 distinct characters, remove the leftmost character from the hashmap and move the left pointer so that sliding window contains again 2 distinct characters only.
 * Update max_len.
 */
public class Q4_LongestSubstringWithAtMostTwoDistinctCharacters {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int n = s.length();
        if (n < 3) {
            return n;
        }
        int left = 0;
        int right = 0;
        HashMap<Character, Integer> hashmap = new HashMap<Character, Integer>();
        int max_len = 2;
        while (right < n) {
            hashmap.put(s.charAt(right), right++);
            if (hashmap.size() == 3) {
                int del_idx = Collections.min(hashmap.values());
                hashmap.remove(s.charAt(del_idx));
                left = del_idx + 1;
            }
            max_len = Math.max(max_len, right - left);
        }
        return max_len;
    }
}
