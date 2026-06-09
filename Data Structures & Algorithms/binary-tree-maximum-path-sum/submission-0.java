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

// 1. Depth First Search

public class Solution {
    int res = Integer.MIN_VALUE; // 紀錄目前為止的最大路徑和，初始設為最小整數

    public int maxPathSum(TreeNode root) {
        dfs(root); // 從 root 開始 DFS 探索所有節點
        return res; // 回傳最終結果
    }

    // 計算從某個節點出發，往下走的最大單邊路徑和（左 or 右擇其一）
    private int getMax(TreeNode root) {
        if (root == null) return 0; // 如果是空節點，最大貢獻為 0
        int left = getMax(root.left);   // 左子樹的最大貢獻
        int right = getMax(root.right); // 右子樹的最大貢獻
        int path = root.val + Math.max(left, right); // 從當前節點向下延伸的最大貢獻
        return Math.max(0, path); // 如果小於 0，就不取（視為 0，不貢獻）
    }

    // 主 DFS 函數：計算每個節點作為「最高點」時的最大路徑和
    // 先透過getMax()計算每個節點單向路徑的連接最大值，再用dfs()計算雙向的
    private void dfs(TreeNode root) {
        if (root == null) return;

        int left = getMax(root.left);   // 左子樹最大貢獻
        int right = getMax(root.right); // 右子樹最大貢獻

        // 將當前節點作為路徑最高點（左右皆取）的總和，與當前最大值比較
        res = Math.max(res, root.val + left + right);

        // 繼續遞迴左、右子樹
        dfs(root.left);
        dfs(root.right);
    }
}
