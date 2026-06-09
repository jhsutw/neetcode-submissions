// 2. Dynamic Programming (Top-Down)
public class Solution {
    int[] cache; // 用來記錄每一階的答案，避免重複運算

    public int climbStairs(int n) {
        cache = new int[n]; // 開一個大小為 n 的陣列（對應第 0 到第 n-1 階）
        for (int i = 0; i < n; i++) {
            cache[i] = -1; // 初始化為 -1 表示尚未計算過
        }
        return dfs(n, 0); // 從第 0 階開始往上爬
    }

    public int dfs(int n, int i) {
        if (i >= n) return i == n ? 1 : 0; // 若剛好爬到 n 階是合法，超過則不合法

        if (cache[i] != -1) return cache[i]; // 如果之前算過，直接回傳

        // 爬一階或兩階的總和
        return cache[i] = dfs(n, i + 1) + dfs(n, i + 2);
    }
}