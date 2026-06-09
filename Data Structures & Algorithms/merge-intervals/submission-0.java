class Solution {
    public int[][] merge(int[][] intervals) {

        // 建立treemap（會依照key排序的樹）
        TreeMap<Integer, Integer> map = new TreeMap<>();

        // 把端點放入 treemap 的 key 中 (1) 起點的value設為1；（2）終點的value設為-1
        for (int[] interval : intervals) {
            map.put(interval[0], map.getOrDefault(interval[0], 0) + 1);
            map.put(interval[1], map.getOrDefault(interval[1], 0) - 1);
        }

        // res 存放結果
        List<int[]> res = new ArrayList<>();

        // 目前還活化的（已經過了起點但還沒到終點）區間數量
        int have = 0;

        // interval 用來存放要放進 res 中的區間
        int[] interval = new int[2];

        for (int point : map.keySet()) {

            // 目前還沒有區間被活化，則將point設為起點
            if (have == 0) {
                interval[0] = point;
            }

            // point 持續往後推移，並計算目前還活化的區間數（新的區間被活化+n ；區間被關閉-n）  
            have += map.get(point);

            // 所有活化的區間都被關閉，則將該point設為終點
            if (have == 0) {
                interval[1] = point;
                res.add(new int[] {interval[0], interval[1]});
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
