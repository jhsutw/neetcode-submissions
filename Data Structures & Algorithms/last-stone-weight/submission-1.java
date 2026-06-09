// 2. Binary Search
public class Solution {
    public int lastStoneWeight(int[] stones) {
        Arrays.sort(stones); // 將石頭陣列排序（從小到大）
        int n = stones.length;

        while (n > 1) {
            // 取出最大的兩顆石頭：stones[n-1] 和 stones[n-2]
            int cur = stones[n - 1] - stones[n - 2];
            n -= 2; // 移除兩顆石頭（模擬刪除）

            if (cur > 0) {
                // 使用 binary search 找出 cur 要插入的位置，維持陣列有序
                int l = 0, r = n;
                while (l < r) {
                    int mid = (l + r) / 2;
                    if (stones[mid] < cur) {
                        l = mid + 1;
                    } else {
                        r = mid;
                    }
                }
                int pos = l; // 插入 cur 的位置

                // 將 stones 補cur的位子回來（前面我們刪了兩顆石頭）
                n++;
                stones = Arrays.copyOf(stones, n); // 複製stones（只複製前n個元素，當前為刪除末兩位並加入cur的元素數量）

                // 把 cur 插入 pos 位置（cur以後的元素都右移，要騰出空間放它）
                for (int i = n - 1; i > pos; i--) {
                    stones[i] = stones[i - 1];
                }
                stones[pos] = cur; // 把 cur 插入
            }
        }

        return n > 0 ? stones[0] : 0;
    }
}