// 1. Brute Force
public class Solution {
    public int countSubstrings(String s) {
        int res = 0;  // 記錄回文子字串的總數

        // 外層迴圈：列舉所有子字串的起始點 i
        for (int i = 0; i < s.length(); i++) {
            // 內層迴圈：列舉所有子字串的結束點 j
            for (int j = i; j < s.length(); j++) {
                int l = i, r = j;  // 定義雙指標從兩端開始比對

                // 檢查子字串 s[l...r] 是否為回文
                while (l < r && s.charAt(l) == s.charAt(r)) {
                    l++;  // 向內移動左指標
                    r--;  // 向內移動右指標
                }

                // 如果 l >= r，代表成功從兩端比對到中間，為回文
                res += (l >= r) ? 1 : 0;
            }
        }

        return res;  // 回傳回文子字串的總數
    }
}