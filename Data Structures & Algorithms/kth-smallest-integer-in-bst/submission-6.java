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

// 5. Morris Traversal

public class Solution {
    public int kthSmallest(TreeNode root, int k) {
        TreeNode curr = root;

        while (curr != null) {
            if (curr.left == null) {
                // 沒有左子樹，直接拜訪當前節點
                k--;
                if (k == 0) return curr.val;
                curr = curr.right;
            } else {
                // 有左子樹 → 找中序前驅（predecessor）
                TreeNode pred = curr.left;

                // 找左子樹中最右邊的節點（也就是 in-order predecessor）
                while (pred.right != null && pred.right != curr)
                    pred = pred.right;

                if (pred.right == null) {
                    // 第一次來到 curr，建立回路
                    pred.right = curr;
                    curr = curr.left;
                } else {
                    // 第二次回到 curr，解除回路
                    pred.right = null;
                    k--;
                    if (k == 0) return curr.val;
                    curr = curr.right;
                }
            }
        }
        return -1; // k 不合法時（理論上不會發生）
    }
}

/*
        5
       / \
      3   8
     / \ / \
    2  4 6  9
   /
  1


| 步驟 | curr 值 | pred（前驅）                    | 操作                | k 值     | 輸出 |
| -- | ------ | --------------------------- | ----------------- | ------- | -- |
| 1  | 5      | 4                           | pred.right = curr | 5       |    |
| 2  | 3      | 2                           | pred.right = curr | 5       |    |
| 3  | 2      | 1                           | pred.right = curr | 5       |    |
| 4  | 1      | null                        | 左為 null → 拜訪      | 4       | 1  |
| 5  | 回到 2   | pred.right==curr → 解回路 + 拜訪 | 3                 | 2       |    |
| 6  | 回到 3   | pred.right==curr → 解回路 + 拜訪 | 2                 | 3       |    |
| 7  | 4      | 無左 → 拜訪                     | 1                 | 4       |    |
| 8  | 回到 5   | pred.right==curr → 解回路 + 拜訪 | 0                 | ✅ 5（答案） |    |

*/