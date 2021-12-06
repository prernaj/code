package crash.course;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 * Question
 * There is an integer array nums sorted in ascending order (with distinct values).
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) 
 * such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). 
 * For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
 * You must write an algorithm with O(log n) runtime complexity.
 */
/**
 * Input Output
 * nums = [4,5,6,7,0,1,2], target = 0 => 4
 * nums = [4,5,6,7,0,1,2], target = 3 => -1
 * nums = [1], target = 0 => -1
 */
/**
 * Thought process
 * binary search.
 * find intersection point. O(logn)
 * search for the element in the left part of intersection or the right part. O(logn)
 * works for basic cases. but does not work for sorted array
 * [1,3] target = 3 => 1 . mine returns -1;
 */
/**
 * Improvements
 * The algorithm is quite straightforward :
 * - Find a rotation index rotation_index, i.e. index of the smallest element in the array. Binary search works just perfect here.
 * - rotation_index splits array in two parts. Compare nums[0] and target to identify in which part one has to look for target.
 * - Perform a binary search in the chosen part of the array.
 * 
 * 
 * One pass binary search.
 * Algorithm:
 * Initiate the pointer start to 0, and the pointer end to n - 1.
 * Perform standard binary search. While start <= end:
 * - Take an index in the middle mid as a pivot.
 * - If nums[mid] == target, the job is done, return mid.
 * - Now there could be two situations:
 * -- Pivot element is larger than the first element in the array, i.e. the subarray from the first element to the pivot is non-rotated, as shown in the following graph.
 * - If the target is located in the non-rotated subarray:
 *  go left: `end = mid - 1`.
 * - Otherwise: go right: `start = mid + 1`.
 * Pivot element is smaller than the first element of the array, i.e. the rotation index is somewhere between 0 and mid. 
 * It implies that the sub-array from the pivot element to the last one is non-rotated, as shown in the following graph.
 * - If the target is located in the non-rotated subarray:
 * go right: `start = mid + 1`.
 * Otherwise: go left: `end = mid - 1`.
 * We're here because the target is not found. Return -1.
 * 
 * Time:O(logN)
 * Space:O(1)
 */
public class Q43_SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        } 
        
        int intersection_index = findIntersectionIndex(nums);
        System.out.println(intersection_index);
        if (intersection_index == -1) {
            return -1;
        }
        int start = 0;
        int end = nums.length-1;
        if (nums[intersection_index] == target) {
            return intersection_index;
        }
        if (nums[intersection_index] > target) {
            start = intersection_index+1;
        } else {
            end = intersection_index-1;
        }
        return binarySearch(nums, target, start, end);
    }

    public int findIntersectionIndex(int[] nums) {
        int left = 0;
        int n = nums.length;
        int right = n-1;
        while (left < right) {
            int mid = left + (right-left)/2;
            if (mid+1 >= 0 && mid+1 < n && nums[mid] > nums[mid + 1]) {
                return mid + 1;
            }
            if (mid-1 >= 0 && mid-1 < n && nums[mid-1] > nums[mid]) {
                return mid;
            }
            if (nums[mid] > nums[0]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            } 
        }
        return left;
    }

    public int binarySearch(int[] nums, int target, int start, int end) {
        if (start < end) {
            int mid = start + (end-start)/2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                return binarySearch(nums, target, start, mid-1);
            }
            return binarySearch(nums, target, mid+1, end);
        }
        return -1;
    }

    public int find_rotate_index(int[] nums, int left, int right) {
        if (nums[left] < nums[right])
          return 0;
    
        while (left <= right) {
          int pivot = (left + right) / 2;
          if (nums[pivot] > nums[pivot + 1])
            return pivot + 1;
          else {
            if (nums[pivot] < nums[left])
              right = pivot - 1;
            else
              left = pivot + 1;
          }
        }
        return 0;
      }

      public int binarySearchImproved(int[] nums, int target, int left, int right) {
        /*
        Binary search
        */
        while (left <= right) {
          int pivot = (left + right) / 2;
          if (nums[pivot] == target)
            return pivot;
          else {
            if (target < nums[pivot])
              right = pivot - 1;
            else
              left = pivot + 1;
          }
        }
        return -1;
      }

      public int searchImproved(int[] nums, int target) {
        int n = nums.length;
    
        if (n == 1)
          return nums[0] == target ? 0 : -1;
    
        int rotate_index = find_rotate_index(nums, 0, n - 1);
    
        // if target is the smallest element
        if (nums[rotate_index] == target)
          return rotate_index;
        // if array is not rotated, search in the entire array
        if (rotate_index == 0)
          return binarySearchImproved(nums, target, 0, n - 1);
        if (target < nums[0])
          // search in the right side
          return binarySearchImproved(nums, target, rotate_index, n - 1);
        // search in the left side
        return binarySearchImproved(nums, target, 0, rotate_index);
      }

      public int searchOnePass(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while (start <= end) {
          int mid = start + (end - start) / 2;
          if (nums[mid] == target) return mid;
          else if (nums[mid] >= nums[start]) {
            if (target >= nums[start] && target < nums[mid]) end = mid - 1;
            else start = mid + 1;
          }
          else {
            if (target <= nums[end] && target > nums[mid]) start = mid + 1;
            else end = mid - 1;
          }
        }
        return -1;
      }
}
