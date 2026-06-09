// 1. Depth First Search
public class Solution {
    public int countComponents(int n, int[][] edges) {
        // 1. 建立鄰接表：用 List<List<Integer>> 表示無向圖
        List<List<Integer>> adj = new ArrayList<>();
        // 2. 訪問標記陣列：記錄每個節點是否已被 DFS 過
        boolean[] visit = new boolean[n];

        // 初始化鄰接表
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        // 將每條邊雙向加入鄰接表
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int res = 0;  // 用於計數連通分量（有幾棵樹）
        // 遍歷所有節點，對每個未訪問過的節點啟動 DFS
        for (int node = 0; node < n; node++) {
            if (!visit[node]) {
                dfs(adj, visit, node);  // 深度優先搜尋，標記整個分量
                res++;                   // 完成一次搜尋，分量數 +1 (有連通的節點會放在同個計數中)
            }
        }
        return res;
    }

    // 深度優先搜尋：從 node 出發，遞迴訪問所有可到達的節點
    private void dfs(List<List<Integer>> adj, boolean[] visit, int node) {
        visit[node] = true;  // 標記當前節點已訪問
        // 遍歷所有鄰居
        for (int nei : adj.get(node)) {
            if (!visit[nei]) {
                dfs(adj, visit, nei);  // 遞迴訪問尚未訪問的鄰居
            }
        }
    }
}
