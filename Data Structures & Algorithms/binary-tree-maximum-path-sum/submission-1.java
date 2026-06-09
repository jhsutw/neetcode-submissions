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

// 2. Depth First Search (Optimal)
public class Solution {

    public int maxPathSum(TreeNode root) {
        int[] res = new int[]{root.val}; // 使用陣列當作可變參數，初始化為 root 的值，因為可能為負數
        dfs(root, res);                  // 開始 DFS 探索每個節點作為最高點的路徑和
        return res[0];                   // 回傳最大路徑總和
    }

    private int dfs(TreeNode root, int[] res) {
        if (root == null) {
            return 0; // 空節點不貢獻任何路徑值
        }

        // 遞迴處理左右子樹，若回傳值為負則視為 0（代表不貢獻，捨棄）
        int leftMax = Math.max(dfs(root.left, res), 0);
        int rightMax = Math.max(dfs(root.right, res), 0);

        // 更新最大路徑總和：以當前節點作為最高點，包含左右貢獻的完整路徑
        res[0] = Math.max(res[0], root.val + leftMax + rightMax);

        // 回傳單邊最大貢獻給上一層（只能選一邊延伸，不能左右分叉）
        return root.val + Math.max(leftMax, rightMax);
    }
}