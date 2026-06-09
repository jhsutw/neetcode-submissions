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
    public int diameterOfBinaryTree(TreeNode root) {
        // 空樹的直徑為 0
        if (root == null) {
            return 0;
        }

        // 左右子樹高度
        int leftHeight = maxHeight(root.left);
        int rightHeight = maxHeight(root.right);

        // 如果通過 root，直徑 = 左高 + 右高（不加 1，因為是邊的數量）
        int diameter = leftHeight + rightHeight;

        // 左子樹或右子樹可能有更大的直徑（不一定經過 root）：用下層的節點為root計算直徑（直到最後每一個節點都會被當作過root計算直徑）
        int sub = Math.max(diameterOfBinaryTree(root.left),
                           diameterOfBinaryTree(root.right));

        // 取最大者
        return Math.max(diameter, sub);
    }

    // 計算以某個節點為根的最大高度
    public int maxHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.max(maxHeight(root.left), maxHeight(root.right));
    }
}

/*
範例輸入：root = [1, 2, 3, 4, 5]

        1
       / \
      2   3
     / \
    4   5

每個節點都會：
- 計算左右子樹的高度（maxHeight）
- 用 left + right 當作「通過該節點的直徑」
- 再往下遞迴比較子樹的最大直徑

執行 trace：

- root = 1 → leftHeight = 2, rightHeight = 1 → diameter = 3
- 左子樹（2）遞迴 → diameter = 2（來自節點 4,5）
- 右子樹（3）遞迴 → diameter = 0

最終結果為 max(3, 2, 0) = 3

⚠️ 時間複雜度分析：
- `maxHeight()` 是 O(n)，但會被重複呼叫很多次
- 每個節點都要呼叫 `maxHeight()` 一次 ⇒ 導致總體時間複雜度為 **O(n²)**

---

✅ 優化建議（O(n)）：
- 使用 **一個遞迴 DFS** 函數，在求高度的同時記錄最大直徑（用全域變數）
- 這樣就不需要重複計算高度，效率大幅提升

你想要我幫你寫出 O(n) 的最佳解版本嗎？我可以幫你附註解整理好
*/