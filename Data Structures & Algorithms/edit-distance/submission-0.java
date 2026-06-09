// 1. Recursion
public class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();

        // 從 (i=0, j=0) 開始遞迴計算最小編輯距離
        return dfs(0, 0, word1, word2, m, n);
    }

    // dfs(i, j): 把 word1 的子字串 word1[i..] 轉成 word2 的子字串 word2[j..] 所需的最少操作數
    private int dfs(int i, int j, String word1, String word2, int m, int n) {
        // 若 word1 用完，只能把 word2 剩下的字元都「插入」進來
        if (i == m) return n - j;
        // 若 word2 用完，只能把 word1 剩下的字元都「刪除」
        if (j == n) return m - i;

        // 當前字元相同，這一步不用操作，往下一個字元繼續比
        if (word1.charAt(i) == word2.charAt(j)) {
            return dfs(i + 1, j + 1, word1, word2, m, n);
        }

        // 三個選擇取其最小：
        // 1) 刪除 word1[i]        -> dfs(i+1, j)
        // 2) 在 word1[i] 前插入    -> dfs(i, j+1)
        // 3) 將 word1[i] 替換掉    -> dfs(i+1, j+1)
        int res = Math.min(dfs(i + 1, j, word1, word2, m, n),
                           dfs(i, j + 1, word1, word2, m, n));
        res = Math.min(res, dfs(i + 1, j + 1, word1, word2, m, n));

        // 本步執行了 1 次操作（刪除/插入/替換 其一）
        return res + 1;
    }
}