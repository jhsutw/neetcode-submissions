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


// 2. Inorder Traversal
public class Solution {
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> arr = new ArrayList<>();
        
        dfs(root, arr);              // 透過中序遍歷收集節點值（左 → 根 → 右）
        return arr.get(k - 1);       // 回傳第 k 小的值（List index 從 0 開始）
    }

    private void dfs(TreeNode node, List<Integer> arr) {
        if (node == null) {
            return;                  // 如果節點為空，直接結束該分支遞迴
        }

        dfs(node.left, arr);         // 遞迴處理左子樹（先處理小的值）
        arr.add(node.val);           // 將目前節點值加入結果清單
        dfs(node.right, arr);        // 遞迴處理右子樹（處理較大的值）
    }
}

/*
     3
    / \
   1   4
    \
     2

中序遍歷順序為：[1, 2, 3, 4]
若 k = 2，則 arr.get(1) = 2 → 第 2 小的元素是 2 ✅
*/
