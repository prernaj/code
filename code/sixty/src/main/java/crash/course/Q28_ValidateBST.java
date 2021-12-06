package crash.course;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/validate-binary-search-tree/
 * Question
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 * A valid BST is defined as follows:
 * - The left subtree of a node contains only nodes with keys less than the node's key.
 * - The right subtree of a node contains only nodes with keys greater than the node's key.
 * - Both the left and right subtrees must also be binary search trees.
 */
/**
 * Thought process
 * check for each node if it is greater than left and less than right
 * if not. return false. else return true;
 * Wrong answer
 * 5->l = 4
 * 5->r = 6
 * 6->l = 3
 * 6->r = 7
 */
/**
 * Improvements
 * Recursive Traversal with Valid Range
 * That means one should keep both upper and lower limits for each node while traversing the tree, 
 * and compare the node value not with children values but with these limits.
 * Time: O(n)
 * Space: O(n)
 * 
 * Iterative Traversal with Valid Range
 * The above recursion could be converted into iteration, with the help of an explicit stack. 
 * DFS would be better than BFS since it works faster here.
 * To be done later...
 * 
 * Inorder Traversal
 * Hence the algorithm with \mathcal{O}(N)O(N) time complexity and \mathcal{O}(N)O(N) space complexity could be simple:
 * Compute inorder traversal list inorder.
 * Check if each element in inorder is smaller than the next one.
 * Do we need to keep the whole inorder traversal list?
 * No. The last added inorder element is enough to ensure at each step that the tree is BST (or not). 
 * Hence one could merge both steps into one and reduce the used space.
 * Time: O(N)
 * Space: O(N)
 * 
 * Iterative
 * 
 */
public class Q28_ValidateBST {
    public boolean isValidBST(TreeNode root) {
        return validate(root, null, null);
    }

    public boolean validate(TreeNode root, Integer low, Integer high) {
        // Empty trees are valid BSTs.
        if (root == null) {
            return true;
        }
        // The current node's value must be between low and high.
        if ((low != null && root.val <= low) || (high != null && root.val >= high)) {
            return false;
        }
        // The left and right subtree must also be valid.
        return validate(root.right, root.val, high) && validate(root.left, low, root.val);
    }

    // We use Integer instead of int as it supports a null value.
    private Integer prev;

    public boolean isValidBSTInorder(TreeNode root) {
        prev = null;
        return inorder(root);
    }

    private boolean inorder(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!inorder(root.left)) {
            return false;
        }
        if (prev != null && root.val <= prev) {
            return false;
        }
        prev = root.val;
        return inorder(root.right);
    }

    public boolean isValidBSTInorderIterative(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        Integer prev = null;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // If next element in inorder traversal
            // is smaller than the previous one
            // that's not BST.
            if (prev != null && root.val <= prev) {
                return false;
            }
            prev = root.val;
            root = root.right;
        }
        return true;
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

