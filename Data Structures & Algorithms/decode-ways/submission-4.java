// 4. Dynamic Programming (Space Optimized)
// 邏輯：當處理i且為單位數時，是看i+1以後的組合數量；當處理i且為雙位數時，是看i + 2以後的組合數量+自己變化出的組合數量
public class Solution {
    public int numDecodings(String s) {
        int dp = 0, dp2 = 0;
        int dp1 = 1; // dp1 對應 dp[i+1]，初始化為 1（空字串的解法數）

        // 從右往左掃描字串
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                dp = 0; // '0' 不能對應任何字母
            } else {
                dp = dp1; // 單獨解碼一個字元的情況（對應 A~I）

                // 判斷是否可以合併兩個字元解碼（10~26，對應 J~Z）
                if (i + 1 < s.length() && (
                    s.charAt(i) == '1' || 
                    (s.charAt(i) == '2' && s.charAt(i + 1) < '7'))) {
                    dp += dp2; // 加上合併解碼的情況
                }
            }

            // 更新狀態變數，準備下一輪迴圈使用
            dp2 = dp1;
            dp1 = dp;

            // 清空 dp，避免影響下一輪迴圈（其實可省略，但寫出來更清楚）
            dp = 0;
        }

        // 最終答案儲存在 dp1（也就是 dp[0]）
        return dp1;
    }
}

// Input: "12131"
// dp1 表示 dp[i + 1]，dp2 表示 dp[i + 2]

// 初始值：
// dp1 = 1; // 空字串的解碼方式為 1 種
// dp2 = 0; // 尚未計算

// i = 4, s.charAt(4) = '1'
// 單一解碼合法 => dp = dp1 = 1
// 無雙位數可組 => dp += 0
// 更新: dp2 = 1 (dp1), dp1 = 1 (dp), dp = 0

// i = 3, s.charAt(3) = '3'
// 單一解碼合法 => dp = dp1 = 1
// '31' 不合法 => 不加 dp2
// 更新: dp2 = 1, dp1 = 1, dp = 0

// i = 2, s.charAt(2) = '1'
// 單一解碼合法 => dp = dp1 = 1
// '13' 合法 => dp += dp2 = 1 => dp = 2
// 更新: dp2 = 1, dp1 = 2, dp = 0

// i = 1, s.charAt(1) = '2'
// 單一解碼合法 => dp = dp1 = 2
// '21' 合法 => dp += dp2 = 1 => dp = 3
// 更新: dp2 = 2, dp1 = 3, dp = 0

// i = 0, s.charAt(0) = '1'
// 單一解碼合法 => dp = dp1 = 3
// '12' 合法 => dp += dp2 = 2 => dp = 5
// 更新: dp2 = 3, dp1 = 5, dp = 0

// 最後回傳 dp1 = 5
// 所以 "12131" 的總解碼方式為 5 種