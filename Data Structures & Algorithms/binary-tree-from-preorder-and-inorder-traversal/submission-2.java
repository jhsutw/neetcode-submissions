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

// 3. Depth First Search (Optimal)
public class Solution {
    int preIdx = 0; // 指向目前正在處理的 preorder 陣列索引
    int inIdx = 0;  // 指向目前正在處理的 inorder 陣列索引

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return dfs(preorder, inorder, Integer.MAX_VALUE); // 初始呼叫，限制值為最大整數，代表整棵樹都可以建
    }

    private TreeNode dfs(int[] preorder, int[] inorder, int limit) {
        if (preIdx >= preorder.length) return null; // 如果 preorder 已經處理完，代表樹已經建完，回傳 null

        if (inorder[inIdx] == limit) {  // 如果 inorder[inIdx] == 限制值，表示這個 subtree 範圍結束了
            inIdx++; // inorder 指標往前，準備回上一層處理右子樹
            return null; // 回傳 null，代表這邊沒有子節點
        }

        TreeNode root = new TreeNode(preorder[preIdx++]); // 從 preorder 中取出當前節點作為 root，preIdx 自動往後移
        root.left = dfs(preorder, inorder, root.val);     // 建立左子樹，左子樹的限制值是 root 的值
        root.right = dfs(preorder, inorder, limit);       // 建立右子樹，右子樹使用原本傳進來的限制值
        return root; // 回傳當前建立好的子樹
    }
}

/*
範例輸入：
preorder = [3,9,20,15,7]
inorder  = [9,3,15,20,7]

流程解析如下（每次呼叫 dfs 都標出 preIdx, inIdx, limit）：

1. 初始呼叫 dfs(limit=∞)
   - preorder[0] = 3 → 建立 root = 3，preIdx++
   - inorder[inIdx] = 9 ≠ 3 → 繼續建左子樹，limit=3

2. dfs(limit=3)
   - preorder[1] = 9 → 建立 root = 9，preIdx++
   - inorder[inIdx] = 9 == limit → 下一層會 return null（左子樹空）
   - inorder[inIdx] = 9 == limit → 再 return null（右子樹空）
   → 結束建構 root 9，接回到 root 3 左子樹

3. 回到 root=3，建右子樹 dfs(limit=∞)
   - preorder[2] = 20 → 建立 root = 20，preIdx++
   - inorder[inIdx] = 3 == limit ❌，繼續建左子樹，limit=20

4. dfs(limit=20)
   - preorder[3] = 15 → 建立 root = 15，preIdx++
   - inorder[inIdx] = 15 == limit → return null（左）
   - inorder[inIdx] = 15 == limit → return null（右）
   → 結束建構 root 15，作為 root 20 的左子樹

5. 回到 root=20，建右子樹 dfs(limit=∞)
   - preorder[4] = 7 → 建立 root = 7，preIdx++
   - inorder[inIdx] = 20 == limit ❌，建左 dfs(limit=7) → return null
   - inorder[inIdx] = 7 == limit → return null
   → 結束建構 root 7，作為 root 20 的右子樹

整棵樹構造完成如下：

        3
       / \
      9   20
         /  \
        15   7

*/