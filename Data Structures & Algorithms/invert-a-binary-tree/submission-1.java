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
    public TreeNode invertTree(TreeNode root) {
        // 遞迴終止條件：如果是空節點，直接回傳 null
        if (root == null) return null;

        // 先交換左右子節點
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        // 再對左右子節點分別遞迴反轉
        invertTree(root.left);
        invertTree(root.right);

        // 回傳當前節點（已完成左右交換）
        return root;
    }
}

/*
範例：root = [1, 2, 3, 4, 5, 6, 7]

原始樹結構：
        1
      /   \
     2     3
    / \   / \
   4   5 6   7

執行遞迴 invertTree(root) 過程：

Step 1: root = 1
- 交換左右 → 2 ↔ 3
- 遞迴處理 3（原右子）和 2（原左子）

        1
      /   \
     3     2

---

Step 2: root = 3
- 交換左右 → 6 ↔ 7
- 遞迴處理 7 和 6

        3
      /   \
     7     6

Step 3: root = 2
- 交換左右 → 4 ↔ 5
- 遞迴處理 5 和 4

        2
      /   \
     5     4

---

最終翻轉後整棵樹為：
        1
      /   \
     3     2
    / \   / \
   7   6 5   4

對應的 level-order 輸出為：[1, 3, 2, 7, 6, 5, 4]

備註：
- 此實作屬於 DFS（深度優先搜尋），採用前序遞迴方式
- 遞迴架構簡潔明瞭，但若樹深度過大，可能導致 StackOverflowError
- 若想避免遞迴限制，可改用 BFS（佇列）方式實作
*/