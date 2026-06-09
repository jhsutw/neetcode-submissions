// 3. Dynamic Programming (Top-Down)
// 跟法1其實很像，只是多用一個HashMap計算之前紀錄過的資訊，減少時間複雜度
public class Solution {
    private Map<Integer, Boolean> memo; // 記錄從索引 i 開始，是否能成功切分字串

    public boolean wordBreak(String s, List<String> wordDict) {
        memo = new HashMap<>();
        memo.put(s.length(), true); // 終止條件：走到字串結尾視為可切分成功
        return dfs(s, wordDict, 0); // 從索引 0 開始嘗試切分
    }

    private boolean dfs(String s, List<String> wordDict, int i) {
        // 若這個起點 i 已經計算過，直接回傳結果
        if (memo.containsKey(i)) {
            return memo.get(i);
        }

        // 嘗試每一個字典中的單字 w
        for (String w : wordDict) {
            // 檢查：1. w 不超出剩餘字串長度  2. s[i..i+w.length()) == w
            if (i + w.length() <= s.length() &&
                s.substring(i, i + w.length()).equals(w)) {
                
                // 若剩下的字串也能成功切分，紀錄並返回 true
                if (dfs(s, wordDict, i + w.length())) {
                    memo.put(i, true); // 記憶化：i 開頭可以成功
                    return true;
                }
            }
        }

        // 若沒有任何一種切法成功，紀錄 false
        memo.put(i, false);
        return false;
    }
}