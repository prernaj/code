package crash.course;

/**
 * https://leetcode.com/problems/path-sum/
 * Question
 * Given the root of a binary tree and an integer targetSum, 
 * return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
 * A leaf is a node with no children.
 */
/**
 * Thought process
 * DFS. keep track of the sum.
 * whenever at leaf check if sum == target. if yes return true.
 * else return false.
 * Time: O(n)
 * Space: O(n) worst case: skew tree
 */
/**
 * 
 */
public class Q25_PathSum {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        return hasPathSumRecur(root, targetSum, 0);
    }

    public boolean hasPathSumRecur(TreeNode root, int targetSum, int sum) {
        if (root != null) {
            if (root.left == null && root.right == null) {
                if (sum == targetSum) {
                    return true;
                }
            }
            return hasPathSumRecur(root.left, targetSum, sum+root.val) || hasPathSumRecur(root.right, targetSum, sum+root.val);
        }
        return false;
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