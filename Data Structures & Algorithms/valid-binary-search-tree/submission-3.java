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
    /**
     * 使用 BFS + 範圍上下限檢查每個節點是否滿足 BST 規則
     * 節點值必須落在 (left, right) 範圍之內，這個範圍會根據父節點更新
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true; // 空樹為合法 BST
        }

        // queue 中每個元素都是 Object[]: [節點, 左邊界, 右邊界]
        Queue<Object[]> queue = new LinkedList<>();

        // 初始把根節點放進 queue，合法範圍是 (-∞, +∞)
        queue.offer(new Object[]{root, Long.MIN_VALUE, Long.MAX_VALUE});

        while (!queue.isEmpty()) {
            Object[] current = queue.poll(); // Object[]是物件陣列，可以包住三個不同型別的值（TreeNode, long left, long right)
            TreeNode node = (TreeNode) current[0];
            long left = (long) current[1];   // 節點值必須 > left
            long right = (long) current[2];  // 節點值必須 < right

            // 如果當前節點值不在合法範圍內，則不是 BST
            if (!(left < node.val && node.val < right)) {
                return false;
            }

            // 對左子節點：合法範圍變成 (left, node.val)
            if (node.left != null) {
                queue.offer(new Object[]{node.left, left, (long) node.val});
            }

            // 對右子節點：合法範圍變成 (node.val, right)
            if (node.right != null) {
                queue.offer(new Object[]{node.right, (long) node.val, right});
            }
        }

        return true; // 所有節點都符合 BST 規則
    }
}
