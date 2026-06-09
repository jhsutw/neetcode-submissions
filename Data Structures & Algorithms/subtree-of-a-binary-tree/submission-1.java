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

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        // 如果子樹是 null，預設任何樹都有這個空子樹
        if (subRoot == null) {
            return true;
        }
        // 如果主樹是 null，但子樹不是，代表不可能有這棵子樹
        if (root == null) {
            return false;
        }

        // 如果目前節點與子樹根節點相同，就檢查是否整棵子樹相同
        if (sameTree(root, subRoot)) {
            return true;
        }

        // 否則繼續往左子樹或右子樹找是否存在相同子樹 (會遍歷每個node，直到符合上面其中一個case為止)
        return isSubtree(root.left, subRoot) || 
               isSubtree(root.right, subRoot);
    }

    // 判斷兩棵樹是否完全相同
    public boolean sameTree(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }
        if (root != null && subRoot != null && root.val == subRoot.val) {
            return sameTree(root.left, subRoot.left) && 
                   sameTree(root.right, subRoot.right);
        }
        return false;
    }
}

/*
/**
 * 範例測資：
 *
 * root:
 *       3
 *      / \
 *     4   5
 *    / \
 *   1   2
 *
 * subRoot:
 *     4
 *    / \
 *   1   2
 */

/*
TreeNode root = new TreeNode(3,
    new TreeNode(4,
        new TreeNode(1),
        new TreeNode(2)
    ),
    new TreeNode(5)
);

TreeNode subRoot = new TreeNode(4,
    new TreeNode(1),
    new TreeNode(2)
);

// 開始呼叫 isSubtree(3, 4)
isSubtree(3, 4)
// sameTree(3, 4) → false（值不同）
// → 繼續遞迴呼叫 isSubtree(3.left, 4) = isSubtree(4, 4)

isSubtree(4, 4)
// sameTree(4, 4) → 值相等 → 繼續檢查左右子樹

sameTree(1, 1) → true（左右子樹為 null）
sameTree(2, 2) → true（左右子樹為 null）

// 所以 sameTree(4, 4) = true → 表示 subRoot 就是 root 的子樹

// 回傳結果：true
*/