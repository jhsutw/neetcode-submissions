public class Solution {
    public int[] minInterval(int[][] intervals, int[] queries) {
        // 1️⃣ 先依照區間的起點（start）進行排序
        //    因為我們要按照查詢點 q 逐一遍歷區間，排序可讓我們線性處理
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        // 2️⃣ 使用最小堆（minHeap），每個元素為 [區間長度, 區間右端點]
        //    目的是：在所有「覆蓋當前 query」的區間中，快速取出最短的那個
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        // 3️⃣ 用來記錄每個 query 對應的最小區間長度結果
        Map<Integer, Integer> res = new HashMap<>();

        int i = 0; // 指標，用來遍歷 intervals

        // 4️⃣ 對 queries 進行排序，確保我們從小到大依序處理
        for (int q : Arrays.stream(queries).sorted().toArray()) {
            
            // ➤ 將所有「左端點 <= q」的區間加入 minHeap
            //    表示這些區間可能覆蓋當前查詢點 q
            while (i < intervals.length && intervals[i][0] <= q) {
                int l = intervals[i][0];
                int r = intervals[i][1];
                // 將 [區間長度, 區間右端點] 放入堆中
                minHeap.offer(new int[]{r - l + 1, r});
                i++;
            }

            // ➤ 移除那些「右端點 < q」的區間
            //    表示這些區間無法覆蓋當前查詢點 q
            while (!minHeap.isEmpty() && minHeap.peek()[1] < q) {
                minHeap.poll();
            }

            // ➤ 若堆為空，表示沒有任何區間覆蓋 q
            //    否則取出最小長度（堆頂）作為結果
            res.put(q, minHeap.isEmpty() ? -1 : minHeap.peek()[0]);
        }

        // 5️⃣ 最後根據原本的 queries 順序回填答案
        int[] result = new int[queries.length];
        for (int j = 0; j < queries.length; j++) {
            result[j] = res.get(queries[j]);
        }
        return result;
    }
}
