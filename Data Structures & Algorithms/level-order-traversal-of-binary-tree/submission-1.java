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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>(); // 儲存每一層的結果

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);  // 將根節點放入佇列中

        // 每次把一層的node加入res中
        while (!q.isEmpty()) {
            List<Integer> level = new ArrayList<>(); // 儲存當前層的節點值

            // 使用 for 迴圈一次處理「一整層」的節點（把當下那層的queue加入level中，然後把node.left/node.right加入下一層的queue中）
            for (int i = q.size(); i > 0; i--) {
                TreeNode node = q.poll(); // 取出佇列前端的節點
                if (node != null) {
                    level.add(node.val);      // 加入當層的結果中
                    q.add(node.left);         // 將左子節點加入佇列
                    q.add(node.right);        // 將右子節點加入佇列
                }
            }

            // 如果這層有節點才加入結果（排除整層為 null 的情況）
            if (!level.isEmpty()) {
                res.add(level);
            }
        }

        return res;
    }
}

/*
     1
    / \
   2   3
  /     \
 4       5

q = [1]
level = [1] → res = [[1]]
q = [2, 3]
level = [2, 3] → res = [[1], [2, 3]]
q = [4, null, null, 5]
level = [4, 5] → res = [[1], [2, 3], [4, 5]]
q = [null, null, null, null] → 全是 null，不會進入 level → 結束
*/
