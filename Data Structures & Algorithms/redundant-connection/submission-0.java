public class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        // 1. 建立節點編號 1…n 的鄰接表
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());  // adj.get(i) 儲存與 i 相連的所有節點
        }

        // 2. 依序「插入」每條邊，並在插入後做一次 DFS 檢測環
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            // 將新邊加入無向圖
            adj.get(u).add(v);
            adj.get(v).add(u);

            // 每插入一次就重置訪問陣列，用於檢測當前圖是否含環
            boolean[] visit = new boolean[n + 1];

            // 從 u 開始做 DFS，如果能「重新訪問」到已走過的節點（return false），表示形成環
            if (dfs(u, -1, adj, visit)) {
                // 一旦檢測到環路，當前這條 edge 就是多餘邊，直接回傳
                return edge;
            }
        }

        // 理論上不會到這，因為題目保證一定有一條多餘的邊
        return new int[0];
    }

    // DFS 環路檢測：node=目前節點，parent=從哪個節點來的
    private boolean dfs(int node, int parent, List<List<Integer>> adj, boolean[] visit) {
        // 若節點已被標記，代表 DFS 過程中又回到它 → 有環
        if (visit[node]) {
            return true;
        }
        visit[node] = true;  // 標記當前節點已訪問

        // 遍歷所有相鄰節點
        for (int nei : adj.get(node)) {
            // 跳過「回到父節點」那條邊，避免誤判
            if (nei == parent) {
                continue;
            }
            // 遞迴檢測，只要子呼叫回傳 true，一路往下走
            if (dfs(nei, node, adj, visit)) {
                return true;
            }
        }
        // 無任何鄰居引發環路
        return false;
    }
}