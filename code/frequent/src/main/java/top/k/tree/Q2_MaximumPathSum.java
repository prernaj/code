package top.k.tree;

/**
 * Question
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. 
 * A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
 * The path sum of a path is the sum of the node's values in the path.
 * Given the root of a binary tree, return the maximum path sum of any non-empty path.
 */

/**
 * Thought Process
 * 
 * Recursion.
 * Let's simplify and implement a function max_gain(node) which takes a node as an argument and computes a maximum contribution 
 * that this node and one/zero of its subtree can add.
 * 
 * Time: O(N)
 * Space: O(H)
 */
public class Q2_MaximumPathSum {
    int max_sum;
    public int maxPathSum(TreeNode root) {
        max_sum = root.val;
        findSum(root);
        return max_sum;
    }
    
    public int findSum(TreeNode node) {
        if (node != null) {
            int leftSum = Math.max(findSum(node.left), 0);
            int rightSum = Math.max(findSum(node.right), 0);
            if (leftSum + rightSum + node.val > max_sum) {
                max_sum = leftSum + rightSum + node.val;
            }
            return Math.max(leftSum, rightSum) + node.val;
        }
        return 0;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) {
        this.val = val;
    }
}
