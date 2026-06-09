/**
 * 複製一個無向圖 (Clone Graph)。
 * 使用廣度優先搜尋 (BFS) + 哈希表保存原節點與複製節點的對應，
 * 保證在環路存在時也不會重複複製同一個節點。
 */
public class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;                  // 空圖直接回傳 null
        }
        // 建立映射：原始節點 → 複製後的新節點
        Map<Node, Node> oldToNew = new HashMap<>();
        // 建立佇列進行 BFS
        Queue<Node> q = new LinkedList<>();

        // 先處理起點：複製該節點並入映射與佇列
        oldToNew.put(node, new Node(node.val));
        q.add(node);

        // BFS 遍歷整張圖
        while (!q.isEmpty()) {
            Node cur = q.poll();           // 取出待處理節點
            // 處理所有鄰居
            for (Node nei : cur.neighbors) {
                // 若該鄰居尚未複製過，就建立複本並加入佇列
                if (!oldToNew.containsKey(nei)) {
                    oldToNew.put(nei, new Node(nei.val));
                    q.add(nei);
                }
                // 將 cur 的複本與 nei 的複本以鄰居關係連接
                oldToNew.get(cur).neighbors.add(oldToNew.get(nei));
            }
        }
        // 回傳原始起點對應的複製圖起點
        return oldToNew.get(node);
    }
}

/*
DFS vs BFS
1. 節點訪問順序
假設原圖如下（無向連通圖）：
1──2
│  │
4──3

鄰居列表：
1: [2,4]
2: [1,3]
3: [2,4]
4: [1,3]

方法	訪問順序 (第一次見到新節點時)	建立複本順序
DFS	   1 → 2 → 3 → 4	           clone1, clone2, clone3, clone4
BFS	   1 → 2 → 4 → 3	           clone1, clone2, clone4, clone3

1. DFS 走到底（深度）再回溯，先沿著 1→2→3 的路徑把 3、4 建完，才回頭處理 4
2. BFS 按層級（廣度）訪問，先把 1 的所有鄰居（2、4）都建了、放進佇列，再依序處理

*/