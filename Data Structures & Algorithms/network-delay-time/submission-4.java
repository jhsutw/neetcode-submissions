// 4. Shortest Path Faster Algorithm
public class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        // 建立鄰接表：每個節點對應一個鄰居清單 [下一個節點, 花費時間]
        Map<Integer, List<int[]>> adj = new HashMap<>();
        for (int i = 1; i <= n; i++) adj.put(i, new ArrayList<>());
        for (int[] time : times) {
            adj.get(time[0]).add(new int[] {time[1], time[2]});
        }

        // 初始化每個節點的最短距離為無限大（代表還沒到達）
        Map<Integer, Integer> dist = new HashMap<>();
        for (int i = 1; i <= n; i++) dist.put(i, Integer.MAX_VALUE);
        dist.put(k, 0); // 起點 k 到自己的距離為 0

        // BFS queue: 每個元素是 [節點, 從起點到這個節點的最短時間]
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {k, 0}); // 從起點開始

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int node = curr[0], time = curr[1];

            // 如果當前時間大於已知最短時間，表示這條路徑較差，跳過
            if (dist.get(node) < time) continue;

            // 遍歷鄰居節點進行鬆弛操作 ()
            for (int[] nei : adj.get(node)) {
                int nextNode = nei[0], weight = nei[1];
                // 若從當前節點前往鄰居的時間比原本紀錄的更短，就更新
                if (time + weight < dist.get(nextNode)) {
                    dist.put(nextNode, time + weight); // 更新最短距離
                    q.offer(new int[] {nextNode, time + weight}); // 加入 queue 繼續擴展
                }
            }
        }

        // 最後取所有節點中最大時間作為答案（最慢到達的那個點）
        int res = Collections.max(dist.values());
        return res == Integer.MAX_VALUE ? -1 : res; // 若有無法到達的節點，回傳 -1
    }
}