public class Solution {
    public int lastStoneWeight(int[] stones) {
        // 找出石頭中的最大重量，用來建立桶的大小
        int maxStone = 0;
        for (int stone : stones) {
            maxStone = Math.max(maxStone, stone);
        }

        // 建立一個桶，每個 index 對應該重量的石頭數量 (元素：0~maxStone的出現次數)
        int[] bucket = new int[maxStone + 1];
        for (int stone : stones) {
            bucket[stone]++; // 統計每個重量的石頭出現幾次
        }
        
        // 初始化 first 和 second 用來記錄最大與次大的石頭重量
        int first = maxStone, second = maxStone;

        while (first > 0) {
            // 如果目前這個重量的石頭數量為偶數，可以成對抵銷
            if (bucket[first] % 2 == 0) {
                first--;
                continue;
            }
            
            // 找出剩下最大的次大石頭 second
            int j = Math.min(first - 1, second);
            while (j > 0 && bucket[j] == 0) {
                j--;
            }

            // 如果沒有其他石頭了，直接回傳目前剩下的 first
            if (j == 0) {
                return first;
            }

            // 執行碰撞：first 與 second 碰撞後，產生一顆新石頭
            second = j;
            bucket[first]--;          // 用掉一顆 first
            bucket[second]--;         // 用掉一顆 second
            bucket[first - second]++; // 產生新的石頭（重量差）

            // 下一輪從剩下最大值開始重新判斷
            first = Math.max(first - second, second);
        }

        return first; // 若最後剩下重量為 0，就會直接 return 0
    }
}