package top.k.tree;

/**
 * Question
 * Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
 */

/**
 * Thought process
 * return the last element from all levels.
 * 
 * BFS. using queue with sentinel
 * Time: O(n)
 * Space: O(d) d for diamter
 * 
 * Recursive DFS. To traverse the tree level by level, starting each time from rightmost child.
 * Time: O(n)
 * Space: O(h) h for height
 * 
 */
public class Q5_BTRightSideView {
    
}
