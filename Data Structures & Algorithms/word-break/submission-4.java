// 5. Dynamic Programming (Bottom-Up)

public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        // dp[i] 表示從索引 i 開始的子字串能否被成功切分成字典中的單字
        boolean[] dp = new boolean[s.length() + 1];
        dp[s.length()] = true; // 空字串視為可成功切分（Base case）

        // 從字串的尾端往前檢查
        for (int i = s.length() - 1; i >= 0; i--) {
            // 嘗試每一個字典中的單字 w
            for (String w : wordDict) {
                // 如果 w 的長度不會超過剩餘字串，且 s[i...i+w.length()-1] == w
                if ((i + w.length()) <= s.length() &&
                     s.substring(i, i + w.length()).equals(w)) {
                    // 那麼 dp[i] 的值取決於 dp[i + w.length()]
                    // 如果從 i 開頭的這段字串，先切出字典中的單字 w 後，剩下的子字串能成功切分（dp[i + w.length()] == true），那麼從 i 開始的整段字串也能成功切分（dp[i] = true）
                    dp[i] = dp[i + w.length()];
                }
                // 如果已經找到一個單字 w 可以讓 dp[i] 成立，就不用再檢查其他字典單字
                if (dp[i]) {
                    break;
                }
            }
        }

        // 回傳 dp[0]，代表整個字串能否成功切分
        return dp[0];
    }
}
