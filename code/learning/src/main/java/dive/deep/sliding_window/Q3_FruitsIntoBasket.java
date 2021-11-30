package dive.deep.sliding_window;

import java.util.Collections;
import java.util.HashMap;

/**
 * https://leetcode.com/problems/fruit-into-baskets/
 * Question
 * You are visiting a farm that has a single row of fruit trees arranged from left to right. 
 * The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.
 * You want to collect as much fruit as possible. 
 * However, the owner has some strict rules that you must follow:
 * - You only have two baskets, and each basket can only hold a single type of fruit. 
 * There is no limit on the amount of fruit each basket can hold.
 * - Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree) while moving to the right. 
 * The picked fruits must fit in one of your baskets.
 * -Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
 * Given the integer array fruits, return the maximum number of fruits you can pick.
 */
/**
 * Input Output
 * fruits = [1,2,1] => 3 {1,2,1}
 * fruits = [0,1,2,2] => 3 {1,2,2}
 * fruits = [1,2,3,2,2] => 4 {2,3,2,2}
 * fruits = [3,3,3,1,2,1,1,2,3,3,4] => 5 {1,2,1,1,2}
 */
/**
 * Thought process.
 * Rephrased qustion. What is the length of longest subarray that contains up to two distinct integers?
 * https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/
 * 
 */
public class Q3_FruitsIntoBasket {
    int totalFruit(int[] fruits) {		
        int n = fruits.length;
        if (n < 3) {
            return n;
        }
        int left = 0;
        int right = 0;
        HashMap<Integer, Integer> hashmap = new HashMap<Integer, Integer>();
        int max_len = 2;
        while (right < n) {
            hashmap.put(fruits[right], right++);
            if (hashmap.size() == 3) {
                int del_idx = Collections.min(hashmap.values());
                hashmap.remove(fruits[del_idx]);
                left = del_idx + 1;
            }
            max_len = Math.max(max_len, right - left);
        }
        return max_len;
        
    }
}
