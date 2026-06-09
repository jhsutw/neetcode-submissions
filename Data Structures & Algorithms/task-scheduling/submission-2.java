// 3. Greedy
/* 核心概念：
 * 1. 找出出現次數最多的任務 maxf。
 * 2. 設定 maxf - 1 組週期，每組長度為 n（中間是冷卻時間）。(此為最少idle的情況)
 * 3. 嘗試用其他任務填入這些冷卻時間（idle）。
 * 4. 若 idle 仍為正，則總時間 = 任務總數 + idle；否則總時間 = 任務總數。
 */

public class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] count = new int[26]; // 統計每個任務出現次數

        for (char task : tasks) {
            count[task - 'A']++; // 'A' 對應 index 0, ..., 'Z' 對應 index 25
        }

        Arrays.sort(count); // 把任務出現次數從小到大排序，最大次數在 count[25]
        int maxf = count[25]; // 找出出現次數最多的任務 max frequency

        // 初始化 idle 空位數量 = (maxf - 1) 組，每組長度為 n
        int idle = (maxf - 1) * n;

        // 嘗試用其他任務填滿 idle
        for (int i = 24; i >= 0 && idle > 0; i--) {
            idle -= Math.min(count[i], maxf - 1); // 每個任務最多填 (maxf - 1) 個空位
        }

        // 如果 idle 為負代表任務已經填滿空格甚至超出（不需額外 idle）
        return Math.max(0, idle) + tasks.length; // 任務總數 + 剩餘 idle
    }
}
