// Breadth First Search (Queue)
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
        Queue<TreeNode> q1 = new LinkedList<>();
        Queue<TreeNode> q2 = new LinkedList<>();
        q1.add(p);
        q2.add(q);

        // 只要兩個 queue 都不為空就持續比對
        while (!q1.isEmpty() && !q2.isEmpty()) {
            // 每次處理目前這一層的所有節點
            for (int i = q1.size(); i > 0; i--) {
                TreeNode nodeP = q1.poll();
                TreeNode nodeQ = q2.poll();

                // 若兩個都是 null，表示對應位置皆為空，跳過
                if (nodeP == null && nodeQ == null) continue;

                // 若其中之一為 null 或值不同，則非相同樹
                if (nodeP == null || nodeQ == null || nodeP.val != nodeQ.val)
                    return false;

                // 加入左右子樹節點到 queue 中
                q1.add(nodeP.left);
                q1.add(nodeP.right);
                q2.add(nodeQ.left);
                q2.add(nodeQ.right);
            }
        }

        // 若走完都沒發現不一樣，就表示完全相同
        return true;
    }
}

/*
假設 p = [1,2,3], q = [1,2,3]

樹的結構如下：

    1             1
   / \           / \
  2   3         2   3

初始時：
q1 = [1]
q2 = [1]

第一輪：
poll() → 比較 1 vs 1 → 相同 → 加入左右節點
q1 = [2, 3], q2 = [2, 3]

第二輪：
poll() → 2 vs 2 → 相同 → 加入 null, null
poll() → 3 vs 3 → 相同 → 加入 null, null
q1 = [null, null, null, null], q2 = [null, null, null, null]

最後輪：
每對 null vs null → continue
Queue 清空 → 回傳 true
*/