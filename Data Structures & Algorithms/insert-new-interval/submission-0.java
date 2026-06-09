class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // 因為不知道要裝多少int[]所以用List<int[]>不直接用int[][]
        List<int[]> res = new ArrayList<>();

        for (int[] interval : intervals) {
            // 1. newI 已經被加入 或 newI 大於 interval 的區間：加入interval
            if (newInterval == null || interval[1] < newInterval[0]) {
                res.add(interval);
            
            // 2. newI 小於 interval 的區間：加入newI > 加入 interval（確保順序性）
            } else if (interval[0] > newInterval[1]) {
                res.add(newInterval);
                res.add(interval);
                newInterval = null; // 把 newI 標記為 null（代表已加入，後面的 interval 就可以不用比較直接加入 res）
            // 3. newI 跟 interval 區間有重疊：取兩區間重疊後的端點
            } else {
                newInterval[0] = Math.min(interval[0], newInterval[0]);
                newInterval[1] = Math.max(interval[1], newInterval[1]);
            }

        }
        
        // 如果 newI 一直沒被加入代表他在最後面，所以跑完 loop 後直接加入
        if (newInterval != null) {res.add(newInterval);}

        // 把List<int[]>再轉回int[][]
        return res.toArray(new int[res.size()][]);
    }
}
