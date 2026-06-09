// 2. Breadth First Search
public class Solution {
    public int countComponents(int n, int[][] edges) {
        // 1. 初始化鄰接表與訪問陣列
        List<List<Integer>> adj = new ArrayList<>();
        boolean[] visit = new boolean[n];
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());  // 為每個節點建立空鄰接串列
        }
        // 2. 根據 edges 建立無向圖
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            adj.get(u).add(v);           // u ↔ v
            adj.get(v).add(u);
        }

        int res = 0;                     // 連通分量計數器
        // 3. 對每個節點做 BFS，若未訪問則代表新的分量
        for (int node = 0; node < n; node++) {
            if (!visit[node]) {
                bfs(adj, visit, node);  // 掃描整個連通分量
                res++;                  // 分量數 +1
            }
        }
        return res;
    }

    private void bfs(List<List<Integer>> adj, boolean[] visit, int node) {
        // 4. BFS：從起始節點開始，將可達的所有節點標記為已訪問
        Queue<Integer> q = new LinkedList<>();
        q.offer(node);                // 起始節點入隊
        visit[node] = true;           // 標記已訪問

        while (!q.isEmpty()) {
            int cur = q.poll();       // 取出隊首節點
            for (int nei : adj.get(cur)) {
                if (!visit[nei]) {
                    visit[nei] = true;  // 標記鄰居已訪問
                    q.offer(nei);      // 鄰居入隊繼續掃描
                }
            }
        }
    }
}