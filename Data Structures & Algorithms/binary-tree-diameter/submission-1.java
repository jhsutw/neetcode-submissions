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

class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        // 使用一個陣列作為 reference，儲存整棵樹的最大直徑（最長路徑長度）
        int[] res = new int[1];

        // DFS 探訪節點，同時計算高度與更新最大直徑
        dfs(root, res);

        // 回傳最大直徑（以邊的數量計算）
        return res[0];
    }

    // 回傳以當前節點為根的最大高度，同時在過程中更新最大直徑
    private int dfs(TreeNode root, int[] res){
        // base case: 只要下層left/right節點是空就回傳0，否則高度+1
        if (root == null){
            return 0;
        }

        // 分別計算左右子樹的高度
        int left = dfs(root.left, res);
        int right = dfs(root.right, res);

        // 更新目前為止的最大直徑（通過該節點的左+右最大長度）
        res[0] = Math.max(res[0], left + right);

        // 回傳該節點的高度（自己 + max(左右子樹高度)）
        return 1 + Math.max(left, right);
    }
}

/*
範例輸入：root = [1, 2, 3, 4, 5]

        1
       / \
      2   3
     / \
    4   5

執行流程：
- 對每個節點，遞迴計算左右子樹的高度
- 同時計算經過該節點的最大直徑（left + right）
- 比較所有節點的直徑，儲存在 res[0] 中

關鍵概念：
- 「直徑」是任意兩節點間的最長路徑長度（以邊數計）
- 最大直徑不一定經過 root，但一定是某個節點的 left + right 高度總和
- 時間複雜度 O(n)，因為每個節點只被訪問一次
- 空間複雜度 O(h)，h 為樹的高度（遞迴 stack 使用）

範例結果：
最大直徑 = 節點 4 → 2 → 1 → 3，總長度為 3（邊）
*/

