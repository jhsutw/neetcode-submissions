// 1. Dijkstra's Algorithm
// 用Dijkstra-like 演算法 + 多維距離陣列來追蹤 stops 數量
public class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int INF = Integer.MAX_VALUE;

        // 建立鄰接表（儲存航班資訊）
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] flight : flights) {
            adj[flight[0]].add(new int[]{flight[1], flight[2]}); // from -> {to, price}
        }

        // dist[i][j]：到達城市 i，使用 j 次中轉（j+1 條航段）的最小費用
        // k + 5：表示每個城市可以儲存不同中轉次數（stops）下的最小花費 (其實k + 2就夠了；k + 5是保守寫法)
        int[][] dist = new int[n][k + 5];
        for (int i = 0; i < n; i++) Arrays.fill(dist[i], INF);
        dist[src][0] = 0; // src 城市，0 次中轉，成本為 0

        // 最小堆：根據 cost 排序 → [cost, city, stops]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
            Comparator.comparingInt(a -> a[0])
        );
        minHeap.offer(new int[]{0, src, -1}); // 起點特例設定 stops = -1，使第一段航班算第 0 次中轉

        while (!minHeap.isEmpty()) {
            int[] top = minHeap.poll();
            int cst = top[0], node = top[1], stops = top[2];

            // 成功抵達目標城市
            if (node == dst) return cst;

            // 超出允許的中轉次數 或 已經用更便宜的花費到達node，跳過
            if (stops == k || dist[node][stops + 1] < cst) continue;

            // 探索所有從 node 出發的相鄰航班
            for (int[] neighbor : adj[node]) {
                int nei = neighbor[0], w = neighbor[1];
                int nextCst = cst + w;
                int nextStops = stops + 1; // 多中轉一次

                // 如果這個中轉數下（跟再多轉一次機比）有更便宜的價格 → 更新 + 推進 queue
                if (dist[nei][nextStops + 1] > nextCst) {
                    dist[nei][nextStops + 1] = nextCst;
                    minHeap.offer(new int[]{nextCst, nei, nextStops});
                }
            }
        }

        // 所有情況都無法在 k 次中轉內抵達
        return -1;
    }
}
