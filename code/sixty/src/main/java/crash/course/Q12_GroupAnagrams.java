package crash.course;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/group-anagrams/
 * Question
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 */
/**
 * Input Output
 * strs = ["eat","tea","tan","ate","nat","bat"] => [["bat"],["nat","tan"],["ate","eat","tea"]]
 * strs = [""] => [[""]]
 * strs = ["a"] => [["a"]]
 */
/**
 * Thought process
 * sort each string and store the mapping of the sorted value with the list of original strings.
 * Time: O(n*mlen(m)) where m is the max_len(str)
 * Space: O(n*m)
 */
/**
 * Improvement
 * Two strings are anagrams if and only if their character counts (respective number of occurrences of each character) are the same.
 * Time: O(nm) where m is the max_len(str)
 * Space: O(nm)
 */
public class Q12_GroupAnagrams {
    public List<List<String>> groupAnagramsSortMap(String[] strs) {
        if (strs.length == 0) return new ArrayList();
        Map<String, List> ans = new HashMap<String, List>();
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String key = String.valueOf(ca);
            if (!ans.containsKey(key)) ans.put(key, new ArrayList());
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }

    public List<List<String>> groupAnagramsCountMap(String[] strs) {
        if (strs.length == 0) return new ArrayList();
        Map<String, List> ans = new HashMap<String, List>();
        int[] count = new int[26];
        for (String s : strs) {
            Arrays.fill(count, 0);
            for (char c : s.toCharArray()) count[c - 'a']++;

            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString();
            if (!ans.containsKey(key)) ans.put(key, new ArrayList());
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }
}
