// 2. Bellman Ford Algorithm

public class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] prices = new int[n]; // 紀錄每個城市的最小票價
        Arrays.fill(prices, Integer.MAX_VALUE);
        prices[src] = 0; // 出發城市的票價是 0

        // 進行 k+1 輪鬆弛操作（最多走 k+1 條航段 = k 次中轉）
        for (int i = 0; i <= k; i++) {
            int[] tmpPrices = Arrays.copyOf(prices, n); // 使用暫存 array，避免同一輪更新相互干擾

            // 遍歷所有航班進行鬆弛操作
            for (int[] flight : flights) {
                int s = flight[0]; // 起點
                int d = flight[1]; // 終點
                int p = flight[2]; // 價格

                // 如果出發點不可達，就跳過
                if (prices[s] == Integer.MAX_VALUE) {
                    continue;
                }

                // 嘗試鬆弛：看是否能用更便宜的票價到達 d
                if (prices[s] + p < tmpPrices[d]) {
                    tmpPrices[d] = prices[s] + p;
                }
            }

            prices = tmpPrices; // 更新票價陣列，進入下一輪
        }

        return prices[dst] == Integer.MAX_VALUE ? -1 : prices[dst]; // 無法抵達則回傳 -1；可以抵達傳終點票價
    }
}