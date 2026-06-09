// Iterative DFS: stack
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
    public boolean isBalanced(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();  // 模擬遞迴呼叫
        TreeNode node = root, last = null;      // last 為上一次處理過的節點
        Map<TreeNode, Integer> depths = new HashMap<>(); // 儲存每個節點的高度

        while (!stack.isEmpty() || node != null) {
            // 模擬遞迴左移到底
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.peek();
                // 如果右子樹不存在或已處理過，就可以處理當前節點（後序）
                if (node.right == null || last == node.right) {
                    stack.pop();

                    // 取得左右子樹的高度（若為 null 則為 0）
                    int left = depths.getOrDefault(node.left, 0);
                    int right = depths.getOrDefault(node.right, 0);

                    // 若不平衡，直接回傳 false
                    if (Math.abs(left - right) > 1) return false;

                    // 計算目前節點高度，並更新到 map
                    depths.put(node, 1 + Math.max(left, right));

                    last = node;  // 更新上一次處理的節點
                    node = null;  // 返回上一層節點(不會走進if (node != null)的判斷邏輯中，所以不會往右走)
                } else {
                    // 若右子樹還沒處理，則轉去處理右子樹
                    node = node.right;
                }
            }
        }

        return true;  // 所有節點皆平衡
    }
}

/*
範例說明與註解：

假設樹的結構如下：
       1
      / \
     2   3
    /
   4

後序遍歷順序應為：4 → 2 → 3 → 1

步驟解析：
1. 使用 stack 模擬遞迴，先一路走到底把所有左節點壓入 stack。
2. 當無左節點時，觀察當前節點的右子節點：
   - 若右子節點為 null 或已處理過（記錄在 last 中），代表可以處理當前節點。
   - 否則還需處理右子樹，就轉向右節點。
3. 當處理一個節點時：
   - 取出左右子節點的高度（null 視為 0）。
   - 如果兩邊高度差 > 1，代表不平衡，直接回傳 false。
   - 否則計算當前節點高度 = 1 + max(left, right)，存入 map。
4. 最後遍歷整棵樹沒問題，就回傳 true。

時間複雜度：O(n)，每個節點最多訪問一次。
空間複雜度：O(n)，stack 最差情況下深度為樹高，map 儲存所有節點高度。

這種做法避免了重複訪問子樹（相比單純遞迴解），是更高效的做法。
*/
