// Depth First Search: bottom up 
class Solution {
    
    // 主函式：回傳 true 表示為平衡樹
    public boolean isBalanced(TreeNode root) {
        return dfs(root)[0] == 1;
    }

    // dfs 回傳值為 int[]: 
    // - 第 0 個元素為是否平衡 (1 表示平衡，0 表示不平衡)
    // - 第 1 個元素為當前節點的高度
    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[]{1, 0};  // 空節點：平衡，高度為 0
        }

        int[] left = dfs(root.left);   // 拿到左子樹的 [平衡與高度]
        int[] right = dfs(root.right); // 拿到右子樹的 [平衡與高度]

        // 如果左右子樹都平衡，且高度差 ≤ 1，才算當前節點也平衡
        boolean balanced = (left[0] == 1 && right[0] == 1) && 
                           (Math.abs(left[1] - right[1]) <= 1);

        // 高度 = 1 + max(左高, 右高)
        int height = 1 + Math.max(left[1], right[1]);

        // 回傳目前節點的平衡狀態與高度
        return new int[]{balanced ? 1 : 0, height};
    }
}
/*
範例一：root = [3,9,20,null,null,15,7]

        3
       / \
      9  20
         / \
        15  7

- 所有節點的左右子樹高度差都 ≤ 1，且其子樹也都平衡 ⇒ 回傳 true

範例二：root = [1,2,2,3,3,null,null,4,4]

        1
       / \
      2   2
     / \
    3   3
   / \
  4   4

- 節點 2（左側）左子樹高度為 2、右為 0，差距為 2，超過 1 ⇒ 回傳 false
*/