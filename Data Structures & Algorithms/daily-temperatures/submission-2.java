class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];

        // 從倒數第二個元素往前遞迴
        // 每次都往後比
        // >> 若後面元素比較小就判斷下一個warmer day是 j + res[j](res[j]為距離下一個warmer day的天數；若res[j]為0代表後面都沒有warmer day了，break)  
        // >> 拿j - i得到距離下一個warmer day的天數
        for (int i = n - 2; i >= 0; i--){
            int j = i + 1;
            while (j < n && temperatures[j] <= temperatures[i]){
                if (res[j] == 0){
                    j = n;
                    break;
                }
                j += res[j];
            }

            if (j < n){
                res[i] = j - i;
            }
        }
        return res;
    }
}

/*
🔁 實際流程（假設你在第 4 天）
1. 當前是 i = 4（值是 69）
2. 我們先看 j = i + 1 = 5，第 5 天溫度是 72，比 69 高 → bingo！
3. 所以 res[4] = 5 - 4 = 1
4. 再看 i = 3（71）：
5. 看 j = 4（69） → 小於 71，不行
6. 那看看 res[4] = 1，代表 69 一天後會有 72，我們就直接跳過去：
7. j = 4 + 1 = 5
8. temperatures[5] = 72，比 71 高 → bingo！
9. 所以 res[3] = 5 - 3 = 2
*/
