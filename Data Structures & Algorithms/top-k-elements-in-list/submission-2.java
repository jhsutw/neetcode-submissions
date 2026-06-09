class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // 創建hashmap（數字, 頻率）
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums){
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        // 創建Priority Queue (指定排序方式由小到大)
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        // 把HashMap依各entry提取出來至Priority Queue
        // 若Priority Queue的數量大於k，每新增一個entry就剔除"第一個"entry（目前頻率最小的）
        // Priority Queue寫法：heap.offer()新增entry/ heap.size()取得entry數量/ heap.poll()剔除第一個entry
        for (Map.Entry<Integer, Integer> entry : count.entrySet()){
            heap.offer(new int[]{entry.getValue(), entry.getKey()});
            if (heap.size() > k){
                heap.poll();
            }
        }

        // 把heap中每個entry第二個元素（數字）放進res
        int[] res = new int[k];
        for (int i = 0; i < k; i++){
            res[i] = heap.poll()[1];
        }
        return res;
    }
}
