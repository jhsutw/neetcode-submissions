public class Solution {
    public int[][] kClosest(int[][] points, int k) {
        // 建立一個最大堆（Max Heap），用來存最小的 k 個點
        // 比較方式：將距離平方大的排在前面，這樣可以在超出 k 時把最遠的移除
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (a, b) -> Integer.compare(
                b[0] * b[0] + b[1] * b[1],  // b 的距離平方
                a[0] * a[0] + a[1] * a[1]   // a 的距離平方
            )
        );

        // 把每個點丟進 maxHeap
        for (int[] point : points) {
            maxHeap.offer(point);       // 加入堆中
            if (maxHeap.size() > k) {   // 若超過 k 個，移除最遠的那個 (現在是最大堆，堆頂是最大值)
                maxHeap.poll();
            }
        }

        // 建立結果陣列
        int[][] res = new int[k][2];
        int i = 0;

        // 把 heap 中剩下的 k 個點取出
        while (!maxHeap.isEmpty()) {
            res[i++] = maxHeap.poll();  // 將點加入結果中
        }

        return res;  // 回傳最近的 k 個點
    }
}