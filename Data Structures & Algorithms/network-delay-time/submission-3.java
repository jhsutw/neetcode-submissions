// 3. Bellman Ford Algorithm
public class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        // 建立一個距離陣列，記錄從起點 k 到每個節點的最短距離
        int[] dist = new int[n];

        // 一開始將所有節點距離設為無限大，代表尚未可達
        Arrays.fill(dist, Integer.MAX_VALUE);

        // 起點到自身的距離為 0（注意：Java 陣列從 0 開始，所以是 k-1）
        dist[k - 1] = 0;

        // Bellman-Ford 核心：對所有邊進行 n-1 次鬆弛（Relaxation）
        /*
        🔁 所以為什麼要鬆弛 n-1 次？
        因為：
        第一次鬆弛：可以找到所有從起點 k 經過 1 條邊能到達的點
        第二次鬆弛：可以找到經過 2 條邊能到達的點
        ...
        第 n-1 次鬆弛：就能涵蓋最長可能的路徑長度（n-1 條邊）
        每次鬆弛都「傳遞」一次最短路徑資訊，最多只需要傳遞 n-1 次，就能更新所有最短路徑。
        */
        for (int i = 0; i < n - 1; i++) {
            for (int[] time : times) {
                int u = time[0] - 1; // 出發點（轉為 0-based index）
                int v = time[1] - 1; // 目的地（轉為 0-based index）
                int w = time[2];     // 邊的權重（傳遞時間）

                // 如果 u 可達 (起點到u的距離非inf)，且k -> u -> v距離比原本（k -> v）短，就更新
                if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        // 找出從起點出發可達節點中的最長距離（即訊號傳到最遠的節點所需時間）
        int maxDist = Arrays.stream(dist).max().getAsInt();

        // 如果有節點仍是無限大，代表無法傳到所有節點，回傳 -1
        return maxDist == Integer.MAX_VALUE ? -1 : maxDist;
    }
}