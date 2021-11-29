package top.k.array;

/**
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 * The overall run time complexity should be O(log (m+n)).
 */

 /**
  * nums1 = [1,3] and nums2 = [2] => 2.0
  * nums1 = [0,1] and nums2 = [0,1] => 0.0
  * nums1 = [] and nums2 = [1] => 1.0
  * nums1 = [1] and nums2 = []  => 2.0
  */

/**
 * Thought proces
 * - dividing a set into two equal subsets, that one subset is always greater than the other.
 * - let's cut A into two parts at position i to get leftA and rightA
 * - if A has m elements, there are m+1 possible cuttings. 
 * - len(leftA) = i and len(rightA) = m-i
 * - when i = 0, leftA is empty and when i = m, rightA is empty
 * - Similarly cut B into two parts at random location j.
 * - put leftA and leftB into left_part, and rightA and rightB in right_part.
 * - if len(left_part) == len(right_part) and max(left_part) <= min(right_part)
 * - Then median = (max(left_part) + min(right_part))/2.
 * 
 * To ensure these conditions, we just need to ensure:
 * (1) i + j == m - i + n - j (or: m - i + n - j + 1)
    if n >= m, we just need to set: i = 0 ~ m, j = (m + n + 1)/2 - i
 * (2) B[j-1] <= A[i] and A[i-1] <= B[j]
 * ps.1 For simplicity, I presume A[i-1],B[j-1],A[i],B[j] are always valid even if i=0/i=m/j=0/j=n . I will talk about how to deal with these edge values at last.
 * ps.2 Why n >= m? Because I have to make sure j is non-nagative since 0 <= i <= m and j = (m + n + 1)/2 - i. If n < m , then j may be nagative, that will lead to wrong result.
 * So, all we need to do is:
 * Searching i in [0, m], to find an object `i` that:
 * B[j-1] <= A[i] and A[i-1] <= B[j], ( where j = (m + n + 1)/2 - i )
 * 
 * Binary Search. algo.
 * 1. set imin = 0 and imax = m
 * 2. i = (imin+imax)/2, j = (m+n+1)/2-1
 * -- B[j-1] <= A[i] and A[i-1] <= B[j]
 * ---- found object i. stop searching.
 * -- B[j-1] > A[i]
 * ---- a[i] is too small. increase i. adjust search to [i+1, imax]. set imin = i+1
 * -- A[i-1] > B[j]
 * ---- a[i-1] is too big. decrease i. adjust search to [imin, i-1]. set imax = i-1
 * 
 * 3. When i is found. median is
 * max(A[i-1], B[j-1]) (when m + n is odd)
 * or (max(A[i-1], B[j-1]) + min(A[i], B[j]))/2 (when m + n is even)
 * 
 */

public class Q5_MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m > n) { // assumption m should be less than n
            // reverse nums1, nums2,m,n
        }
        int imin = 0;
        int imax = m;
        int half_len = (m+n+1)/2;

        while (imin <= imax) {
            int i = (imin + imax)/2;
            int j = half_len - 1;
            if (i < m && nums2[j-1] > nums1[i]) {
                // i is too small, must increase it
                imin = i+1;
            } else if (i > 0 && nums1[i-1] > nums2[j]) {
                // i is too big, must decrease it
                imax = i-1;
            } else {
                int max_of_left, min_of_right;
                if (i == 0) {
                    max_of_left = nums2[j-1];
                } else if (j == 0) {
                    max_of_left = nums1[i-1];
                } else {
                    max_of_left = Math.max(nums1[i-1], nums2[j-1]);
                }

                if ((m+n)%2 == 1) {
                    return max_of_left;
                }

                if (i == m) {
                    min_of_right = nums2[j];
                } else if (j == n) {
                    min_of_right = nums1[i];
                } else {
                    min_of_right = Math.min(nums1[i], nums2[j]);
                }
                return (max_of_left+min_of_right)/2.0;
            }
        }
        return -1;
    }
}
