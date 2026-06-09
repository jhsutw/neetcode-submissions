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
    public int maxDepth(TreeNode root) {
        // 如果節點為空，表示深度為 0（遞迴終止條件）
        if (root == null) {
            return 0;
        }

        // 遞迴計算左右子樹最大深度，並取其中較大值 + 1（代表當前層）
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}

/*
範例輸入：root = [1, 2, 3, 4, 5]

對應二元樹結構如下：

        1
       / \
      2   3
     / \
    4   5

執行 maxDepth(root) 的遞迴流程：

- maxDepth(1)
  → 1 + max(maxDepth(2), maxDepth(3))

- maxDepth(2)
  → 1 + max(maxDepth(4), maxDepth(5))
  → maxDepth(4) = 1（葉節點）
  → maxDepth(5) = 1
  → 所以 maxDepth(2) = 1 + max(1, 1) = 2

- maxDepth(3) = 1（葉節點）

→ 所以 maxDepth(1) = 1 + max(2, 1) = 3

最終回傳結果：3

說明：
- 此方法採用 DFS（深度優先搜尋）遞迴方式
- 每層返回左右子樹中較大的深度 + 1
- 適合小型或中型樹結構，程式簡潔易懂
*/
