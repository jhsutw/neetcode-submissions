// 1. Brute Force
public class Solution {

    // 主函式：找出最長的迴文子字串
    public String longestPalindrome(String s) {
        String res = "";      // 用來儲存目前找到的最長迴文子字串
        int resLen = 0;       // 用來儲存最長迴文的長度

        // 遍歷所有可能的子字串起點 i
        for (int i = 0; i < s.length(); i++) {

            // 遍歷所有子字串終點 j，j 必須 >= i
            for (int j = i; j < s.length(); j++) {

                int l = i, r = j;  // 使用雙指標 l 與 r 檢查子字串 s[i..j] 是否為迴文

                // 當左右字元相等時，往中間移動指標
                while (l < r && s.charAt(l) == s.charAt(r)) {
                    l++;
                    r--;
                }

                // 如果 l >= r 表示這個區間是迴文，且長度比目前已知最大長（求最長的 Palindromic），則更新結果
                if (l >= r && resLen < (j - i + 1)) {
                    res = s.substring(i, j + 1);     // 更新最長迴文子字串
                    resLen = j - i + 1;              // 更新最長長度
                }
            }
        }

        return res;  // 回傳最長的迴文子字串
    }
}