class Solution {
    public boolean canAttendMeetings(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) return true;

        // 按照會議開始時間排序
        Collections.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));

        int prevEnd = intervals.get(0).end;

        for (int i = 1; i < intervals.size(); i++) {
            int start = intervals.get(i).start;
            int end = intervals.get(i).end;

            // 如果下一場會議開始時間 < 上一場會議結束時間 → 有衝突
            if (start < prevEnd) {
                return false;
            }

            prevEnd = end;
        }
        return true;
    }
}