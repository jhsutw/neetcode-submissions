// 1. Brute Force
/*
 * ✅ 作法概述：
 * 1. 計算每個任務的出現次數，儲存在 `arr` 中。
 * 2. 用一個 `processed` 列表記錄每個時間單位執行的任務代號（或 idle = -1）。
 * 3. 每個時間點從還沒做完的任務中，選出「目前可執行、且剩餘次數最多」的任務執行。
 * 4. 若沒有任務可執行，就 idle 一次。
 * 5. 直到所有任務都做完為止，最後回傳總時間 `time`。
 */

public class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] count = new int[26];  // 紀錄每個任務出現的次數（用 A~Z 映射到 index 0~25）
        for (char task : tasks) {
            count[task - 'A']++;   // 統計每個任務的數量
        }

        // 將出現過的任務加入 list，存成 [任務剩餘次數, 任務代號 (0~25)]
        List<int[]> arr = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                arr.add(new int[]{count[i], i});
            }
        }

        int time = 0;                      // 計算總共用了幾個時間單位
        List<Integer> processed = new ArrayList<>();  // 紀錄每一個時間單位執行的任務代號

        while (!arr.isEmpty()) {          // 還有任務沒完成
            int maxi = -1;                // 用來找到這一輪要執行的任務 index（初始化為沒找到）

            // 從目前任務清單中，挑選 cooldown 沒限制（閒置時間限制內沒被重複執行）、而且剩餘次數最多的任務
            for (int i = 0; i < arr.size(); i++) {
                boolean canUse = true;

                // 從 (time - n) ~ (time - 1) 檢查是否曾經執行過同一任務
                for (int j = Math.max(0, time - n); j < time; j++) {
                    if (j < processed.size() && processed.get(j) == arr.get(i)[1]) { // 往前找是否在閒置時間限制（n）下有相同的任務被執行
                        canUse = false;  // 遇到 cooldown 過程中有執行過相同任務，就return false（還不能被執行）
                        break;
                    }
                }

                if (!canUse) continue;  // cooldown 沒過就跳過

                // 如果沒選任務 or 現在這個任務剩下的次數比較多，就換成這個任務
                if (maxi == -1 || arr.get(maxi)[0] < arr.get(i)[0]) {
                    maxi = i;
                }
            }

            time++;         // 無論有沒有執行任務，都會經過 1 單位時間
            int current = -1;  // -1 代表這個時間點是 idle

            if (maxi != -1) {
                current = arr.get(maxi)[1];  // 要執行的任務代號
                arr.get(maxi)[0]--;          // 該任務剩餘次數減 1

                if (arr.get(maxi)[0] == 0) {
                    arr.remove(maxi);        // 如果任務做完了就移除
                }
            }

            processed.add(current);   // 紀錄這個時間點做了什麼（任務代號或 -1 代表 idle）
        }

        return time;  // 全部任務完成所需的總時間
    }
}