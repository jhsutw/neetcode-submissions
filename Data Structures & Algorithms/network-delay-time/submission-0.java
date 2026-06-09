// 1. Depth First Search
public class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        // 建立鄰接表，key 是出發點，value 是 [目的地, 傳遞時間]
        Map<Integer, List<int[]>> adj = new HashMap<>();
        for (int[] time : times) {
            // 例如：time = [2, 3, 1] 表示從 2 傳到 3 需要 1 單位時間
            adj.computeIfAbsent(time[0], x -> new ArrayList<>())
                .add(new int[]{time[1], time[2]});
        }

        // 初始化每個節點的距離為無限大（尚未訪問）
        Map<Integer, Integer> dist = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            dist.put(i, Integer.MAX_VALUE);
        }

        // 從節點 k 開始 DFS
        dfs(k, 0, adj, dist);

        // 取最長的傳遞時間（即最晚收到訊號的節點時間），時間最長者是最後一個走到的node
        int res = Collections.max(dist.values());

        // 如果有節點還是無限大（沒收到訊號），就回傳 -1
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    // 每一條路都走走看，然後不斷迭代每個node的最小距離
    private void dfs(int node, int time,
                     Map<Integer, List<int[]>> adj,
                     Map<Integer, Integer> dist) {
        // 如果目前的時間比記錄的還慢，代表已經有更快的路徑，直接跳過
        if (time >= dist.get(node)) return;

        // 更新此節點的最短時間
        dist.put(node, time);

        // 如果沒有從該節點出發的路，就結束
        if (!adj.containsKey(node)) return;

        // 繼續對相鄰節點進行 DFS 遞迴
        for (int[] edge : adj.get(node)) {
            int nei = edge[0];     // 目的地
            int weight = edge[1];  // 邊的權重（傳遞時間）
            dfs(nei, time + weight, adj, dist);
        }
    }
}