// 4. Dynamic Programming (Space Optimized)
public class Solution {
    public int climbStairs(int n) {
        // 初始化：one 表示 f(i-1)，two 表示 f(i-2)
        int one = 1, two = 1;

        // 從第 2 階開始算到第 n 階 (每次把one跟two都往上走一格)
        for (int i = 0; i < n - 1; i++) {
            int temp = one;        // 暫存 f(i-1)
            one = one + two;       // f(i) = f(i-1) + f(i-2)
            two = temp;            // 更新 f(i-2) 為之前的 f(i-1)
        }

        return one; // 回傳 f(n)
    }
}