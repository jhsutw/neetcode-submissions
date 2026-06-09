public class MedianFinder {

    // 儲存較小一半的數字（最大堆），堆頂是最大值
    private Queue<Integer> smallHeap;

    // 儲存較大一半的數字（最小堆），堆頂是最小值
    private Queue<Integer> largeHeap;

    public MedianFinder() {
        // maxHeap：大值優先（從大排到小），用於記錄較小的一半數字
        smallHeap = new PriorityQueue<>((a, b) -> b - a);

        // minHeap：小值優先（從小排到大），用於記錄較大的一半數字
        largeHeap = new PriorityQueue<>((a, b) -> a - b);
    }

    public void addNum(int num) {
        // 預設先將新數字加進 smallHeap
        smallHeap.add(num);

        // 維持 smallHeap 的最大值 <= largeHeap 的最小值
        // 如果不滿足條件，就把 smallHeap 的最大值移到 largeHeap
        if (
            smallHeap.size() - largeHeap.size() > 1 || // 若 smallHeap 多太多
            (!largeHeap.isEmpty() && smallHeap.peek() > largeHeap.peek()) // 或違反順序性（smallHeap 中所有數字都應該比 largeHeap 中的數字小或相等）
        ) {
            largeHeap.add(smallHeap.poll());
        }

        // 若 largeHeap 多太多，也要平衡回來
        if (largeHeap.size() - smallHeap.size() > 1) {
            smallHeap.add(largeHeap.poll());
        }
    }

    public double findMedian() {
        // 如果兩邊大小相同，回傳兩堆堆頂的平均值
        if (smallHeap.size() == largeHeap.size()) {
            return (double) (largeHeap.peek() + smallHeap.peek()) / 2;
        } 
        // 否則回傳元素較多的那一邊的堆頂（中位數）
        else if (smallHeap.size() > largeHeap.size()) {
            return (double) smallHeap.peek();
        } else {
            return (double) largeHeap.peek();
        }
    }
}