package crash.course;

/**
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 * Question
 * Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced binary search tree.
 * A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs by more than one.
 */
/**
 * Input Output
 * nums = [-10,-3,0,5,9] => root at 0
 * nums = [1,3] => root at 1 or 3
 */
/**
 * Thought process
 * add middle element as root.
 * left as left subtree and right as right subtree recursively.
 * base case: if only one node, create a new node and send.
 * if no element, return null.
 * 
 * Time: O(n)
 * Space: O(logN). Recursive stack requires O(logN) space.
 */
public class Q24_ConvertSortedArrayToBST {
    public TreeNode sortedArrayToBST(int[] nums) {
        return buildTree(nums, 0, nums.length-1);
    }

    public TreeNode buildTree(int[] nums, int left, int right) {
        if (left <= right) {
            int mid = (left+right)/2;
            TreeNode newNode = new TreeNode(nums[mid]);
            newNode.left = buildTree(nums, left, mid-1);
            newNode.right = buildTree(nums, mid+1, right);
            return newNode;
        }
        return null;
    }
}

class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode() {}
         TreeNode(int val) { this.val = val; }
         TreeNode(int val, TreeNode left, TreeNode right) {
             this.val = val;
             this.left = left;
             this.right = right;
         }
}
