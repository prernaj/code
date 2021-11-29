https://leetcode.com/discuss/general-discussion/655708/Graph-For-Beginners-Problems-or-Pattern-or-Sample-Solutions

*******************************
Identify if problems talks about finding groups or components.
*******************************

https://leetcode.com/problems/friend-circles/
https://leetcode.com/problems/redundant-connection/
https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/
https://leetcode.com/problems/number-of-operations-to-make-network-connected/
https://leetcode.com/problems/satisfiability-of-equality-equations/
https://leetcode.com/problems/accounts-merge/
https://leetcode.com/problems/connecting-cities-with-minimum-cost/


Standard Template
class Solution {
 	vector<int>parent;
 	int find(int x) {
 		return parent[x] == x ? x : find(parent[x]);
 	}
    public:
 	vector<int> findRedundantConnection(vector<vector<int>>& edges) {

 		int n = edges.size();

 		parent.resize(n+1, 0);
 		for (int i = 0; i <= n; i++)
 			parent[i] = i;

 		vector<int>res(2, 0);
 		for (int i = 0; i < n; i++) {
 			int x = find(edges[i][0]);
 			int y = find(edges[i][1]);
 			if (x != y)
 				parent[y] = x;
 			else {
 				res[0] = edges[i][0];
 				res[1] = edges[i][1];
 			}
 		}

 		return res;
 	}
 };
