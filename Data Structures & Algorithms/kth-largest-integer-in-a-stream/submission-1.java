// 2. Min-Heap
class KthLargest {
    
    // 儲存最小堆，只保留 k 個最大元素
    private PriorityQueue<Integer> minHeap;
    private int k;

    // 初始化：建立一個容量為 k 的最小堆
    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.offer(num);           // 加入數字
            if (minHeap.size() > k) {
                minHeap.poll();           // 如果超過 k 個，移除最小的那個
            }
        }
    }

    // 加入新數字後，回傳目前第 k 大的元素（堆頂）
    public int add(int val) {
        minHeap.offer(val);               // 將新元素放入堆中
        if (minHeap.size() > k) {
            minHeap.poll();               // 若超過 k 個，移除最小的那個
        }
        return minHeap.peek();            // 堆頂就是第 k 大元素（目前minHeap中的最小元素）
    }
}

/*
目前的priorityqueue是最小堆（堆頂是最小元素），以下為最大堆的實作：
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
maxHeap.offer(10);
maxHeap.offer(5);
maxHeap.offer(7);

System.out.println(maxHeap.peek()); // 輸出 10（最大）
*/