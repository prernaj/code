package crash.course;

/**
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
 * Question
 * Given the root of a binary tree, return its maximum depth.
 * A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 */
/**
 * Thought process
 * inorder traversal. keep track of counter plus one as we go down a level
 * each time we reach a leaf node. update the max_level
 * 
 * Time: O(n) sice we visit each node
 * Space: O(N)
 */
/**
 * Tail Recursion + BFS
 * One might have noticed that the above recursion solution is probably not the most optimal one in terms of the space complexity, 
 * and in the extreme case the overhead of call stack might even lead to stack overflow.
 * To address the issue, one can tweak the solution a bit to make it tail recursion, 
 * which is a specific form of recursion where the recursive call is the last action in the function. 
 *
 * The benefit of having tail recursion, is that for certain programming languages (e.g. C++) 
 * the compiler could optimize the memory allocation of call stack by reusing the same space for every recursive call, 
 * rather than creating the space for each one. 
 * As a result, one could obtain the constant space complexity O(1) for the overhead of the recursive calls.
 * 
 * Did not quiet understand this tail recursion for now.
 * 
 * Also, we can do this iteratively using a stack.
 */
public class Q21_MaximumDepthOfBinaryTree {
    private int max_depth = Integer.MIN_VALUE;

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        traversal(root, 1);
        return max_depth;
    }

    public void traversal(TreeNode node, int depth) {
        if (node != null) {
            if (node.left == null && node.right == null) {
                if (max_depth < depth) {
                    max_depth = depth;
                }
            }
            traversal(node.left, depth+1);
            traversal(node.right, depth+1);
        }
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
