// 2. Breadth First Search
public class Solution {
    public boolean validTree(int n, int[][] edges) {
        // 1. 若邊數 > n-1，必有迴路（樹最多只有 n-1 條邊）
        if (edges.length > n - 1) {
            return false;
        }

        // 2. 建立無向圖的鄰接表
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());      // 初始化每個節點的鄰居列表
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);   // edge[0] ↔ edge[1]
            adj.get(edge[1]).add(edge[0]);
        }

        // 3. 用 BFS 檢測「環路」與「連通性」
        Set<Integer> visit = new HashSet<>();
        Queue<int[]> q = new LinkedList<>();
        // 從節點 0 開始，parent 設為 -1（無父節點）
        q.offer(new int[]{0, -1});
        visit.add(0);

        while (!q.isEmpty()) {
            int[] pair = q.poll();
            int node   = pair[0];
            int parent = pair[1];

            for (int nei : adj.get(node)) {
                // 忽略回到父節點的那條邊
                if (nei == parent) {
                    continue;
                }
                // 若遇到已訪問的鄰居，代表形成迴路
                if (visit.contains(nei)) {
                    return false;
                }
                // 標記並繼續向外擴散
                visit.add(nei);
                q.offer(new int[]{nei, node});
            }
        }

        // 4. 若所有節點都被訪問，則圖是「連通且無迴路」的樹
        return visit.size() == n;
    }
}