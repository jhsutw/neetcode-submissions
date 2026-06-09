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

// 3. Iterative DFS
// 深度優先（stack），法一（queue）為廣度優先
public class Solution {
    public TreeNode invertTree(TreeNode root) {
        // 若是空樹，直接回傳 null
        if (root == null) return null;

        // 使用 Stack 實作 DFS（深度優先搜尋）
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root); // 先將根節點放入堆疊

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop(); // 拿出一個節點

            // 交換當前節點的左右子節點
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            // 將左右節點加入堆疊（不為 null 時）
            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
        }

        // 回傳反轉後的根節點
        return root;
    }
}

/*
範例輸入：root = [1, 2, 3, 4, 5, 6, 7]

原始樹結構：
        1
      /   \
     2     3
    / \   / \
   4   5 6   7

執行 invertTree(root) 過程如下（採用 DFS，stack 模擬）：

Step 1: stack = [1]
- 處理節點 1：交換左右 → 2 ↔ 3
- stack 加入 3、2 → stack = [3, 2]

Step 2: 處理節點 2
- 交換左右 → 4 ↔ 5
- stack 加入 5、4 → stack = [5, 4]

Step 3: 處理節點 4（葉節點）→ 無子節點，不動作

Step 4: 處理節點 5（葉節點）→ 無子節點，不動作

Step 5: 處理節點 3
- 交換左右 → 6 ↔ 7
- stack 加入 7、6 → stack = [7, 6]

Step 6~7: 處理節點 6 和 7（皆為葉節點）

最終結果樹：
        1
      /   \
     3     2
    / \   / \
   7   6 5   4

對應 level-order 輸出為：[1, 3, 2, 7, 6, 5, 4]

補充：
- 此解法為 DFS 前序遍歷實作
- 使用 Stack 可避免遞迴造成的 stack overflow
- 節點順序與傳統 BFS 不同，但效果一致
*/
