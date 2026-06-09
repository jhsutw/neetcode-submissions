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


// 1. Brute Force
public class Solution {
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> arr = new ArrayList<>();
        
        dfs(root, arr); // 將整棵樹的所有節點值收集到 arr 中
        Collections.sort(arr); // 排序 arr 中的值，確保是從小到大
        return arr.get(k - 1); // 回傳排序後第 k 小的元素（index 從 0 開始）
    }

    private void dfs(TreeNode node, List<Integer> arr) {
        if (node == null) {
            return; // 若節點為 null，直接結束此路徑的遞迴
        }

        arr.add(node.val);      // 將當前節點的值加入 arr
        dfs(node.left, arr);    // 遞迴拜訪左子樹
        dfs(node.right, arr);   // 遞迴拜訪右子樹
    }
}