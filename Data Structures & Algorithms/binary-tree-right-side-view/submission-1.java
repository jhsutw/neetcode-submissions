class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>(); // 儲存每層最右邊節點的結果
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root); // 將根節點放入 queue

        while (!q.isEmpty()) {
            TreeNode rightSide = null; // 每層的最右側節點
            int qLen = q.size();       // 當前層的節點數量

            for (int i = 0; i < qLen; i++) {
                TreeNode node = q.poll(); // 彈出目前節點
                if (node != null) {
                    rightSide = node;     // 不斷更新為當前層的節點（最終會是最右的）
                    q.offer(node.left);   // 加入左子樹
                    q.offer(node.right);  // 加入右子樹 (Queue為先進先出)
                }
            }

            // 該層有節點，將最右邊的節點值加入結果中
            if (rightSide != null) {
                res.add(rightSide.val);
            }
        }

        return res;
    }
}