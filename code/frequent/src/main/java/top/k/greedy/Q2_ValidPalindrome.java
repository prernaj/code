package top.k.greedy;

/**
 * Given a string s, return true if the s can be palindrome after deleting at most one character from it.

Input:
aba

Explanation: 
aba -> aa 

Output:
true

Input:
abca

Ouput:
true

Input:
abc

Output:
false
 */

class Q2_ValidPalindrome {
    public boolean validPalindrome(String s) {
        int n = s.length();
        for (int i = 0, j = n-1; i <= j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return isPalindrome(s, i+1, j) || isPalindrome(s, i, j-1);
            }
        }
        return true;
    }
    
    public boolean isPalindrome(String s, int start, int end) {
        for (int i = start, j = end; i <= j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}

/** 
Two Pointer.

Have a start and end index. Compare the elements. 
If not equal, check skipping ith character once and continue, or skipping the jth character and continue. 
For each next check, if not equal, return false. Else return true.

COMPLEXITY:
Time: O(n)
Space: O(1)
*/
