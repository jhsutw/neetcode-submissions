// 3. Heap
class Solution {
    public int lastStoneWeight(int[] stones) {
        // 用最小堆模擬最大堆，記得所有元素都乘上 -1
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int s : stones) {
            minHeap.offer(-s); // 把每個石頭重量變成負數
        }

        // 只要還有兩顆以上的石頭
        while (minHeap.size() > 1) {
            int first = minHeap.poll();  // 最大的（實際值是 -first）
            int second = minHeap.poll(); // 次大的（實際值是 -second）
            if (second > first) { // 若兩值相等就不用補差值到minHeap中，所以要下這個判斷式
                // 把差值放回堆裡（注意：也要取負數）
                minHeap.offer(first - second);
            }
        }

        // 補 0 再回傳正數
        minHeap.offer(0);

        // 若補0前剩下一個值就會回傳該值（該值為負比0小） ；否則回傳0
        return Math.abs(minHeap.peek());
    }
}