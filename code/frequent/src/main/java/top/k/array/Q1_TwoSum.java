package top.k.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order.
 */

 /**
  * nums = [2,7,11,15], target = 9 Output: [0,1]
  * nums = [3,2,4], target = 6 Output: [1,2]
  * nums = [3,3], target = 6 Output: [0,1]
  */

  /**
   * Approach:
   * Add elements to a hashmap of element and list of indexes. for each element search for its target-nuber in map. if found, return indexes.
   * time: O(n) space: O(n)
   */
public class Q1_TwoSum {
    public int[] twoSum(int[] nums, int target) {
        int[] output = new int[2];
        int n = nums.length;
        HashMap<Integer, List<Integer>> indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (indexMap.containsKey(num)) {
                List<Integer> indexList = indexMap.get(num);
                indexList.add(i);
                indexMap.put(num, indexList);
            } else {
                List<Integer> indexList = new ArrayList<>();
                indexList.add(i);
                indexMap.put(num, indexList);
            }
        }
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            int newNum = target - num;
            if (indexMap.containsKey(newNum)) {
                List<Integer> indexList = indexMap.get(newNum);
                for (Integer x : indexList) {
                    if (x != i) {
                        output[0] = i;
                        output[1] = x;
                        return output;
                    }
                }
            }
        }
        return output;
    }
}

/**
 * Constraints:

2 <= nums.length <= 104
-109 <= nums[i] <= 109
-109 <= target <= 109
Only one valid answer exists.
 */
