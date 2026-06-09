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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // 如果兩棵子樹都是空的，表示相同
        if (p == null && q == null) {
            return true;
        }

        // 如果兩個節點都不為 null 且值相同，則繼續遞迴比較左右子樹
        if (p != null && q != null && p.val == q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        } else {
            // 只要有一個為 null 或值不相同，代表樹不同
            return false;
        }
    }
}

/*
p = [1,2,3]
q = [1,2,3]

    1              1
   / \            / \
  2   3          2   3

isSameTree(p, q)
→ p.val = 1, q.val = 1 → 相等，繼續比對左右子樹

→ isSameTree(p.left, q.left)
   → p.left.val = 2, q.left.val = 2 → 相等，左右子樹皆為 null
   → isSameTree(null, null) → true
   → isSameTree(null, null) → true
   → 返回 true

→ isSameTree(p.right, q.right)
   → p.right.val = 3, q.right.val = 3 → 相等，左右子樹皆為 null
   → isSameTree(null, null) → true
   → isSameTree(null, null) → true
   → 返回 true

→ 左右子樹皆為 true → 回傳 true ✅
*/