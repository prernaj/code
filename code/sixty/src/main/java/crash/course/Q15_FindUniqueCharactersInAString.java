package crash.course;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/first-unique-character-in-a-string/
 * Question
 * Given a string s, find the first non-repeating character in it and return its index. 
 * If it does not exist, return -1.
 */
/**
 * s = "leetcode" => 0
 * s = "loveleetcode" => 2
 * s = "aabb" => -1
 */
/**
 * Thought process
 * store the alphabets in a count map.
 * re-traverse the string. return the index for the first element of the non-repeating index.
 * Time: O(n)
 * Space: (n)
 */
public class Q15_FindUniqueCharactersInAString {
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> count = new HashMap<Character, Integer>();
        int n = s.length();
        // build hash map : character and how often it appears
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            count.put(c, count.getOrDefault(c, 0) + 1);
        }
        
        // find the index
        for (int i = 0; i < n; i++) {
            if (count.get(s.charAt(i)) == 1) 
                return i;
        }
        return -1;
    }
}
