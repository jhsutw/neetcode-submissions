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
    public int maxDepth(TreeNode root) {
        // 建立一個 queue 來做 BFS（層序遍歷）
        Queue<TreeNode> q = new LinkedList<>();

        // 如果 root 不為空，就先加進 queue
        if (root != null) {
            q.add(root);
        }

        int level = 0; // 紀錄當前處理到第幾層（最終就是最大深度）

        // 當 queue 不為空時，表示還有節點未處理
        while (!q.isEmpty()) {
            int size = q.size(); // 取得目前這層的節點數量（上一層會把下一層所有節點放進queue，因此q存的是每一層的所有值）

            // 處理當層所有節點
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll(); // 拿出節點

                // 把子節點加進下一層的 queue
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }

            // 每處理完一層，level +1
            level++;
        }

        // 最終 level 就是最大深度
        return level;
    }
}

/*
範例輸入：root = [1, 2, 3, 4, null, null, 5]

對應樹結構為：
        1
       / \
      2   3
     /     \
    4       5

執行流程說明（BFS 層序遍歷）：

Step 1: queue = [1]
- 處理節點 1，加進 2 和 3
- level = 1

Step 2: queue = [2, 3]
- 處理節點 2：加進 4
- 處理節點 3：加進 5
- level = 2

Step 3: queue = [4, 5]
- 處理節點 4（無子節點）
- 處理節點 5（無子節點）
- level = 3

Step 4: queue 為空 → 結束，最大深度為 3

補充：
- 時間複雜度 O(n)，n 為節點數
- 空間複雜度 O(w)，w 為最寬一層的節點數（queue 最多會存這麼多節點）
- 適合找「最淺路徑」或「最大深度」，可避免遞迴造成 stack overflow
*/