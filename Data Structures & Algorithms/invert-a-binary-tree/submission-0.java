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
    public TreeNode invertTree(TreeNode root) {
        // 如果是空樹，直接回傳 null
        if (root == null) {
            return null;
        }

        // 使用佇列來做層序遍歷（BFS）
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root); // 將根節點放入佇列中

        // 開始 BFS 迴圈
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll(); // 取出當前節點

            // 交換左右子節點（1 左右節點是2, 3）
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            // 如果左子節點不為空，加入佇列
            if (node.left != null) {
                queue.offer(node.left);
            }

            // 如果右子節點不為空，加入佇列
            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        // 最後回傳翻轉後的根節點
        return root;
    }
}

/*
範例：root = [1,2,3,4,5,6,7]

原始二元樹結構如下：

        1
      /   \
     2     3
    / \   / \
   4   5 6   7

執行 invertTree(root) 後的 BFS 操作流程：

Step 1: 處理節點 1
- 左右子節點 2 和 3 對調
- queue 加入 3 和 2

        1
      /   \
     3     2
    / \   / \
   6   7 4   5

Step 2: 處理節點 3
- 左右子節點 6 和 7 對調
- queue 加入 7 和 6

Step 3: 處理節點 2
- 左右子節點 4 和 5 對調
- queue 加入 5 和 4

Step 4~7: 處理節點 7, 6, 5, 4
- 都是葉節點，無需操作

最終翻轉後的樹為：

        1
      /   \
     3     2
    / \   / \
   7   6 5   4

其 level-order 為：[1, 3, 2, 7, 6, 5, 4]
*/