package top.k.tree;

/**
 * Question
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 */

/**
 * Thought process
 * Use tree traversal to search for two nodes. one we reach the desired nodes, we can backtrack to find its LCA.
 * 
 * Recursion.
 * Traverse the tree in DFS. The moment you find p or q, return a boolean flag.
 * The flag helps to determine if we found the required nodes in any of the paths.
 * The LCA would then be the node for which both the subtree recursions return a True flag.
 * 
 * Time: O(n)
 * Space: O(n)
 * 
 */
public class Q1_LCABinaryTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return findLCA(root, p, q);
    }
    
    public TreeNode findLCA(TreeNode root, TreeNode p, TreeNode q) {
        if (root != null) {
            if (root == p || root == q) {
                return root;
            }
            TreeNode left = findLCA(root.left, p, q);
            TreeNode right = findLCA(root.right, p, q);
            if (left != null && right != null) {
                return root;
            }
            if (left != null) {
                return left;
            }
            return right;
        }
        return null;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
        val = x;
    }
}
