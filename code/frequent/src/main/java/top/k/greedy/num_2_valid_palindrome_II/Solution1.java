package top.k.greedy.num_2_valid_palindrome_II;

class Solution1 {
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
