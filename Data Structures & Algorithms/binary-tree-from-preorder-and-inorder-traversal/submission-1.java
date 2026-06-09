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
    int pre_idx = 0;  // 用來追蹤目前在 preorder 中的位置
    HashMap<Integer, Integer> indices = new HashMap<>();  // 快速查詢 inorder 中某個值的 index

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 將 inorder 中每個值對應到它的 index（O(1) 查詢）
        for (int i = 0; i < inorder.length; i++) {
            indices.put(inorder[i], i);
        }
        // 呼叫遞迴函數：建構整棵樹
        return dfs(preorder, 0, inorder.length - 1);
    }

    // 遞迴建樹：l, r 是 inorder 的範圍（左右邊界）
    private TreeNode dfs(int[] preorder, int l, int r) {
        if (l > r) return null;  // 沒有節點可建，回傳 null（遞迴終止）

        int root_val = preorder[pre_idx++];  // 拿下一個 preorder 的值當作根節點
        TreeNode root = new TreeNode(root_val);  // 建立根節點
        int mid = indices.get(root_val);  // 在 inorder 中找出該值的位置

        // 依照 inorder 的位置劃分左右子樹
        root.left = dfs(preorder, l, mid - 1);     // 左子樹範圍：l 到 mid - 1
        root.right = dfs(preorder, mid + 1, r);    // 右子樹範圍：mid + 1 到 r

        return root;  // 回傳根節點
    }
}

/*
範例測資：
preorder = [3,9,20,15,7]
inorder  = [9,3,15,20,7]

流程：

1. 初始化 indices: {9:0, 3:1, 15:2, 20:3, 7:4}
2. pre_idx = 0，preorder[0] = 3 → root = 3，mid = 1
   → 左子樹 inorder[0:0] → dfs(preorder, 0, 0)
   → 右子樹 inorder[2:4] → dfs(preorder, 2, 4)

3. dfs(preorder, 0, 0): preorder[1] = 9 → root = 9，mid = 0
   → 左子樹 dfs(preorder, 0, -1) → return null
   → 右子樹 dfs(preorder, 1, 0) → return null

4. dfs(preorder, 2, 4): preorder[2] = 20 → root = 20，mid = 3
   → 左子樹 dfs(preorder, 2, 2) → preorder[3] = 15 → root = 15，mid = 2
       → 左/右都是空 → 回傳 15
   → 右子樹 dfs(preorder, 4, 4) → preorder[4] = 7 → root = 7，mid = 4
       → 左/右都是空 → 回傳 7

最終樹：
        3
       / \
      9   20
         /  \
        15   7
*/
