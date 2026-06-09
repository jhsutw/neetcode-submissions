public class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        // 先依照「起始點」排序
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        int res = 0;                       // 要移除的區間數
        int prevEnd = intervals[0][1];     // 紀錄前一個區間的結束點

        for (int i = 1; i < intervals.length; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];

            if (start >= prevEnd) {
                // 沒有重疊 → 更新 prevEnd
                prevEnd = end;
            } else {
                // 有重疊 → 移除一個區間
                res++;
                // 保留「結束時間較早的區間」，這樣能留下更多空間
                prevEnd = Math.min(end, prevEnd);
            }
        }
        return res;
    }
}
