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
    // res[i] 表示第 i 層的節點值
    List<List<Integer>> res = new ArrayList<>();
    
    public List<List<Integer>> levelOrder(TreeNode root) {
        dfs(root, 0); // 從根節點、深度 0 開始遞迴
        return res;
    }
    
    private void dfs(TreeNode node, int depth) {
        if (node == null) {
            return; // 如果節點為空，直接返回
        }

        // 當層級列表尚未建立時，新增一個空的 list (depth - 1才是層數)
        if (res.size() == depth) {
            res.add(new ArrayList<>());
        }

        // 將目前節點的值加入對應層級的 list
        res.get(depth).add(node.val);

        // 遞迴處理左子樹與右子樹，層級加一
        dfs(node.left, depth + 1);
        dfs(node.right, depth + 1);
    }
}

/*
     1
    / \
   2   3
  /   / \
 4   5   6

dfs(1, 0) → res = [[1]]
dfs(2, 1) → res = [[1], [2]]
dfs(4, 2) → res = [[1], [2], [4]]
dfs(null, 3) → return
dfs(null, 3) → return
dfs(3, 1) → res = [[1], [2, 3]]
dfs(5, 2) → res = [[1], [2, 3], [4, 5]]
dfs(null, 3) → return
dfs(null, 3) → return
dfs(6, 2) → res = [[1], [2, 3], [4, 5, 6]]
*/