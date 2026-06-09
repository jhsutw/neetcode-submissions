class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 建立prioity Queue來放長度為k的子字串（降冪排序）
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        // 設定output長度為nums.length - k + 1
        int[] output = new int[nums.length - k + 1];
        int idx = 0;
        // 把值一個個放入heap中，同時刪除i - k前的值
        for (int i = 0; i < nums.length; i++){
            heap.offer(new int[]{nums[i], i});
            // 類似sliding window左界概念，左界右移
            if (i >= k - 1){
                while (heap.peek()[1] <= i - k){
                    heap.poll();
                } // 會先把output[idx]設為heap.peak()[0]，然後再更新idx = idx + 1
                output[idx++] = heap.peek()[0];
            }
        }
        return output;
    }
}
