package crash.course;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * Question
 * Given a string s, find the length of the longest substring without repeating characters.
 */
/**
 * Input Output
 * s = "abcabcbb" => 3
 * s = "bbbbb" => 1
 * s = "pwwkew" => 3
 * s = "" => 0
 */
/**
 * Thought process
 * sliding window.
 * move the right pointer until the characters are non-repeating.
 * move the left pointer when the characters are repeating until they stop repeating.
 * keep track of max length of the window.
 * return max length.
 * Time: O(n)
 * Space: O(1)
 */
/**
 * Improvements
 * Brute force.
 * check for uniqueness in all substrings.
 * Time:O(n^3)
 * 
 * Sliding window.
 * By using HashSet as a sliding window, checking if a character in the current can be done in O(1).
 * We use HashSet to store the characters in current window [i, j) (j=i initially).
 * Then we slide the index j to the right.
 * If it is not in the HashSet, we slide j further.
 * Doing so until s[j] is already in the HashSet. 
 * At this point, we found the maximum size of substrings without duplicate characters start with index i.
 * If we do this for all i, we get our answer.
 * 
 * Time: O(2*n)
 * Space: O(min(m,n)) 
 * 
 * Sliding window optimized.
 * Instead of using a set to tell if a character exists or not, we could define a mapping of the characters to its index. 
 * Then we can skip the characters immediately when we found a repeated character.
 * The reason is that if s[j]s[j] have a duplicate in the range [i,j) with index j', we don't need to increase i little by little. 
 * We can skip all the elements in the range [i, j'] and let i to be j' + 1 directly.
 * 
 * If we know that the charset is rather small, we can replace the Map with an integer array as direct access table.
 * Commonly used tables are:
 * int[26] for Letters 'a' - 'z' or 'A' - 'Z'
 * int[128] for ASCII
 * int[256] for Extended ASCII
 * 
 * Time: O(n)
 * Space: HashMap : O(min(m,n))
 * Space: Table: O(m)
 */
public class Q48_LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();

        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (checkRepetition(s, i, j)) {
                    res = Math.max(res, j - i + 1);
                }
            }
        }

        return res;
    }

    private boolean checkRepetition(String s, int start, int end) {
        int[] chars = new int[128];

        for (int i = start; i <= end; i++) {
            char c = s.charAt(i);
            chars[c]++;
            if (chars[c] > 1) {
                return false;
            }
        }

        return true;
    }

    public int lengthOfLongestSubstringSlidingWindow(String s) {
        int[] chars = new int[128];

        int left = 0;
        int right = 0;

        int res = 0;
        while (right < s.length()) {
            char r = s.charAt(right);
            chars[r]++;

            while (chars[r] > 1) {
                char l = s.charAt(left);
                chars[l]--;
                left++;
            }

            res = Math.max(res, right - left + 1);

            right++;
        }
        return res;
    }

    public int lengthOfLongestSubstringOptimized(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

    public int lengthOfLongestSubstringOptimized2(String s) {
        Integer[] chars = new Integer[128];

        int left = 0;
        int right = 0;

        int res = 0;
        while (right < s.length()) {
            char r = s.charAt(right);

            Integer index = chars[r];
            if (index != null && index >= left && index < right) {
                left = index + 1;
            }

            res = Math.max(res, right - left + 1);

            chars[r] = right;
            right++;
        }

        return res;
    }
}
