/**
 * 複製一個無向圖 (Clone Graph)。
 * 使用深度優先搜尋 (DFS) + 哈希表保存原節點與複製節點的對應，
 * 可正確處理環路並避免重複創建節點。
 */
public class Solution {
    public Node cloneGraph(Node node) {
        // 建立映射：原節點 → 複製後的新節點
        Map<Node, Node> oldToNew = new HashMap<>();
        // 從入口節點開始遞迴複製整張圖
        return dfs(node, oldToNew);
    }

    private Node dfs(Node node, Map<Node, Node> oldToNew) {
        if (node == null) {
            return null;                   // 空圖直接回傳 null
        }
        if (oldToNew.containsKey(node)) {
            return oldToNew.get(node);    // 若已複製過，直接回傳對應的複本
        }

        // 尚未複製：先根據 val 建立新節點
        Node copy = new Node(node.val);
        // 記錄映射，避免重複複製同一節點
        oldToNew.put(node, copy);

        // 遞迴複製所有鄰居並加入 copy 的鄰居列表
        for (Node nei : node.neighbors) {
            copy.neighbors.add(dfs(nei, oldToNew)); // 把node的相鄰節點變成copy的相鄰節點（已經建了就mapping，還沒就創建）
        }

        return copy;                      // 回傳此節點的複本
    }
}

// 執行流程示例：
// 假設原圖如下（無向連通圖）：
//   1──2
//   │  │
//   4──3
// 節點建立：
// Node n1 = new Node(1);
// Node n2 = new Node(2);
// Node n3 = new Node(3);
// Node n4 = new Node(4);
// n1.neighbors = [n2, n4];
// n2.neighbors = [n1, n3];
// n3.neighbors = [n2, n4];
// n4.neighbors = [n1, n3];
//
// 呼叫 cloneGraph(n1)：
// 1. dfs(n1)：建立 copy1 (val=1)，加入 map。
// 2. 遍歷 n1.neighbors：
//    - dfs(n2)：建立 copy2，加入 map。
//      • dfs(n1) 已在 map，回傳 copy1。
//      • dfs(n3)：建立 copy3，加入 map。
//        ◦ dfs(n2) 回傳 copy2。
//        ◦ dfs(n4)：建立 copy4，加入 map。
//          · dfs(n1) 回傳 copy1。
//          · dfs(n3) 回傳 copy3。
//        ◦ copy4.neighbors = [copy1, copy3]。
//      • copy3.neighbors = [copy2, copy4]。
//    • copy2.neighbors = [copy1, copy3]。
// 3. dfs(n4)（從 n1.neighbors 的第二個 n4 觸發）：
//    - 已在 map，直接回傳 copy4。
// 4. copy1.neighbors = [copy2, copy4]。
//
// 最終回傳 copy1，整張圖所有節點皆為新的物件，結構與原圖完全相同。```