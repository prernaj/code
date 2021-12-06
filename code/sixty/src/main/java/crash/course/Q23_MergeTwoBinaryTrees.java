package crash.course;

import java.util.Stack;

/**
 * https://leetcode.com/problems/merge-two-binary-trees/
 * Question
 * You are given two binary trees root1 and root2.
 * Imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not. 
 * You need to merge the two trees into a new binary tree. 
 * The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. 
 * Otherwise, the NOT null node will be used as the node of the new tree.
 * Return the merged tree.
 * Note: The merging process must start from the root nodes of both trees.
 */
/**
 * Thought process
 * traverse both the tree simultaneously
 * if both nodes are not null. sum their values. and insert in new tree
 * if one of them is not null. add that value in the new tree.
 * if both are not null. return.
 * Time: O(max(m,n)) ma dn n are the number of nodes in both trees resectively.
 * Space: O(max(m,n))
 */
/**
 * Improvements
 * Recursion in style.
 * Time: O(min(m,n))
 * Space: O(min(m,n))
 * 
 * Iterative
 * using stack.
 * each entry in the stack stores data in the form of node1, node2.
 * We start off by pushing the root nodes of both the trees onto the stackstack. 
 * Then, at every step, we remove a node pair from the top of the stack. 
 * For every node pair removed, we add the values corresponding to the two nodes and update the value of the corresponding node in the first tree. 
 * Then, if the left child of the first tree exists, we push the left child(pair) of both the trees onto the stack. 
 * If the left child of the first tree doesn't exist, we append the left child(subtree) of the second tree to the current node of the first tree. 
 * We do the same for the right child pair as well.
 * If, at any step, both the current nodes are null, we continue with popping the next nodes from the stackstack.
 * Time: O(n)
 * Space: O(n)
 */

public class Q23_MergeTwoBinaryTrees {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null)
            return t2;
        if (t2 == null)
            return t1;
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }
    public TreeNode mergeTreesIterative(TreeNode t1, TreeNode t2) {
        if (t1 == null)
            return t2;
        Stack <TreeNode[]> stack = new Stack<>();
        stack.push(new TreeNode[] {t1, t2});
        while (!stack.isEmpty()) {
            TreeNode[] t = stack.pop();
            if (t[0] == null || t[1] == null) {
                continue;
            }
            t[0].val += t[1].val;
            if (t[0].left == null) {
                t[0].left = t[1].left;
            } else {
                stack.push(new TreeNode[] {t[0].left, t[1].left});
            }
            if (t[0].right == null) {
                t[0].right = t[1].right;
            } else {
                stack.push(new TreeNode[] {t[0].right, t[1].right});
            }
        }
        return t1;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
