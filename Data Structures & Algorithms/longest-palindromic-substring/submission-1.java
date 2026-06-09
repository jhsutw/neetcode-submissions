// 2. Dynamic Programming
public class Solution {

    // 主函式：找出最長的迴文子字串
    public String longestPalindrome(String s) {
        int resIdx = 0;       // 最長迴文子字串的起始索引
        int resLen = 0;       // 最長迴文子字串的長度
        int n = s.length();   // 字串長度

        // 建立動態規劃表：dp[i][j] 表示 s[i..j] 是否為迴文
        boolean[][] dp = new boolean[n][n];

        // 從字串尾端開始往前處理（因為 dp[i+1][j-1] 需要先算好）
        for (int i = n - 1; i >= 0; i--) {
            // j 從 i 向右延伸，形成 s[i..j]
            for (int j = i; j < n; j++) {
                // 條件一：s[i] == s[j]（首尾相同）
                // 條件二：若長度 <= 2，直接是迴文（"aa"、"aba"）
                // 條件三：若 s[i+1..j-1] 是迴文，則 s[i..j] 也是迴文 
                if (s.charAt(i) == s.charAt(j) &&
                    (j - i <= 2 || dp[i + 1][j - 1])) {

                    dp[i][j] = true; // 標記為迴文

                    // 若這段迴文比目前紀錄的更長，則更新結果
                    if (resLen < (j - i + 1)) {
                        resIdx = i;               // 更新起始位置
                        resLen = j - i + 1;       // 更新長度
                    }
                }
            }
        }

        // 回傳最長的迴文子字串
        return s.substring(resIdx, resIdx + resLen);
    }
}