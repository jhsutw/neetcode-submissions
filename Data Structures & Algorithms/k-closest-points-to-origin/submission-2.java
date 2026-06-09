public class Solution {
    public int[][] kClosest(int[][] points, int K) {
        // 建立一個最小堆（Min Heap），根據每個點與原點的距離平方作為排序依據
        // 放入堆中的元素是 int[] 陣列，其中包含 [距離平方, x座標, y座標]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparing(a -> a[0]));

        // 對每個點計算距離平方後加入最小堆
        for (int[] point : points) {
            // 計算點到原點的距離平方（無須開根號，避免浮點數誤差）
            int dist = point[0] * point[0] + point[1] * point[1];
            // 將 [距離平方, x, y] 放入堆中
            minHeap.offer(new int[]{dist, point[0], point[1]});
        }

        // 建立結果陣列，用來儲存最近的 K 個點
        int[][] result = new int[K][2];
        for (int i = 0; i < K; ++i) {
            // 從堆中取出距離最小的點（根據距離平方排序）
            int[] point = minHeap.poll();
            // 只需要 x 和 y 座標，不需要距離平方
            result[i] = new int[]{point[1], point[2]};
        }

        // 回傳最近的 K 個點
        return result;
    }
}