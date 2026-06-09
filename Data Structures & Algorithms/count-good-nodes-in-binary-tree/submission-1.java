public class Solution {
    public int goodNodes(TreeNode root) {
        int res = 0; // good nodes 的總數
        Queue<Pair<TreeNode, Integer>> q = new LinkedList<>();

        // 初始化 queue，從 root 開始，起始最大值為最小整數（保證 root 是 good node）
        q.offer(new Pair<>(root, Integer.MIN_VALUE));

        while (!q.isEmpty()) {
            // 取出當前節點與這條路徑的最大值
            Pair<TreeNode, Integer> pair = q.poll();
            TreeNode node = pair.getKey();
            int maxval = pair.getValue();

            // 如果當前節點比路徑上所有節點都大或一樣大，就是 good node
            if (node.val >= maxval) {
                res++;
            }

            // 更新最大值：從 root 到這個節點的最大值
            int newMax = Math.max(maxval, node.val);

            // 加入左子節點（如果存在），並帶入目前這條路徑上的最大值
            if (node.left != null) {
                q.offer(new Pair<>(node.left, newMax));
            }

            // 加入右子節點（如果存在）
            if (node.right != null) {
                q.offer(new Pair<>(node.right, newMax));
            }
        }

        return res;
    }
}

/*
      3
     / \
    1   4
   /   / \
  3   1   5

這個 BFS 程式會走訪順序：
3 → 1 → 4 → 3 → 1 → 5

對應的 max 值變化是：
root: 3 → good ✅
1：max=3 → ❌
4：max=3 → good ✅
3（左子節點的左）：max=3 → ✅
1（右子）：max=4 → ❌
5：max=4 → ✅
*/