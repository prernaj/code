package crash.course;

/**
 * https://leetcode.com/problems/k-th-symbol-in-grammar/
 * Question
 * We build a table of n rows (1-indexed). 
 * We start by writing 0 in the 1st row. 
 * Now in every subsequent row, we look at the previous row and replace each occurrence of 0 with 01, and each occurrence of 1 with 10.
 * For example, for n = 3, the 1st row is 0, the 2nd row is 01, and the 3rd row is 0110.
 * Given two integer n and k, return the kth (1-indexed) symbol in the nth row of a table of n rows.
 */
/**
 * Input Output
 * n = 1, k = 1 => 0
 * n = 2, k = 1 => 0
 * n = 2, k = 2 => 1
 * n = 3, k = 1 => 0
 */
/**
 * Thought process
 * think it as a tree. with each evel, each node will have two children.
 * root is 0.
 * return the kth element in the leaf level.
 * 
 * find the first leaf index 
 * add k-1 to first leaf index to get the index of the node to be found when stored in a full binary tree array.
 * travel from the desired node to the root by using the trick that parent index = (i-1)/2.
 * push these indexes in a stack.
 * re-traverse the stack and toggle 0 and 1.
 * return output.
 * Time:
 * build tree: O(height of tree) = O(n)
 * 
 * 
 */
public class Q46_KthSymbolInGrammar {
    public int kthGrammar(int n, int k) {
        int height = n;
        int totalNumberOfNodesInTree = (int)(Math.pow(2.0, (double)n) -1.0);
        int numberOfLeafNodes = (int)(Math.pow(2.0, (double)(n-1)));
        int firstLeafIndex = totalNumberOfNodesInTree - numberOfLeafNodes;
        int indexToFind = firstLeafIndex + k - 1;
        Stack<Integer> stack = new Stack<>();
        int index = indexToFind;
        while(index >= 0) {
            stack.push(index);
            if (index == 0) {
                break;
            }
            index = (index-1)/2;
        }
        int grammarElement = 0;
        int prev = 0;
        while(!stack.isEmpty()) {
            index = stack.pop();
            if (index == 0) {
                grammarElement = 0;
            } else {
                if (index == 2*prev+1) { // left
                    grammarElement = grammarElement == 0 ? 0 : 1;
                } else {
                    grammarElement = grammarElement == 0 ? 1 : 0;
                }
            }
            prev = index;
        }
        return grammarElement;
    }
}
