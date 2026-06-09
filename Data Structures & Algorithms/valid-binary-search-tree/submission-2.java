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

// 2. Depth First Search

public class Solution {

    /**
     * 主函式：判斷整棵樹是否為合法 BST
     * 使用上下限範圍遞迴檢查，初始範圍為 (-∞, +∞)
     */
    public boolean isValidBST(TreeNode root) {
        return valid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * 遞迴函式：檢查節點是否在合法區間內，並遞迴檢查左右子樹
     * @param node 目前要檢查的節點
     * @param left 節點應該大於的下限
     * @param right 節點應該小於的上限
     * @return 若該子樹是合法 BST，則回傳 true
     */
    public boolean valid(TreeNode node, long left, long right) {
        if (node == null) {
            return true; // 空節點視為合法
        }

        // 當前節點的值若不在合法區間內，則不是 BST
        if (!(left < node.val && node.val < right)) {
            return false;
        }

        // 左子樹：最大值不能超過目前節點值
        // 右子樹：最小值不能小於目前節點值
        return valid(node.left, left, node.val) && 
               valid(node.right, node.val, right);
    }
}