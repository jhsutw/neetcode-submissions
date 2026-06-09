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

// 3. Recursive DFS (Optimal)
public class Solution {
    public int kthSmallest(TreeNode root, int k) {
        int[] tmp = new int[2];  // tmp[0]: 剩下要找幾個；tmp[1]: 結果存放處
        tmp[0] = k;
        dfs(root, tmp);         // 進行中序遍歷
        return tmp[1];          // 回傳找到的第 k 小元素
    }

    private void dfs(TreeNode node, int[] tmp) {
        if (node == null) {
            return;             // 空節點結束遞迴
        }

        dfs(node.left, tmp);    // 遞迴進左子樹（中序遍歷：左 → 根 → 右）

        tmp[0] -= 1;            // 每拜訪一個節點，就代表找到一個更小的值
        if (tmp[0] == 0) {      // 如果已經找到第 k 小
            tmp[1] = node.val;  // 把結果存在 tmp[1]
            return;             // 結果已確定，提前結束
        }

        dfs(node.right, tmp);   // 遞迴進右子樹
    }
}

/*
     3
    / \
   1   4
    \
     2

中序遍歷順序是：[1, 2, 3, 4]
若 k = 3：
tmp[0] 起始為 3，拜訪順序如下：
訪問 1 → tmp[0] = 2
訪問 2 → tmp[0] = 1
訪問 3 → tmp[0] = 0，找到目標 tmp[1] = 3
*/