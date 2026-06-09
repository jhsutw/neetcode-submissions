// dynamic Programming
// 解題方式類似於two pointers解法，只是寫法不同
class Solution {
    public int maxProfit(int[] prices) {
        int maxP = 0;
        int minBuy = prices[0];

        for (int sell : prices){
            // 每次計算賣出（sell）-買進價格（minBuy）更新maxP
            maxP = Math.max(maxP, sell - minBuy);
            // 若賣出價比minBuy小，就更新sell為賣出價
            minBuy = Math.min(minBuy, sell);
        }
        return maxP;
    }
}
