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

    // 判斷某個節點的值是否小於其父節點值，用於檢查左子樹
    static boolean left_check(int val, int limit) {
        return val < limit; 
    }

    // 判斷某個節點的值是否大於其父節點值，用於檢查右子樹
    static boolean right_check(int val, int limit) {
        return val > limit; 
    }

    /**
     * 判斷整棵樹是否為合法的 BST
     * 此函式會：
     * 1. 檢查左子樹是否所有值 < root.val（透過 isValid + left_check）
     * 2. 檢查右子樹是否所有值 > root.val（透過 isValid + right_check）
     * 3. 遞迴檢查左右子樹是否本身也是合法的 BST
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true; // 空樹視為合法 BST
        }

        // 檢查整個左子樹是否都比 root 小，右子樹是否都比 root 大
        if (!isValid(root.left, root.val, Solution::left_check) || 
            !isValid(root.right, root.val, Solution::right_check)) {
            return false; // 若有任一側不滿足條件則直接回傳 false
        }

        // 繼續遞迴檢查左子樹與右子樹本身是否為合法 BST
        return isValidBST(root.left) && isValidBST(root.right);
    }

    /**
     * 通用檢查函式，檢查整棵子樹中所有節點是否符合特定條件
     * @param root 當前要檢查的節點
     * @param limit 父節點值，用於比較
     * @param check 比較函式（是 left_check 或 right_check）
     */
    public boolean isValid(TreeNode root, int limit, CheckFunction check) {
        if (root == null) {
            return true; // 遇到 null 視為通過
        }

        // 檢查當前節點值是否滿足條件
        if (!check.apply(root.val, limit)) {
            return false;
        }

        // 繼續檢查左右子樹是否都滿足相同條件（與同一個 limit 比較）
        return isValid(root.left, limit, check) && 
               isValid(root.right, limit, check);
    }

    // 函式式接口，定義 apply() 方法
    interface CheckFunction {
        boolean apply(int val, int limit);
    }
}