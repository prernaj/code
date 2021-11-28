package top.k.backtracking;

/**
 * Question
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
 * 
 * Constraints: 0 <= digits.length <= 4 and digits[i] is a digit in the range ['2', '9'].
 */

/**
 * Input and output
 * digits = "23" => ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * digits = "" => []
 * digits = "2" => ["a","b","c"]
 */

/**
 * Thought process
 * Looking at the constraints. since length is small. brute force should work.
 * Brute force.
 * generate all combinations of letters.
 * backtracking.
 * Go digit by digit. we need to lock-in letters when we generate new letters.
 * Recursion.
 * - if input is empty, return an empty array
 * - create a map of digit to letters
 * - use backtracking to generate all combinations.
 * 
 * Time: O((4^N).N) N = length of digits, 4 here is the maximum length of hashmap value
 * Space: O(N)
 * 
 * 
 */
public class Q2_LetterCombinationsOfPhoneNumber {
    private List<String> combinations = new ArrayList<>();
    private Map<Character, String> letters = Map.of(
        '2', "abc", '3', "def", '4', "ghi", '5', "jkl", 
        '6', "mno", '7', "pqrs", '8', "tuv", '9', "wxyz");
    private String phoneDigits;

    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return combinations;
        }
        phoneDigits = digits;
        backtrack(0, new StringBuilder());
        return combinations;
    }

    private void backtrack(int index, StringBuilder path) {
        if (path.length() == phoneDigits.length()) {
            combinations.add(path.toString());
            return; // Backtrack
        }

        String possibleLetters = letters.get(phoneDigits.charAt(index));
        for (char letter: possibleLetters.toCharArray()) {
            // Add the letter to our current path
            path.append(letter);
            // Move on to the next digit
            backtrack(index + 1, path);
            // Backtrack by removing the letter before moving onto the next
            path.deleteCharAt(path.length() - 1);
        }
    }
}
