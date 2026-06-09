// brute force: top down
class Solution {
    // 主函式：檢查是否為平衡二元樹
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;  // 空樹視為平衡

        int left = height(root.left);   // 計算左子樹高度
        int right = height(root.right); // 計算右子樹高度

        // 若左右子樹高度差 > 1，則不是平衡樹
        if (Math.abs(left - right) > 1) return false;

        // 遞迴檢查左右子樹是否也為平衡樹
        return isBalanced(root.left) && isBalanced(root.right);
    }

    // 輔助函式：計算一棵樹的高度
    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // 高度 = 1 + 左右子樹最大高度
        return 1 + Math.max(height(root.left), height(root.right));
    }
}

/*
範例：root = [1,2,2,3,3,null,null,4,4]

        1
       / \
      2   2
     / \
    3   3
   / \
  4   4

執行步驟：
1. 從葉節點 4 計算高度為 1
2. 回傳到節點 3，高度為 2
3. 回傳到節點 2，高度為 3
4. 右子樹為 null，高度為 0 ⇒ 高度差為 3，超過 1，return false

→ 回傳：false，因為不是平衡樹
*/