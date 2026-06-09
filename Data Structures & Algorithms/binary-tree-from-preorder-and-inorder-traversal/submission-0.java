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

class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 如果前序或中序為空，代表沒有節點可以建立
        if (preorder.length == 0 || inorder.length == 0) {
            return null;
        }

        // 前序遍歷的第一個值一定是根節點
        TreeNode root = new TreeNode(preorder[0]);

        // 在中序遍歷中找到根節點的位置，以此切分左右子樹
        int mid = -1;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == preorder[0]) {
                mid = i;
                break;
            }
        }

        // 左子樹的前序遍歷：從第 1 個開始，長度為 mid（因為左邊有 mid 個節點）
        int[] leftPreorder = Arrays.copyOfRange(preorder, 1, mid + 1);
        // 左子樹的中序遍歷：從開頭到 mid（不含 mid）
        int[] leftInorder = Arrays.copyOfRange(inorder, 0, mid);
        // 遞迴建立左子樹
        root.left = buildTree(leftPreorder, leftInorder);

        // 右子樹的前序遍歷：從 mid + 1 到結尾（跳過 root 和左子樹部分）
        int[] rightPreorder = Arrays.copyOfRange(preorder, mid + 1, preorder.length);
        // 右子樹的中序遍歷：從 mid + 1 到結尾
        int[] rightInorder = Arrays.copyOfRange(inorder, mid + 1, inorder.length);
        // 遞迴建立右子樹
        root.right = buildTree(rightPreorder, rightInorder);

        // 回傳根節點，整棵樹就建立完成了
        return root;
    }
}

/*
範例：
preorder = [3,9,20,15,7]
inorder  = [9,3,15,20,7]

第一層：
root = 3
→ inorder index = 1
→ leftPreorder = [9]
→ leftInorder  = [9]
→ rightPreorder = [20,15,7]
→ rightInorder  = [15,20,7]

第二層（左子樹）：
preorder = [9]
inorder  = [9]
→ root = 9
→ left/right = null

第二層（右子樹）：
preorder = [20,15,7]
inorder  = [15,20,7]
→ root = 20
→ inorder index = 1
→ leftPreorder = [15]
→ leftInorder  = [15]
→ rightPreorder = [7]
→ rightInorder  = [7]

第三層（右子樹的左子樹）：
preorder = [15]
inorder  = [15]
→ root = 15
→ left/right = null

第三層（右子樹的右子樹）：
preorder = [7]
inorder  = [7]
→ root = 7
→ left/right = null

最終建出樹的結構：
        3
       / \
      9   20
         /  \
        15   7
*/
