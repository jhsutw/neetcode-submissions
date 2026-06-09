// 3. Dynamic Programming (Bottom-Up)
public class Solution {
    public int numDecodings(String s) {
        // dp[i] 代表從第 i 個字元開始到字串結尾的解碼方法總數
        int[] dp = new int[s.length() + 1];

        // 空字串視為一種有效解碼方式
        dp[s.length()] = 1;

        // 從字串最後一個字元往前處理 121 > [0, 0, 1, 1] > [0, 2, 1, 1] > [3, 2, 1, 1]
        for (int i = s.length() - 1; i >= 0; i--) {
            // 如果當前字元是 '0'，無法對應到任何字母（因為 '0' 不是有效的編碼），解法為 0
            if (s.charAt(i) == '0') {
                dp[i] = 0;
            } else {
                // 單獨取一位數字解碼（對應 A~I），解法數為 dp[i+1]
                dp[i] = dp[i + 1];

                // 如果兩位數在 10 到 26 之間，也可以當作一組解碼（對應 J~Z） 
                if (i + 1 < s.length() && 
                    (s.charAt(i) == '1' || 
                     (s.charAt(i) == '2' && s.charAt(i + 1) < '7'))) {
                    dp[i] += dp[i + 2];
                }
            }
        }

        // 回傳從第 0 個字元開始的總解碼方法數
        return dp[0];
    }
}