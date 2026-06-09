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
    public int goodNodes(TreeNode root) {
        return dfs(root, root.val);
    }

    private int dfs(TreeNode node, int maxVal){
        if (node == null){
            return 0;
        }

        int res = (node.val >= maxVal) ? 1 : 0;
        maxVal = Math.max(maxVal, node.val);
        res += dfs(node.left, maxVal);
        res += dfs(node.right, maxVal);
        return res;
    }
}

/*
      3
     / \
    1   4
   /   / \
  3   1   5

TreeNode root = new TreeNode(3,
    new TreeNode(1,
        new TreeNode(3),
        null
    ),
    new TreeNode(4,
        new TreeNode(1),
        new TreeNode(5)
    )
);

▶ 遍歷 root = 3：
3 >= 3 → ✅ 是 good node → res = 1
maxVal = 3
進入左子樹 dfs(node.left, 3)

▶ 遍歷左子節點 = 1：
1 < 3 → ❌ 不是 good node → res = 0
maxVal = 3
進入左子節點 dfs(node.left, 3)（值是 3）

▶ 遍歷左子節點的左節點 = 3：
3 >= 3 → ✅ 是 good node → res = 1
maxVal = 3
左右子樹為 null → 各回傳 0
回傳總數 1
回到上一層的 1，左子樹總和是 1，右子樹是 null → 回傳 0
→ 小計：res = 0 + 1 + 0 = 1
回到 root 的左子樹部分：總共得到 1 個 good node。

▶ 右子節點 = 4：
4 >= 3 → ✅ 是 good node → res = 1
更新 maxVal = 4
左子樹 → dfs(1, 4)
右子樹 → dfs(5, 4)

▶ 遍歷 4 的左子節點 = 1：
1 < 4 → ❌ 不是 good node → res = 0
maxVal = 4
子節點為 null → 回傳 0
→ 回傳 0

▶ 遍歷 4 的右子節點 = 5：
5 >= 4 → ✅ 是 good node → res = 1
maxVal = 5
左右皆為 null → 回傳 0
→ 回傳 1
回到節點 4：
本身是 good（1）
左子樹 0，右子樹 1 → 總共 2
*/