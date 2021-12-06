package crash.course;

/**
 * https://leetcode.com/problems/search-insert-position/
 * Question
 * Given a sorted array of distinct integers and a target value, return the index if the target is found. 
 * If not, return the index where it would be if it were inserted in order.
 * You must write an algorithm with O(log n) runtime complexity.
 */
/**
 * Input Output
 * nums = [1,3,5,6], target = 5 => 2
 * nums = [1,3,5,6], target = 2 => 1
 * nums = [1,3,5,6], target = 7 => 4
 * nums = [1,3,5,6], target = 0 => 0
 * nums = [1], target = 0 => 0
 */
/**
 * Thought process
 * binary search in O(logn)
 * instead of returning true or false.
 * return the index where the element is found. or return the index where the search is stopped because adjacent numbers are not correct.
 * 
 * Time:O(logn)
 * Space:O(1)
 */
/**
 * Improvements
 * Usually, within binary search, we compare the target value to the middle element of the array at each iteration.
 * - If the target value is equal to the middle element, the job is done.
 * - If the target value is less than the middle element, continue to search on the left.
 * - If the target value is greater than the middle element, continue to search on the right.
 */
public class Q41_SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        return binarySearch(nums, target, 0, nums.length-1);
    }

    public int binarySearch(int[] nums, int target, int start, int end) {
        int n = nums.length;
        if (start >= 0 && start < n && end >= 0 && end < n && start < end) {
            int mid = start + (end-start)/2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > target) {
                return binarySearch(nums, target, start, mid-1);
            }
            return binarySearch(nums, target, mid+1, end);
        }
        if (end >= 0 && end < n && nums[end] < target) {
            return end+1;
        }
        
        return start >= 0 ? start : 0;
    }

    public int searchInsertImproved(int[] nums, int target) {
        int pivot, left = 0, right = nums.length - 1;
        while (left <= right) {
          pivot = left + (right - left) / 2;
          if (nums[pivot] == target) return pivot;
          if (target < nums[pivot]) right = pivot - 1;
          else left = pivot + 1;
        }
        return left;
      }
}
