// 2. Dynamic Programming (Top-Down)
public class Solution {

    // 主函式：計算字串 s 的解碼方式總數
    public int numDecodings(String s) {
        Map<Integer, Integer> dp = new HashMap<>(); // 記憶化表，儲存從索引 i 開始的結果
        dp.put(s.length(), 1);  // 空字串視為一種有效解碼方式 (base case: 這一行會在 i == s.length() 時結束遞迴，並回傳 1)
        return dfs(s, 0, dp);   // 從索引 0 開始遞迴
    }

    // 遞迴函式 dfs：計算從索引 i 開始的解碼總數，並使用 dp 記憶化避免重複計算
    private int dfs(String s, int i, Map<Integer, Integer> dp) {
        // 如果之前已經計算過，就直接取結果
        if (dp.containsKey(i)) {
            return dp.get(i);
        }

        // 若當前字元為 '0'，無法解碼，返回 0（包含零res==0）
        if (s.charAt(i) == '0') {
            return 0;
        }

        // 嘗試解碼單個字元，例如 '1' -> 'A' (單個字元算1個解法)
        int res = dfs(s, i + 1, dp);

        // 若有可能解碼成兩個字元（10~26），也嘗試解碼兩個字元（兩個字元算第2種解法）
        if (i + 1 < s.length() && (
            s.charAt(i) == '1' ||                     // "1x" 組合合法
            (s.charAt(i) == '2' && s.charAt(i + 1) < '7')  // "2x" 且 x < 7 才合法
        )) {
            res += dfs(s, i + 2, dp);
        }

        // 把當前結果存進 dp 表中
        dp.put(i, res);
        return res;
    }
}