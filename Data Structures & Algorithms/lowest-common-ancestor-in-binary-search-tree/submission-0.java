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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 若 root、p、q 其中之一為 null，則無法繼續，回傳 null
        if (root == null || p == null || q == null) {
            return null;
        }

        // 如果 p 和 q 都小於 root，表示最近公共祖先在左子樹
        if (Math.max(p.val, q.val) < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        
        // 如果 p 和 q 都大於 root，表示最近公共祖先在右子樹
        } else if (Math.min(p.val, q.val) > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        
        // 否則，root 介於 p 和 q 之間（或就是其中一個），就是最近公共祖先
        } else {
            return root;
        }
    }
}
