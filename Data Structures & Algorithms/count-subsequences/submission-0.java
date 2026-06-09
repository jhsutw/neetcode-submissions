// 1. Recursion
public class Solution {
    public int numDistinct(String s, String t) {
        // 若 t 比 s 還長，不可能成為子序列，直接回傳 0
        if (t.length() > s.length()) {
            return 0;
        }
        // 從索引 (i=0, j=0) 開始遞迴：i 指向 s，j 指向 t
        return dfs(s, t, 0, 0);
    }

    // 回傳：s[i..] 中有多少種子序列可以匹配 t[j..]
    private int dfs(String s, String t, int i, int j) {
        // 若 t 已經完全匹配完（j 到尾端），代表找到一種有效子序列
        if (j == t.length()) {
            return 1;
        }
        // 若 s 已經用完，但 t 還沒匹配完，則無法形成子序列
        if (i == s.length()) {
            return 0;
        }

        // 選擇 1：跳過 s[i]（不使用當前字元），看後續能匹配出多少
        int res = dfs(s, t, i + 1, j);

        // 選擇 2：若 s[i] 與 t[j] 相同，則可以「使用」這個字元去匹配，往下一個目標字元前進
        if (s.charAt(i) == t.charAt(j)) {
            res += dfs(s, t, i + 1, j + 1);
        }
        return res;
    }
}