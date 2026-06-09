// 2. Sliding Window
// （1）以0為切點切分nums為數個sub (2) 計算負數的needs（若數量為奇數則-1）（3）每個sub將i為右界、j為左界找最大乘機
public class Solution {
    public int maxProduct(int[] nums) {
        List<List<Integer>> A = new ArrayList<>(); // 存放被0分隔的子陣列段落
        List<Integer> cur = new ArrayList<>();     // 當前段落
        int res = Integer.MIN_VALUE;               // 初始化最大乘積為最小整數值

        // Step 1: 分段 - 依照 0 將 nums 分割成多個不含0的子陣列
        for (int num : nums) {
            res = Math.max(res, num);              // 考慮單個元素的情況（包含0或負數）
            if (num == 0) {
                if (!cur.isEmpty()) A.add(cur);    // 將目前段落加入清單
                cur = new ArrayList<>();           // 開始新的段落
            } else cur.add(num);                   // 加入目前段落
        }
        if (!cur.isEmpty()) A.add(cur);            // 最後一段加入清單

        // Step 2: 處理每一段不含0的子陣列
        for (List<Integer> sub : A) {
            int negs = 0;
            for (int i : sub) {
                if (i < 0) negs++;                 // 統計負數的數量
            }

            int prod = 1;
            int need = (negs % 2 == 0) ? negs : (negs - 1); // 如果負數數量為奇數，就移除一個負數
            negs = 0;

            // 雙指標法縮小左邊直到剩下偶數個負數 (每個sub各自找組合)（i持續往右擴張，遇到複數超過need就縮小左邊邊界j）
            for (int i = 0, j = 0; i < sub.size(); i++) {
                prod *= sub.get(i);                // 計算子陣列乘積
                if (sub.get(i) < 0) {
                    negs++;                        // 碰到負數就更新數量
                    while (negs > need) {          // 如果負數數量超過允許的，就縮小左邊界
                        prod /= sub.get(j);
                        if (sub.get(j) < 0) negs--;
                        j++;
                    }
                }
                if (j <= i) res = Math.max(res, prod); // 更新最大乘積
            }
        }
        return res;
    }
}