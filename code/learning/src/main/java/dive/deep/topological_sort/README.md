https://leetcode.com/discuss/general-discussion/1078072/introduction-to-topological-sort


When we are scheduling jobs or tasks, they may have dependencies.

Implementations: DFS or BFS

BFS
- For BFS, we need an array indegree to keep the track of indegrees.
- Put all nodes with indegree 0 in the queue
- Remove edges from queue. modify (dcrement by 1) indegree of adjacent nodes.
- repeat until all nodes are visited.

PseudoCode
indegree = an array indicating indegrees for each node
neighbours = a HashMap recording neighbours of each node
queue = []
for i in indegree:
    if indegree[i] == 0:
        queue.append(i)
		
while !queue.empty():
    node = queue.dequeue()
    for neighbour in neighbours[node]:
        indegree[neighbour] -= 1
        if indegree[neighbour] == 0:
            queue.append(neighbour)

DFS
Leaf nodes should always come after their parents and ancestors.
apply DFS and output nodes from leaves to the root.
- implement a boolean array visited
- for each unvisited node, first mark it as visited, call DFS() to start searching its neighbours.
- after finishing this, insert it at the front of the list.
- after visiting all the nodes, return the list

Pseduo Code
def topological_sort():
    for each node:
        if visited[node] is False:
            dfs(node)

def dfs(node):
    visited[node] = True
    for nei in neighbours[node]:
        dfs(node)
	if visited(node) = false:
		ret.insert_at_the _front(node)

https://leetcode.com/problems/course-schedule/
https://leetcode.com/problems/course-schedule-ii/
https://leetcode.com/problems/alien-dictionary/
https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
https://leetcode.com/problems/sequence-reconstruction/
https://leetcode.com/problems/sort-items-by-groups-respecting-dependencies/
