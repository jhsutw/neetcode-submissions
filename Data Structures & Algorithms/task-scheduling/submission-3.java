// 4. Math
/**
 * 核心邏輯：
 * 1. 統計每個任務的出現次數。
 * 2. 找出出現次數最多的任務 maxf。
 * 3. 將這些任務視為分布於 maxf - 1 個「框架」中，每個框架需要 n 個冷卻時間。
 * 4. 嘗試用其他任務填入這些冷卻時間空格 idle，最後再加回原始任務數。
 */

public class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] count = new int[26]; // 記錄每個任務 (A~Z) 的出現次數

        for (char task : tasks) {
            count[task - 'A']++; // 將字母轉成索引，統計次數
        }

        Arrays.sort(count); // 排序後，最大值在最後（count[25]）

        int maxf = count[25]; // 出現最多次的任務次數

        // idle 表示冷卻時間的總空格數：最多任務 maxf - 1 次中間 * 每次需要 n 的間隔
        int idle = (maxf - 1) * n;

        // 嘗試用其他任務填入冷卻時間空格（最多只能填入 maxf - 1 個框架）
        for (int i = 24; i >= 0; i--) {
            idle -= Math.min(count[i], maxf - 1);
        }

        // 如果 idle 仍大於 0，就要加上這些空格；否則就代表所有空格已填滿，僅需 tasks.length 時間
        return Math.max(0, idle) + tasks.length;
    }
}