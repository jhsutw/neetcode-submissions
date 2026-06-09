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

// 4. Kth Smallest Integer in BST
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>(); // 用來模擬遞迴過程的堆疊
        TreeNode curr = root;                  // 從根節點開始

        // 只要 stack 不空 或還有未處理的節點
        while (!stack.isEmpty() || curr != null) {

            // 一直往左走，直到碰到 null，沿路把節點壓入 stack
            while (curr != null) {
                stack.push(curr);              // 將目前節點放入堆疊
                curr = curr.left;              // 走訪左子節點
            }

            // 彈出 stack 的頂端節點（左邊到底了）
            curr = stack.pop();
            k--;                               // 每拜訪一個節點，就代表找到一個更小的值

            if (k == 0) {
                return curr.val;               // 找到第 k 小，直接回傳
            }

            // 移動到右子節點繼續中序遍歷
            curr = curr.right;
        }

        return -1; // 理論上不會走到這裡，因為 k 一定有效
    }
}

/*
     3
    / \
   1   4
    \
     2

中序遍歷：1 → 2 → 3 → 4

如果 k = 3：
第一次彈出 1 → k = 2
第二次彈出 2 → k = 1
第三次彈出 3 → ✅ k = 0，回傳 3
*/

