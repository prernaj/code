package dive.deep.sliding_window;
/**
 * https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/
 * Question
 * Given a string s consisting only of characters a, b and c.
 * Return the number of substrings containing at least one occurrence of all these characters a, b and c.
 */
/**
 * Input Output
 * s = "abcabc" => 10 {"abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc"}
 * s = "aaacb" => 3 {"aaacb", "aacb" and "acb"}
 * s = "abc" => 1 {"abc"}
 */
/**
 * Thought process
 * for each position we need to find the first occurance of a/b/c on or after this position.
 * we can precompute the three linklists of each a,b and c.
 * 1. start with moving one step at a time.
 * 2. once we reach a point where we get a string where we get all the three characters.
 * 
 * last will record the position of last occurrence.
 * If the ending index of substring is i, the starting position should be on the left of min(last), in order to have all 3 different letters. 
 * And in this case, the starting index can be in range [0, min(last)], min(last) + 1 in total.
 */
public class Q2_NumberOfSubstringsContainingAllCharacters {
    public int numOfSubstrings(String s) {
        int count[] = {0, 0, 0};
        int res = 0;
        int left = 0;
        int n = s.length();
        for (int right = 0; right < n; ++right) {
            ++count[s.charAt(right) - 'a'];
            while (count[0] > 0 && count[1] > 0 && count[2] > 0) {
                --count[s.charAt(left++) - 'a'];
            }
            res += left;
        }
        return res;
    }

    public int numberOfSubstringsSecond(String s) {
        int last[] = {-1, -1, -1};
        int res = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            last[s.charAt(i) - 'a'] = i;
            res += 1 + Math.min(last[0], Math.min(last[1], last[2]));
        }
        return res;
    }
}
