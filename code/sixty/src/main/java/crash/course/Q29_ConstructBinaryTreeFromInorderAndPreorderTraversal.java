package crash.course;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * Question
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree 
 * and inorder is the inorder traversal of the same tree, construct and return the binary tree.
 */
/**
 * Thought process
 * Preorder is Root L R
 * Inorder is L Root R
 * you find the root from the preorder, find the inder of root in inorder, 
 * left of that index is left subtree and right of that index is right subtree.
 */
/**
 * Improvements
 * A tree has a recursive structure because it has subtrees which are trees themselves.
 * Recursion.
 * The two key observations are:
 * - Preorder traversal follows Root -> Left -> Right, therefore, given the preorder array preorder, we have easy access to the root which is preorder[0].
 * - Inorder traversal follows Left -> Root -> Right, therefore if we know the position of Root, we can recursively split the entire array into two subtrees.
 * 
 * Idea
 * We will design a recursion function: it will set the first element of preorder as the root, and then construct the entire tree. 
 * To find the left and right subtrees, it will look for the root in inorder, so that everything on the left should be the left subtree, 
 * and everything on the right should be the right subtree. Both subtrees can be constructed by making another recursion call.
 * 
 * Algorithm:
 * - Build a hashmap to record the relation of value -> index for inorder, so that we can find the position of root in constant time.
 * - Initialize an integer variable preorderIndex to keep track of the element that will be used to construct the root.
 * - Implement the recursion function arrayToTree which takes a range of inorder and returns the constructed binary tree:
 * -- if the range is empty, return null;
 * -- initialize the root with preorder[preorderIndex] and then increment preorderIndex;
 * -- recursively use the left and right portions of inorder to construct the left and right subtrees.
 * 
 * Simply call the recursion function with the entire range of inorder.
 * Time: O(N)
 * Space: O(N)
 */
public class Q29_ConstructBinaryTreeFromInorderAndPreorderTraversal {
    int preorderIndex;
    Map<Integer, Integer> inorderIndexMap;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        preorderIndex = 0;
        // build a hashmap to store value -> its index relations
        inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        return arrayToTree(preorder, 0, preorder.length - 1);
    }

    private TreeNode arrayToTree(int[] preorder, int left, int right) {
        // if there are no elements to construct the tree
        if (left > right) return null;

        // select the preorder_index element as the root and increment it
        int rootValue = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootValue);

        // build left and right subtree
        // excluding inorderIndexMap[rootValue] element because it's the root
        root.left = arrayToTree(preorder, left, inorderIndexMap.get(rootValue) - 1);
        root.right = arrayToTree(preorder, inorderIndexMap.get(rootValue) + 1, right);
        return root;
    }
}
