public class MedianFinder {
    private ArrayList<Integer> data;

    // 建構子：初始化一個空的 ArrayList
    public MedianFinder() {
        data = new ArrayList<>();
    }

    // 加入數字：直接加入 list 中（時間複雜度 O(1)）
    public void addNum(int num) {
        data.add(num);
    }

    // 找中位數：每次都重新排序整個 list，時間複雜度 O(n log n)
    public double findMedian() {
        Collections.sort(data); // 排序整個 list（會改動原資料）

        int n = data.size();
        // 奇數個數時取中間一個
        if ((n & 1) == 1) {
            return data.get(n / 2); // int[]取資料：data[index] ; ArrayList取資料：data.get(index)
        } 
        // 偶數個數時取中間兩個的平均
        else {
            return (data.get(n / 2) + data.get(n / 2 - 1)) / 2.0;
        }
    }
}

/*
✅ 含意：
(n & 1) == 1   // 如果是 true，代表 n 是奇數
(n & 1) == 0   // 如果是 true，代表 n 是偶數

🔍 為什麼這樣可以判斷？
在二進位中：

奇數的最低位（最右邊一位）永遠是 1
例如：
1 = 0001
3 = 0011
5 = 0101

偶數的最低位永遠是 0
例如：
2 = 0010
4 = 0100
*/