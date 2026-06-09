// 3. Two Pointers
class Solution {

    // 主函式：找出最長的迴文子字串
    public String longestPalindrome(String s) {
        int resLen = 0;  // 紀錄目前最長迴文子字串的長度
        int resIdx = 0;  // 紀錄目前最長迴文子字串的起始索引

        // 以每個字元為中心展開（可能是奇數或偶數長度）
        for (int i = 0; i < s.length(); i++) {

            // 處理奇數長度的迴文（如 "aba"）
            int l = i, r = i;
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                // 若當前迴文長度比目前最長的還長，更新結果
                if (r - l + 1 > resLen) {
                    resIdx = l;
                    resLen = r - l + 1;
                }
                l--;
                r++;
            }

            // 處理偶數長度的迴文（如 "abba"）
            l = i;
            r = i + 1;
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                if (r - l + 1 > resLen) {
                    resIdx = l;
                    resLen = r - l + 1;
                }
                l--;
                r++;
            }
        }

        // 回傳最長迴文子字串
        return s.substring(resIdx, resIdx + resLen);
    }
}