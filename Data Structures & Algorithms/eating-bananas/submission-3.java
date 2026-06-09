class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int l = 1; // 最小可能速度（每小時至少吃1根香蕉）
        int r = Arrays.stream(piles).max().getAsInt(); // 最大可能速度（一次吃完最大堆的香蕉）
        int res = r; // 預設答案為最大速度，之後找更小的再更新

        // 二分搜尋：在 [l, r] 範圍內找最小的可行速度 k
        while (l <= r) {
            int k = (l + r) / 2; // 嘗試的吃香蕉速度

            long totalTime = 0; // 紀錄總共需要的時間

            // 計算以速度 k 吃完所有 piles 的總時間
            for (int p : piles) {
                // Math.ceil((double)p / k) = 每堆至少要花的時間（無條件進位）
                totalTime += Math.ceil((double) p / k);
            }

            // 如果可以在 h 小時內吃完（包含剛好等於 h）
            if (h >= totalTime) {
                res = k;     // 更新答案為更小的 k
                r = k - 1;   // 嘗試繼續往更小的速度搜尋
            } else {
                l = k + 1;   // 吃不完 → 提高速度
            }
        }
        return res; // 回傳找到的最小速度
    }
}