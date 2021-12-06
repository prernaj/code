package crash.course;

/**
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 * Question
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,2,4,5,6,7] might become:
 * [4,5,6,7,0,1,2] if it was rotated 4 times.
 * [0,1,2,4,5,6,7] if it was rotated 7 times.
 * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 * Given the sorted rotated array nums of unique elements, return the minimum element of this array.
 * You must write an algorithm that runs in O(log n) time.
 */
/**
 * Input Output
 * nums = [3,4,5,1,2] => 1
 * nums = [4,5,6,7,0,1,2] => 0
 * nums = [11,13,15,17] => 11
 */
/**
 * Thought Process
 * binary search.
 * whenever we find the pattern greater_element then smaller_element return the smaller_element.
 * Time: O(logN)
 */
/**
 * Improvements
 * Find the inflection point:
 * - All the elements to the left of inflection point > first element of the array.
 * - All the elements to the right of inflection point < first element of the array.
 * 
 * Algorithm:
 * - find the mid element of the array
 * - if mid_element > first element of array, this means that we need to look for inflection point on the right of mid.
 * - if mid_element < first element of array, we need to look for inflection point on the left of mid.
 * - we stop our search when we find the inflection point, when either of the two conditions is satisfied:
 * -- nums[mid] > nums[mid + 1] Hence, mid+1 is the smallest.
 * -- nums[mid - 1] > nums[mid] Hence, mid is the smallest.
 * 
 * Time: O(logN)
 * Space: O(1)
 */
public class Q42_FindMinimumInRotatedSortedArray {
    public int findMin(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int left = 0, right = nums.length - 1;
        if (nums[right] > nums[0]) {
            return nums[0];
        }
        while (right >= left) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }
            if (nums[mid - 1] > nums[mid]) {
                return nums[mid];
            }
            if (nums[mid] > nums[0]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
