/**
 * 題目：Task Scheduler
 * 解法概述：
 * 使用最大堆 (Max Heap) 優先處理數量最多的任務，
 * 並用 Queue 紀錄正在冷卻（cooldown）中的任務。
 * 每個回合（time++）：
 *   - 優先執行 maxHeap 中出現次數最多的任務。
 *   - 如果該任務還沒做完，則加入 cooldown Queue（保存剩餘次數與可重新進入 heap 的時間）。
 *   - 如果 cooldown queue 有任務冷卻完畢（time 到了），則重新加回 heap。
 */

public class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] count = new int[26]; // 統計每個任務出現的次數
        for (char task : tasks) {
            count[task - 'A']++;
        }

        // Max Heap 根據任務剩餘次數排序（最多的排在最前）
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int cnt : count) {
            if (cnt > 0) {
                maxHeap.add(cnt);
            }
        }

        int time = 0; // 記錄 CPU 執行的總時間
        Queue<int[]> q = new LinkedList<>(); // Queue 裡儲存 [任務剩餘次數, 解鎖時間]（放還需要做的任務的剩餘次數跟解鎖時間，不care任務是編號）

        while (!maxHeap.isEmpty() || !q.isEmpty()) {
            time++; // 每輪時間 +1

            // 若有任務可執行
            if (!maxHeap.isEmpty()) {
                int cnt = maxHeap.poll() - 1; // 執行該任務，剩餘次數 -1
                if (cnt > 0) {
                    // 如果還有剩餘次數，加入 cooldown queue，等待 time + n 再進 heap
                    q.add(new int[]{cnt, time + n});
                }
            }

            // 若有任務 cooldown 結束，加入 heap 再排程
            if (!q.isEmpty() && q.peek()[1] == time) { // 當該輪時間等於某任務的解鎖時間，把該任務放入maxHeap等待分配）
                maxHeap.add(q.poll()[0]);
            }
        }

        return time;
    }
}