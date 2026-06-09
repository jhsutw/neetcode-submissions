/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

public class Solution {
    List<Integer> res = new ArrayList<>(); // 用來儲存每層的最右邊節點
    
    public List<Integer> rightSideView(TreeNode root) {
        dfs(root, 0);  // 從根節點開始 DFS，初始深度為 0
        return res;
    }
    
    private void dfs(TreeNode node, int depth) {
        if (node == null) return; // 如果節點為 null，直接返回

        // 如果目前深度的結果尚未加入，代表這是該層第一個遇到的節點（也就是最右邊的節點）
        if (res.size() == depth) {
            res.add(node.val);  // 加入結果
        }

        // 先走右子樹，再走左子樹，這樣每層會先看到最右邊的節點
        dfs(node.right, depth + 1);
        dfs(node.left, depth + 1);
    }
}

/*
      1
     / \
    2   3
     \   \
      5   4

遍歷順序如下（先右後左）：
depth = 0：1 是第一個（加入）
depth = 1：3 是第一個（加入）
depth = 2：4 是第一個（加入）
*/
