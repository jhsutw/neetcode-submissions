// two pointers
class Solution {
    public int maxProfit(int[] prices) {
        // 不是用prices兩端的值往內夾擊，而是先從隔壁的開始、再同步向右擴張
        int l = 0, r = 1;
        int maxP = 0;

        while (r < prices.length){
            // 當右邊的pirce > 左邊的price，則計算profit
            if (prices[l] < prices[r]){
                int profit = prices[r] - prices[l];
                maxP = Math.max(maxP, profit);
            // 當右邊的值小於或等於左邊的值時，更新左邊的值（低點）
            } else {
                l = r;
            }
            r++;
        }
        return maxP;
    }
}
