// 2. Prim's Algorithm
// 這是 Prim's 演算法經典實作：先建圖，再從某點開始貪婪選最短邊擴展。
public class Solution {
    public int minCostConnectPoints(int[][] points) {
        int N = points.length;
        Map<Integer, List<int[]>> adj = new HashMap<>();

        // Step 1: 建立圖的鄰接表（adjacency list）
        // 每條邊儲存為 [距離, 鄰點索引] (每個node建立與鄰點的關係表)
        for (int i = 0; i < N; i++) {
            int x1 = points[i][0];
            int y1 = points[i][1];
            for (int j = i + 1; j < N; j++) {
                int x2 = points[j][0];
                int y2 = points[j][1];
                int dist = Math.abs(x1 - x2) + Math.abs(y1 - y2); // 曼哈頓距離
                adj.computeIfAbsent(i, k -> new ArrayList<>()).add(new int[]{dist, j});
                adj.computeIfAbsent(j, k -> new ArrayList<>()).add(new int[]{dist, i});
            }
        }

        int res = 0; // 結果累加最小成本
        Set<Integer> visit = new HashSet<>(); // 記錄已納入 MST 的節點
        PriorityQueue<int[]> minH = new PriorityQueue<>(Comparator.comparingInt(a -> a[0])); // 最小堆，按邊距離排序

        minH.offer(new int[]{0, 0}); // 從第 0 個點開始，邊的成本為 0（邊的成本, 目標節點）

        while (visit.size() < N) {
            int[] curr = minH.poll();
            int cost = curr[0]; // 當前邊的成本
            int i = curr[1];    // 當前目標節點

            if (visit.contains(i)) continue; // 若已經訪問，跳過

            res += cost; // 累加該邊的成本
            visit.add(i); // 標記此節點已納入 MST

            // 探訪相鄰邊，將尚未加入 MST 的節點加入堆中 (繼續往其他node延伸)
            for (int[] nei : adj.getOrDefault(i, Collections.emptyList())) {
                int neiCost = nei[0];
                int neiIndex = nei[1];
                if (!visit.contains(neiIndex)) {
                    minH.offer(new int[]{neiCost, neiIndex});
                }
            }
        }

        return res;
    }
}