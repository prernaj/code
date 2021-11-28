package top.k.tree;

/**
 * Question
 * Given the root of a binary tree, the value of a target node target, and an integer k, 
 * return an array of the values of all nodes that have a distance k from the target node.
 * You can return the answer in any order.
 */

/**
 * Thought process
 * Annotate Parent.
 * If we know the parent of every node, we know al the nodes at distance 1 from x.
 * We can then perform a BFS from target node to find the answer.
 * Algo:
 * - do DFS to annotate every node about it's parent.
 * - do BFS to find all nodes at distance k from target.
 * 
 * Time: O(n)
 * Space: O(n)
 */
public class Q4_DistanceKNodesBT {
    
}
