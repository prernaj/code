package top.k.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits 
 * so that it can be stored in a file or memory buffer, or transmitted across a network connection link 
 * to be reconstructed later in the same or another computer environment.
 * 
 * Design an algorithm to serialize and deserialize a binary tree. 
 */

/**
 * Thought process
 * Tree Traversal. and serialize to a string. DFS or BFS.
 * In this scenario, DFS is adaptive, since the linkage among the adjacent nodes is naturally encoded in the order,
 * which is rather helpful for the later task of deserialization.
 * 
 * Preorder traversal.
 * 
 * Deserialize.
 * Goes along the string. initiate the root value and then calls itself to construct its left and right child nodes.
 * Time: O(n). visit each node exactly once. 
 * Space: O(n)
 */
public class Q3_SerializeDeserializeBT {
   public String serialize(TreeNode2 root) {
        return serializeTree(root, "");
   } 

   public String serializeTree(TreeNode2 root, String str) {
        if (root == null) {
            str += "null,";
        } else {
            str += str.valueOf(root.val) + ",";
            str = serializeTree(root.left, str);
            str = serializeTree(root.right, str);
        }
        return str;
   }

   public TreeNode deserialize(String data) {
        String[] data_array = data.split(",");
        List<String> data_list = new LinkedList<String>(Arrays.asList(data_array));
        return deserializeTree(data_list);
   }

   public TreeNode deserializeTree(List<String> dataList) {
        if (dataList.get(0).equals("null")) {
            dataList.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(dataList.get(0)));
        dataList.remove(0);
        root.left = deserializeTree(dataList);
        root.right = deserializeTree(dataList);

        return root;
   }
}

class TreeNode2 {
    int val;
    TreeNode2 left;
    TreeNode2 right;
    TreeNode2(int val) {
        this.val = val;
    }
}
