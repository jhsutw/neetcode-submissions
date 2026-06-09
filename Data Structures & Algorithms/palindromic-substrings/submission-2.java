// 3. Two Pointers
public class Solution {
    public int countSubstrings(String s) {
        int res = 0;  // 用來計算回文子字串的總數

        // 每個字元都可能是回文的中心，從左到右遍歷
        for (int i = 0; i < s.length(); i++) {
            // 處理奇數長度的回文（中心點是單一字元）
            int l = i, r = i;
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                res++;  // 找到一個回文子字串
                l--;    // 左邊擴展
                r++;    // 右邊擴展
            }

            // 處理偶數長度的回文（中心點是兩個字元）
            l = i;
            r = i + 1;
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                res++;  // 找到一個回文子字串
                l--;    // 左邊擴展
                r++;    // 右邊擴展
            }
        }

        return res;  // 回傳所有回文子字串的數量
    }
}