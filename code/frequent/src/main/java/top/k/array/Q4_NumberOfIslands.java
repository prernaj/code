package top.k.array;

/**
 * Question
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
 * You may assume all four edges of the grid are all surrounded by water.
 */

/**
 * Input Output
 * grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
 * output = 1
 * grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
 * output = 3 
 */

/**
 * Thought process
 * DFS.
 * - Linear scan. if node = 1, trigger DFS. count++
 * - during DFS, mark node as 0 to mark as visited.
 * - return count.
 * 
 * Time: O(m*n)
 * Space: O(m*n)
 * 
 * BFS.
 * - linear scan. if node is 1, trigeer BFS.
 * - put it in queue and set it as 0 to mark as visited.
 * - iteratively search the neighbours of enqueued nodes until the queue becomes empty.
 * 
 * DID not understand it well.
 * Time: O((M*N)
 * Space: O(min(M,N))
 * 
 * Union Find.
 * - traverse the grid.
 * time: O(m*n)
 * space: O(m*n)
 * 
 */
public class Q4_NumberOfIslands {
    
}
