package crash.course;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 * Question
 * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. 
 * (i.e., from left to right, then right to left for the next level and alternate between).
 */
/**
 * Thought process
 * bfs. level order. store in output list of list.
 * for even number of level, reverse the output.
 * Time: O(N)
 * Space: O(N)
 */
/**
 * Improvements
 * One of the keys here is to store the values that are of the same level with the deque (double-ended queue) data structure, 
 * where we could add new values on either end of a queue.
 * Algorithm
 * There are several ways to implement the BFS algorithm.
 * - One way would be that we run a two-level nested loop, with the outer loop iterating each level on the tree, 
 * and with the inner loop iterating each node within a single level.
 * - We could also implement BFS with a single loop though. 
 * The trick is that we append the nodes to be visited into a queue and we separate nodes of different levels
 * with a sort of delimiter (e.g. an empty node). 
 * The delimiter marks the end of a level, as well as the beginning of a new level.
 * Time: O(N) Space: O(N)
 * 
 * DFS.
 * The trick is that during the DFS traversal, we maintain the results in a global array that is indexed by the level, 
 * i.e. the element array[level] would contain all the nodes that are at the same level. 
 * The global array would then be referred and updated at each step of DFS.
 * Time: O(N) Space: O(H)
 */
public class Q27_BinaryTreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
          return new ArrayList<List<Integer>>();
        }
    
        List<List<Integer>> results = new ArrayList<List<Integer>>();
    
        // add the root element with a delimiter to kick off the BFS loop
        LinkedList<TreeNode> node_queue = new LinkedList<TreeNode>();
        node_queue.addLast(root);
        node_queue.addLast(null);
    
        LinkedList<Integer> level_list = new LinkedList<Integer>();
        boolean is_order_left = true;
    
        while (node_queue.size() > 0) {
          TreeNode curr_node = node_queue.pollFirst();
          if (curr_node != null) {
            if (is_order_left)
              level_list.addLast(curr_node.val);
            else
              level_list.addFirst(curr_node.val);
    
            if (curr_node.left != null)
              node_queue.addLast(curr_node.left);
            if (curr_node.right != null)
              node_queue.addLast(curr_node.right);
    
          } else {
            // we finish the scan of one level
            results.add(level_list);
            level_list = new LinkedList<Integer>();
            // prepare for the next level
            if (node_queue.size() > 0)
              node_queue.addLast(null);
            is_order_left = !is_order_left;
          }
        }
        return results;
      }
}
