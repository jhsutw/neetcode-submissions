// minheap
/**
 * 此程式碼用最小堆（Min Heap）找出陣列中第 k 大的元素。
 * 方法是維護一個大小為 k 的最小堆，當堆大小超過 k 時，就移除最小元素。
 * 最後，堆頂元素就是整體第 k 大的數字。
 * 時間複雜度：O(n log k)，空間複雜度：O(k)
 */

class Solution {
    public int findKthLargest(int[] nums, int k) {
        // 建立最小堆（預設為升序排列）
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // 將所有元素放入堆中，並維持大小為 k
        for (int num : nums){
            minHeap.offer(num);            // 放入當前元素
            if (minHeap.size() > k){       // 如果超過 k 個
                minHeap.poll();            // 移除最小的（堆頂），保留前 k 大元素
            }
        }

        // 此時堆頂就是第 k 大的元素
        return minHeap.peek();
    }
}