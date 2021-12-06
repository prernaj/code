package crash.course;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
 * Question
 * Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
 */
/**
 * Thought process
 * bfs. queue. null pointer at end.
 * Time: O(n)
 * Space: O(n)
 */
/**
 * Improvements
 * Without using special temp node. levelOrderBFS2
 * Algorithm:
 * - Initiate queue with a root and start from the level number 0 : level = 0.
 * - While queue is not empty :
 * -- Start the current level by adding an empty list into output structure levels.
 * -- Compute how many elements should be on the current level : it's a queue length.
 * -- Pop out all these elements from the queue and add them into the current level.
 * -- Push their child nodes into the queue for the next level.
 * -- Go to the next level level++.
 * Time: O(n)
 * Space: O(n)
 * 
 * Recursion
 * The output list here is called levels, and hence the current level is just a length of this list len(levels). 
 * Compare the number of a current level len(levels) with a node level level. 
 * If you're still on the previous level - add the new one by adding a new list into levels.
 * Append the node value to the last list in levels.
 * Process recursively child nodes if they are not None : helper(node.left / node.right, level + 1).
 * Yet to clarify in mind. 
 * 
 * Time: O(n)
 * Space: O(n)
 */
public class Q26_BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        TreeNode temp = null;
        queue.add(temp);
        List<List<Integer>> output = new ArrayList<>();
        List<Integer> set = new ArrayList<>();
        while(!queue.isEmpty() && check(queue, temp)) {
            TreeNode node = queue.peek();
            queue.remove();
            if (node == temp) {
                output.add(set);
                queue.add(temp);
                set = new ArrayList<>();
            } else {
                set.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        if (!set.isEmpty()) {
            output.add(set);
        }
        return output;
    }
    
    public boolean check(Queue<TreeNode> queue, TreeNode temp) {
        for (TreeNode item: queue) {
            if (item != temp) {
                return true;
            }
        }
        return false;
    }

    public List<List<Integer>> levelOrderBFS2(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<List<Integer>>();
        if (root == null) return levels;
    
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int level = 0;
        while ( !queue.isEmpty() ) {
          // start the current level
          levels.add(new ArrayList<Integer>());
    
          // number of elements in the current level
          int level_length = queue.size();
          for(int i = 0; i < level_length; ++i) {
            TreeNode node = queue.remove();
    
            // fulfill the current level
            levels.get(level).add(node.val);
    
            // add child nodes of the current level
            // in the queue for the next level
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
          }
          // go to next level
          level++;
        }
        return levels;
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
