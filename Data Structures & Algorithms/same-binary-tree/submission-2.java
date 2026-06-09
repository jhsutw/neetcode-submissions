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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // 使用 stack 存放成對的節點（p, q）一開始先放root節點
        Stack<TreeNode[]> stack = new Stack<>();
        stack.push(new TreeNode[]{p, q});

        while (!stack.isEmpty()) {
            TreeNode[] nodes = stack.pop();
            TreeNode node1 = nodes[0], node2 = nodes[1];

            // 若兩節點皆為 null，代表目前比對的子樹都結束了，跳過繼續
            if (node1 == null && node2 == null) continue;

            // 若其中一個為 null，或值不同 → 樹不同，直接回傳 false
            if (node1 == null || node2 == null || node1.val != node2.val) {
                return false;
            }

            // 將右子節點對推入 stack（right先放入stack，所以會晚被pop出來）
            stack.push(new TreeNode[]{node1.right, node2.right});
            // 將左子節點對推入 stack（right先放入stack，所以會先被pop出來）
            stack.push(new TreeNode[]{node1.left, node2.left});
        }

        // 所有節點都一樣 → 回傳 true
        return true;
    }
}

/*
p = [1,2,3], q = [1,2,3]

兩棵樹結構如下：

    1                1
   / \              / \
  2   3            2   3

執行流程（Stack 行為）：
stack.push([1,1])
pop → 比較 1 vs 1 → 相同 → push([3,3]), push([2,2])
pop → 比較 2 vs 2 → 相同 → push([null,null]), push([null,null])
pop → 比較 null vs null → continue
pop → 比較 null vs null → continue
pop → 比較 3 vs 3 → 相同 → push([null,null]), push([null,null])
pop → 比較 null vs null → continue
pop → 比較 null vs null → continue
stack 空 → 回傳 true ✅
*/