// 3. Topological Sort (Kahn's Algorithm)
public class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;  
        // 1. 建立度數陣列與鄰接表（節點編號 1…n）
        int[] indegree = new int[n + 1];
        List<List<Integer>> adj = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());  // 每個節點都有一個鄰居列表
        }
        // 2. 讀入所有邊，構建無向圖並計算每個節點的度
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
            indegree[u]++;
            indegree[v]++;
        }

        // 3. 將所有「葉節點」（度 = 1）入隊，準備依序刪除
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 1) {
                q.offer(i);
            }
        }

        // 4. 不斷從隊列中取出葉節點「削除」，直到只剩下環上的節點
        while (!q.isEmpty()) {
            int node = q.poll();
            indegree[node]--;  // 將該葉節點標記為已刪除（度減為 0）
            // 對每個相鄰節點度數減 1，若變成葉節點就加入隊列
            for (int nei : adj.get(node)) {
                indegree[nei]--;
                if (indegree[nei] == 1) {
                    q.offer(nei);
                }
            }
        }

        // 5. 反向遍歷原始邊陣列，找到「最後一條」連在環上兩個節點的邊
        for (int i = edges.length - 1; i >= 0; i--) {
            int u = edges[i][0], v = edges[i][1];
            // 環上節點在剝葉後度數仍 ≥ 2，葉節點度數已被削為 0 或 1（因為環中不會有indegree < 2的節點）
            if (indegree[u] >= 2 && indegree[v] >= 2) {
                return new int[]{u, v};
            }
        }
        return new int[0];  // 理論上不會到這
    }
}