package crash.course;

import java.util.Arrays;
import java.util.HashSet;

/**
 * https://leetcode.com/problems/intersection-of-two-arrays/
 * Question
 * Given two integer arrays nums1 and nums2, return an array of their intersection. 
 * Each element in the result must be unique and you may return the result in any order.
 */
/**
 * Input Output
 * nums1 = [1,2,2,1], nums2 = [2,2] => [2]
 * nums1 = [4,9,5], nums2 = [9,4,9,8,4] => [9,4]
 */
/**
 * Thought process
 * check for each element of nums1 if it is present in nums2.
 * check for each element of nums2 if it is present in nums1.
 * Time: O(n1*n2)
 * Space: O(len(intersection(n1, n2)))
 */
/**
 * Improvement
 * To solve the problem in linear time, let's use the structure set, which provides in/contains operation in O(1) time in average case.
 * The idea is to convert both arrays into sets, and then iterate over the smallest set checking the presence of each element in the larger set.
 * Time O(n+m) in the average case.
 * Space: O(m+n)
 */ 
public class Q13_IntersectionOfTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<Integer>();
        for (Integer n : nums1) set1.add(n);
        HashSet<Integer> set2 = new HashSet<Integer>();
        for (Integer n : nums2) set2.add(n);
    
        if (set1.size() < set2.size()) return set_intersection(set1, set2);
        else return set_intersection(set2, set1);
    }

    public int[] set_intersection(HashSet<Integer> set1, HashSet<Integer> set2) {
        int [] output = new int[set1.size()];
        int idx = 0;
        for (Integer s : set1)
          if (set2.contains(s)) output[idx++] = s;
    
        return Arrays.copyOf(output, idx);
      }
    
}
