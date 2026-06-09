// 4. Two Pointers (Optimal)
public class Solution {

    public int countSubstrings(String s) {
        int res = 0;

        // 對於字串中每一個位置 i，嘗試以下兩種中心擴展：
        for (int i = 0; i < s.length(); i++) {
            // 情況 1：以 s[i] 為中心（奇數長度回文）
            res += countPali(s, i, i);
            // 情況 2：以 s[i] 和 s[i+1] 為中心（偶數長度回文）
            res += countPali(s, i, i + 1);
        }

        return res;  // 回傳總共找到的回文子字串數
    }

    // 給定左右指標 l, r，從中心向兩邊擴展，計算有幾個回文子字串
    private int countPali(String s, int l, int r) {
        int res = 0;

        // 當左右邊界沒有超出範圍且兩邊字元相等時，持續擴展
        while (l >= 0 && r < s.length() &&
               s.charAt(l) == s.charAt(r)) {
            res++;  // 找到一個回文子字串
            l--;    // 左擴展
            r++;    // 右擴展
        }

        return res;
    }
}