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
    
    // 將二元樹序列化為一個字串
    public String serialize(TreeNode root) {
        List<String> res = new ArrayList<>(); // 用來儲存序列化結果
        dfsSerialize(root, res);             // 使用 DFS 前序遍歷進行序列化
        return String.join(",", res);        // 用逗號分隔每個節點
    }

    // 使用 DFS 前序遍歷序列化二元樹
    private void dfsSerialize(TreeNode node, List<String> res) {
        if (node == null) {
            res.add("N"); // 用 "N" 表示 null 節點
            return;
        }
        res.add(String.valueOf(node.val));  // 加入當前節點的值
        dfsSerialize(node.left, res);       // 遞迴序列化左子樹
        dfsSerialize(node.right, res);      // 遞迴序列化右子樹
    }

    // 將字串反序列化為二元樹
    public TreeNode deserialize(String data) {
        String[] vals = data.split(",");  // 先將逗號分隔的字串轉回陣列
        int[] i = {0};                    // 用陣列包裝 index，作為可變參數傳遞（模擬 pass by reference），int無法在遞迴中被傳遞
        return dfsDeserialize(vals, i);   // 使用 DFS 遞迴建立樹
    }

    // 使用 DFS 遞迴還原樹結構
    private TreeNode dfsDeserialize(String[] vals, int[] i) {
        if (vals[i[0]].equals("N")) { // 若為 "N"，代表 null 節點
            i[0]++;                   // index 前進
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(vals[i[0]])); // 建立當前節點
        i[0]++;                           // index 前進
        node.left = dfsDeserialize(vals, i);   // 還原左子樹
        node.right = dfsDeserialize(vals, i);  // 還原右子樹
        return node;                            // 回傳當前節點
    }
}