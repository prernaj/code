package dive.deep.sliding_window;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
 * Question
 * Given a string s and an integer k, return the length of the longest substring of s that contains at most k distinct characters.
 */

/**
 * Input Output
 * s = "eceba", k = 2 => 3
 * s = "aa", k = 1 => 2
 */

/**
 * Thought process
 * Sliding Window + Hashmap.
 * The idea is to set both pointers in the position 0 and then move right pointer to the right 
 * while the window contains not more than k distinct characters. 
 * If at some point we've got k + 1 distinct characters, let's move left pointer to keep not more than k + 1 distinct characters in the window.
 * how to move the left pointer to keep only k distinct characters in the string?
 * Let's use for this purpose hashmap containing all characters in the sliding window as keys and their rightmost positions as values. 
 * At each moment, this hashmap could contain not more than k + 1 elements.
 * 
 * Algo:
 * - Return 0 if the string is empty or k is equal to zero.
 * - Set both set pointers in the beginning of the string left = 0 and right = 0 and init max substring length max_len = 1.
 * - While right pointer is less than N:
 * --Add the current character s[right] in the hashmap and move right pointer to the right.
 * --If hashmap contains k + 1 distinct characters, remove the leftmost character from the hashmap 
 * and move the left pointer so that sliding window contains again k distinct characters only.
 * --Update max_len.
 * 
 * Time: For the worst case when the input string contains n distinct characters, the answer is no. 
 * In that case at each step one uses O(k) time to find a minimum value in the hashmap with k elements 
 * and so the overall time complexity is O(Nk).
 * Space: O(k)
 * 
 * Sliding Window + Ordered Dictionary
 * 
 * How to achieve \mathcal{O}(N)O(N) time complexity
 * To have \mathcal{O}(N)O(N) algorithm performance, one would need a structure, which provides four operations in \mathcal{O}(1)O(1) time :
 * Insert the key
 * Get the key and check if the key exists
 * Delete the key
 * Return the first or last added key/ value
 * The first three operations in O(1) time are provided by the standard hashmap, and the forth one - by linked list.
 * 
 * There is a structure called ordered dictionary, it combines behind both hashmap and linked list. 
 * In Python this structure is called OrderedDict and in Java LinkedHashMap.
 * 
 * Algorithm:
 * Let's use ordered dictionary instead of standard hashmap to trim the algorithm from approach 1 :
 * - Return 0 if the string is empty or k is equal to zero.
 * - Set both pointers to the beginning of the string left = 0 and right = 0 and initialize max substring length max_len = 1.
 * - While right pointer is less than N:
 * If the current character s[right] is already in the ordered dictionary hashmap -- delete it, to ensure that the first key in hashmap is the leftmost character.
 * Add the current character s[right] in the ordered dictionary and move right pointer to the right.
 * If ordered dictionary hashmap contains k + 1 distinct characters, remove the leftmost one and move the left pointer so that sliding window contains again k distinct characters only.
 * Update max_len.
 * 
 * Time complexity : O(N) since all operations with ordered dictionary : insert/get/delete/popitem (put/containsKey/remove) are done in a constant time.
 * Space complexity : O(k) since additional space is used only for an ordered dictionary with at most k + 1 elements.
 * 
 */
public class Q5_LongestSubstringWithAtMostKDistinctCharacters {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int n = s.length();
        if (n * k == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;

        Map<Character, Integer> rightmostPosition = new HashMap<>();

        int maxLength = 1;

        while (right < n) {
            rightmostPosition.put(s.charAt(right), right++);

            if (rightmostPosition.size() == k + 1) {
                int lowestIndex = Collections.min(rightmostPosition.values());
                rightmostPosition.remove(s.charAt(lowestIndex));
                left = lowestIndex + 1;
            }

            maxLength = Math.max(maxLength, right - left);
        }
        return maxLength;
    }

    public int lengthOfLongestSubstringKDistinctOptimized(String s, int k) {
        int n = s.length();
        if (n * k == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;

        Map<Character, Integer> rightmostPosition = new LinkedHashMap<>();

        int maxLength = 1;

        while (right < n) {
            Character character = s.charAt(right);
            if (rightmostPosition.containsKey(character)) {
                rightmostPosition.remove(character);
            }
            rightmostPosition.put(character, right++);

            if (rightmostPosition.size() == k + 1) {
                Map.Entry<Character, Integer> leftmost = rightmostPosition.entrySet().iterator().next();
                rightmostPosition.remove(leftmost.getKey());
                left = leftmost.getValue() + 1;
            }

            maxLength = Math.max(maxLength, right - left);
        }
        return maxLength;
    }
}
