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

public class Codec {

    // 將二元樹序列化成字串（使用 BFS）
    public String serialize(TreeNode root) {
        if (root == null) return "N"; // 空樹直接回傳 "N"

        StringBuilder res = new StringBuilder(); // 儲存序列化的結果
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root); // 初始化 queue 並放入 root
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll(); // 取出當前節點
            if (node == null) {
                res.append("N,"); // 空節點記為 "N"
            } else {
                res.append(node.val).append(","); // 加入節點值
                queue.add(node.left);  // 將左右子節點加入 queue
                queue.add(node.right);
            }
        }
        return res.toString(); // 回傳逗號分隔的序列化結果
    }

    // 將序列化的字串還原成二元樹
    public TreeNode deserialize(String data) {
        String[] vals = data.split(","); // 將字串拆成陣列
        if (vals[0].equals("N")) return null; // 空樹處理 (val[0]是root)

        TreeNode root = new TreeNode(Integer.parseInt(vals[0])); // 建立根節點
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int index = 1; // 從第 1 個元素開始讀（第 0 個是 root）

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            // 建立左子節點（若不是 "N"）
            if (!vals[index].equals("N")) {
                node.left = new TreeNode(Integer.parseInt(vals[index]));
                queue.add(node.left);
            }
            index++;

            // 建立右子節點（若不是 "N"）
            if (!vals[index].equals("N")) {
                node.right = new TreeNode(Integer.parseInt(vals[index]));
                queue.add(node.right);
            }
            index++;
        }

        return root; // 回傳還原後的根節點
    }
}