// 3. Shortest Path Faster Algorithm
public class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // 初始化每個城市的最小價格，預設為無限大
        int[] prices = new int[n];
        Arrays.fill(prices, Integer.MAX_VALUE);
        prices[src] = 0; // 出發地成本設為 0

        // 建立鄰接表，儲存每個城市的出發航班 (目的地, 價格)
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (var flight : flights) {
            adj[flight[0]].add(new int[] { flight[1], flight[2] });
        }

        // BFS queue：每個元素為 {目前花費, 當前城市, 已中轉次數}
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { 0, src, 0 }); // 一開始在 src，花費為 0，中轉次數為 0

        while (!q.isEmpty()) {
            var curr = q.poll();
            int cst = curr[0];    // 目前總花費
            int node = curr[1];   // 當前城市
            int stops = curr[2];  // 已中轉次數

            // 如果中轉次數超過限制，跳過
            if (stops > k) continue;

            // 遍歷所有從當前城市出發的航班
            for (var neighbor : adj[node]) {
                int nei = neighbor[0]; // 目的地城市
                int w = neighbor[1];   // 飛行花費
                int nextCost = cst + w;

                // 如果找到更便宜的方式抵達 nei，則更新並加入 queue
                if (nextCost < prices[nei]) {
                    prices[nei] = nextCost;
                    q.offer(new int[] { nextCost, nei, stops + 1 });
                }
            }
        }

        // 最終判斷是否有可行路徑
        return prices[dst] == Integer.MAX_VALUE ? -1 : prices[dst];
    }
}