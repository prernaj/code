package crash.course;

import java.util.LinkedList;

/**
 * https://leetcode.com/problems/minimum-depth-of-binary-tree/
 * Question
 * Given a binary tree, find its minimum depth.
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 * Note: A leaf is a node with no children.
 */
/**
 * Thought process
 * same as max_depth. keep a global min_depth variable.
 */
/**
 * Improvement
 * Recursion.
 * intuitive approach.
 * 
 * Time: O(N) where N is number of nodes
 * Space: O(N)
 * 
 * DFS Iterative
 * The idea is to visit each leaf with the DFS strategy, while updating the minimum depth when we reach the leaf node.
 * 
 */
public class Q22_MinimumDepthOfBinaryTree {
    public int minDepth(TreeNode root) {
        if (root == null) {
          return 0;
        }
    
        if ((root.left == null) && (root.right == null)) {
          return 1;
        }
    
        int min_depth = Integer.MAX_VALUE;
        if (root.left != null) {
          min_depth = Math.min(minDepth(root.left), min_depth);
        }
        if (root.right != null) {
          min_depth = Math.min(minDepth(root.right), min_depth);
        }
    
        return min_depth + 1;
    }

    public int minDepthDFSIterative(TreeNode root) {
        LinkedList<Pair> stack = new LinkedList<>();
        if (root == null) {
          return 0;
        }
        else {
          stack.add(new Pair(root, 1));
        }
    
        int min_depth = Integer.MAX_VALUE;
        while (!stack.isEmpty()) {
          Pair current = stack.pollLast();
          root = current.getKey();
          int current_depth = current.getValue();
          if ((root.left == null) && (root.right == null)) {
            min_depth = Math.min(min_depth, current_depth);
          }
          if (root.left != null) {
            stack.add(new Pair(root.left, current_depth + 1));
          }
          if (root.right != null) {
            stack.add(new Pair(root.right, current_depth + 1));
          }
        }
        return min_depth;
    }

    class Pair {
        TreeNode key;
        int value;
        public Pair(TreeNode key, int value) {
            this.key = key;
            this.value = value;
        }
        public TreeNode getKey() {
          return this.key;
        }
        public int getValue() {
          return this.value;
        }
    }
}
