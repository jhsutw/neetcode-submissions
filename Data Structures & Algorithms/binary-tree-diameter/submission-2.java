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
    public int diameterOfBinaryTree(TreeNode root) {
        // Map 用來存每個節點對應的 [高度, 直徑]，null 節點對應 [0, 0]
        Map<TreeNode, int[]> mp = new HashMap<>();
        mp.put(null, new int[]{0, 0}); // 空節點預設值
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root); // 初始推入根節點

        while (!stack.isEmpty()){
            TreeNode node = stack.peek();
            
            if (node.left != null && !mp.containsKey(node.left)){ // 先處理左子樹（模擬遞迴）
                stack.push(node.left);
            } else if (node.right != null && !mp.containsKey(node.right)){ // 再處理右子樹
                stack.push(node.right);
            } else { // 左右子樹都處理完後，處理當前節點（即後序遍歷）
                node = stack.pop(); // 彈出當前節點

                // 從 map 中取得左右子樹的 [高度, 直徑]
                int[] leftData = mp.get(node.left);
                int[] rightData = mp.get(node.right);

                int leftHeight = leftData[0], leftDiameter = leftData[1];
                int rightHeight = rightData[0], rightDiameter = rightData[1];

                // 計算目前節點的高度
                int height = 1 + Math.max(leftHeight, rightHeight);

                // 直徑有三種可能：
                // 1. 經過此節點的路徑（左高 + 右高）
                // 2. 在左子樹內部
                // 3. 在右子樹內部
                int diameter = Math.max(leftHeight + rightHeight, 
                               Math.max(leftDiameter, rightDiameter));

                // 將當前節點的 [高度, 直徑] 存進 map    
                mp.put(node, new int[]{height, diameter});
            }
        }
        // 最終回傳根節點對應的直徑值（邊的數量）
        return mp.get(root)[1];
    }
}


/*
範例：
    給定一棵二元樹：
    
            1
           / \
          2   3
         / \
        4   5

後序遍歷順序會是：4, 5, 2, 3, 1
stack push/poll 的順序會這樣：

一開始 push root → stack: [1]
看 1 的左子樹是 2，未處理 → push 2 → stack: [1, 2]
看 2 的左子樹是 4，未處理 → push 4 → stack: [1, 2, 4]
4 沒有子節點，進入 else → pop 4，處理完成
回到 2，左處理過了，看右子樹 5 → push → stack: [1, 2, 5]
5 沒有子節點，pop 處理
2 左右都處理過 → pop 處理
回到 1，左處理完 → push 右子樹 3 → stack: [1, 3]
3 無子節點 → pop 處理
1 左右都處理完 → pop 處理


處理順序如下：
1. 處理節點 4：
   - 左右子樹皆為 null ⇒ 高度 = 1, 直徑 = 0
   - mp.put(4, [1, 0])

2. 處理節點 5：
   - 左右子樹皆為 null ⇒ 高度 = 1, 直徑 = 0
   - mp.put(5, [1, 0])

3. 處理節點 2：
   - left = [1, 0], right = [1, 0]
   - 高度 = 1 + max(1, 1) = 2
   - 直徑 = max(1+1, max(0, 0)) = 2
   - mp.put(2, [2, 2])

4. 處理節點 3：
   - 左右皆為 null ⇒ 高度 = 1, 直徑 = 0
   - mp.put(3, [1, 0])

5. 處理節點 1：
   - left = [2, 2], right = [1, 0]
   - 高度 = 1 + max(2, 1) = 3
   - 直徑 = max(2+1, max(2, 0)) = 3
   - mp.put(1, [3, 3])

最後回傳根節點的直徑：mp.get(root)[1] = 3

即最長路徑為：4 → 2 → 1 → 3，共 3 條邊
*/