package crash.course;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
 * Question
 * You have a graph of n nodes. 
 * You are given an integer n and an array edges where edges[i] = [ai, bi] 
 * indicates that there is an edge between ai and bi in the graph.
 * Return the number of connected components in the graph.
 */
/**
 * Input Output
 * n = 5, edges = [[0,1],[1,2],[3,4]] => 2
 * n = 5, edges = [[0,1],[1,2],[2,3],[3,4]] => 1
 */
/**
 * Thought process
 * dfs.
*/
/** 
 * Improvements
 * 
 * In an undirected graph, a connected component is a subgraph in which each pair of vertices is connected via a path. 
 * So essentially, all vertices in a connected component are reachable from one another.
 * 
 * If we run DFS, starting from a particular vertex, 
 * it will continue to visit the vertices depth-wise until there are no more adjacent vertices left to visit. 
 * Each time we finish exploring a connected component, we can find another vertex that has not been visited yet, and start a new DFS from there. 
 * The number of times we start a new DFS will be the number of connected components.
 * 
 * Algo:
 * 1. Create an adjacency list such that adj[v] contains all the adjacent vertices of vertex v.
 * 2. Initialize a hashmap or array, visited, to track the visited vertices.
 * 3. Define a counter variable and initialize it to zero.
 * 4. Iterate over each vertex in edges, and if the vertex is not already in visited, start a DFS from it. 
 * Add every vertex visited during the DFS to visited.
 * 5. Every time a new DFS starts, increment the counter variable by one.
 * 6. At the end, the counter variable will contain the number of connected components in the undirected graph.
 * 
 * Time: O(V+E)
 * Building the adjacency list will take O(E) operations, as we iterate over the list of edges once, and insert each edge into two lists.
 * During the DFS traversal, each vertex will only be visited once. This is because we mark each vertex as visited as soon as we see it, 
 * and then we only visit vertices that are not marked as visited. 
 * In addition, when we iterate over the edge list of each vertex, we look at each edge once. This has a total cost of O(E+V).
 * Space: O(V+E)
 * Building the adjacency list will take O(E) space. 
 * To keep track of visited vertices, an array of size O(V) is required. 
 * Also, the run-time stack for DFS will use O(V) space.
 * 
 * Disjoint Set.
 * Algorithm
 * - Initialize a variable count with the number of vertices in the input.
 * - Traverse all of the edges one by one, performing the union-find method combine on each edge. 
 * If the endpoints are already in the same set, then keep traversing. 
 * If they are not, then decrement count by 1.
 * - After traversing all of the edges, the variable count will contain the number of components in the graph.
 * Time: O(E.alpha(n))
 * Iterating over every edge requires O(E) operations, and for every operation, 
 * we are performing the combine method which is O(α(n)), where α(n) is the inverse Ackermann function.
 * Space: O(V)
 * Storing the representative/immediate-parent of each vertex takes O(V) space. 
 * Furthermore, storing the size of components also takes O(V) space.
 */
public class Q19_NumberOfConnectedComponentsInAnUndirectedGraph {
    private void dfs(List<Integer>[] adjList, int[] visited, int startNode) {
        visited[startNode] = 1;
         
        for (int i = 0; i < adjList[startNode].size(); i++) {
            if (visited[adjList[startNode].get(i)] == 0) {
                dfs(adjList, visited, adjList[startNode].get(i));
            }
        }
    }
    
    public int countComponents(int n, int[][] edges) {
        int components = 0;
        int[] visited = new int[n];
        
        List<Integer>[] adjList = new ArrayList[n]; 
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<Integer>();
        }
        
        for (int i = 0; i < edges.length; i++) {
            adjList[edges[i][0]].add(edges[i][1]);
            adjList[edges[i][1]].add(edges[i][0]);
        }
        
        for (int i = 0; i < n; i++) {
            if (visited[i] == 0) {
                components++;
                dfs(adjList, visited, i);
            }
        }
        return components;
    }
}
