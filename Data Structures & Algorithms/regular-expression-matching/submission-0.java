// 1. Recursion
public class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        // 從 s 與 p 的起點開始進行遞迴比對
        return dfs(0, 0, s, p, m, n);
    }

    private boolean dfs(int i, int j, String s, String p, int m, int n) {
        // 若模式 p 已經完全用完，只有在 s 也完全用完時才算匹配成功
        if (j == n) return i == m;

        // 檢查當前字元是否匹配：
        // 1) s 還沒用完，且
        // 2) s[i] == p[j] 或 p[j] 是萬用字元 '.'
        boolean match = i < m && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');

        // 若下一個模式字元是 '*'，則有兩種選擇：
        // 1) 將 "前一元素 + *" 視為匹配 0 次：直接略過 p 的兩個字元（j+2）
        // 2) 若當前 match 成立，消耗 s 的一個字元（i+1），但 p 不前進（j）
        if (j + 1 < n && p.charAt(j + 1) == '*') {
            return dfs(i, j + 2, s, p, m, n) ||           // 使用 0 次 (不需要proceding element 或是 已經匹配完了)(+2：跳過被proceded的元素以及*符號兩個字元)
                   (match && dfs(i + 1, j, s, p, m, n));  // 使用 ≥1 次（因為*視為proceding element所以可以繼續用當前j元素來跟i+1匹配）
        }

        // 若不是 '*'，且當前字元匹配，則同步前進
        if (match) {
            return dfs(i + 1, j + 1, s, p, m, n);
        }

        // 其他情況皆不匹配
        return false;
    }
}