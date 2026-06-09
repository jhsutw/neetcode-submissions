/**
 * Definition of Interval:
 * public class Interval {
 *     public int start, end;
 *     public Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

public class Solution {
    public int minMeetingRooms(List<Interval> intervals) {
        // 建立一個 time list，將每個會議的「開始時間」與「結束時間」拆成獨立事件
        // 用 (time, type) 表示：
        //   type = +1 → 有會議開始
        //   type = -1 → 有會議結束
        List<int[]> time = new ArrayList<>();
        for (Interval i : intervals) {
            time.add(new int[] { i.start, 1 });   // 會議開始時 +1
            time.add(new int[] { i.end, -1 });    // 會議結束時 -1
        }

        // 將所有事件按時間排序：
        //  1️⃣ 先依時間升序排列
        //  2️⃣ 若時間相同，先處理「結束事件 (-1)」，再處理「開始事件 (+1)」
        //      → 避免會議結束與另一個會議同時開始時，誤以為需要多一間會議室
        time.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        int res = 0;    // 紀錄所需的最大會議室數
        int count = 0;  // 當前正在進行的會議數量

        // 掃描時間線（line sweep）
        for (int[] t : time) {
            count += t[1];           // 會議開始時 +1，結束時 -1
            res = Math.max(res, count); // 更新最大同時進行的會議數
        }

        // 最大同時進行的會議數，即為所需最少會議室數量
        return res;
    }
}

