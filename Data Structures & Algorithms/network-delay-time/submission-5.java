// 5. Dijkstra's Algorithm
/*
✅ 為什麼這是 Dijkstra？
利用最小堆（PriorityQueue）確保每次都取出「目前距離最短」的節點。
每個節點只訪問一次（用 visited 集合控制）。
適用於邊權為非負的圖。
*/
public class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        // 建立鄰接表：edges[u] = [[v1, w1], [v2, w2], ...]
        Map<Integer, List<int[]>> edges = new HashMap<>();
        for (int[] time : times) {
            edges.computeIfAbsent(time[0], key -> new ArrayList<>())
                 .add(new int[]{time[1], time[2]}); // time[0] -> time[1] with cost time[2]
        }

        // 最小堆（priority queue）：每個元素是 [目前累積距離, 節點編號]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        minHeap.offer(new int[]{0, k}); // 從起點 k 出發，累積時間為 0

        Set<Integer> visited = new HashSet<>(); // 紀錄已訪問過的節點
        int t = 0; // 記錄目前的最長傳遞時間

        while (!minHeap.isEmpty()) {
            int[] curr = minHeap.poll(); // 取出距離最小的節點
            int w1 = curr[0]; // 當前累積時間
            int n1 = curr[1]; // 當前節點編號

            if (visited.contains(n1)) continue; // 已訪問過就跳過

            visited.add(n1); // 標記為已訪問
            t = w1; // 更新目前最大時間（因為 minHeap 保證是最小距離來的）

            // 探訪所有相鄰節點
            if (edges.containsKey(n1)) {
                for (int[] next : edges.get(n1)) {
                    int n2 = next[0]; // 下一個節點
                    int w2 = next[1]; // 邊的權重
                    if (!visited.contains(n2)) {
                        minHeap.offer(new int[]{w1 + w2, n2}); // 加入堆中，總距離為目前時間 + 邊權重
                    }
                }
            }
        }

        // 如果有 n 個節點都訪問過，代表訊號可傳到每個點，返回花費時間；否則返回 -1
        return visited.size() == n ? t : -1;
    }
}