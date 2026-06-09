// 5. Manacher's Algorithm
public class Solution {

    // Manacher 演算法，用來找出以每個中心為基準的最長回文半徑
    public int[] manacher(String s) {
        // Step 1: 為了統一處理奇數與偶數長度的回文，使用特殊字元 '#' 插入原字串中
        // 例如: "aba" -> "#a#b#a#"
        StringBuilder t = new StringBuilder("#");
        for (char c : s.toCharArray()) {
            t.append(c).append("#");
        }

        int n = t.length();
        int[] p = new int[n];  // p[i] 表示以 i 為中心的回文半徑
        int l = 0, r = 0;      // [l, r] 為目前最右擴展的回文區間

        // Step 2: 遍歷新字串 t，更新每個中心點的最大回文長度
        for (int i = 0; i < n; i++) {
            // 如果 i 在目前回文右界 r 的範圍內
            // 使用對稱位置的結果來初始化 p[i]，避免重複比較
            /*
            這行就是在問：「我是不是可以偷懶，用對稱點的答案來當作我的起點？」
            1. 如果可以，就用對稱點的答案（但不能超出右邊界）；
            2. 如果不行，就自己從 0 開始擴展。
            */
            p[i] = (i < r) ? Math.min(r - i, p[l + (r - i)]) : 0;

            // 嘗試中心擴展，向外擴展左右相等的字元
            while (i + p[i] + 1 < n && i - p[i] - 1 >= 0 &&
                   t.charAt(i + p[i] + 1) == t.charAt(i - p[i] - 1)) {
                p[i]++;
            }

            // 如果新的回文擴展區間超過原本的 r，就更新 l 和 r
            if (i + p[i] > r) {
                l = i - p[i];
                r = i + p[i];
            }
        }

        return p;  // 回傳每個位置為中心時的最大回文半徑
    }

    // 計算原字串中所有的回文子字串數量
    public int countSubstrings(String s) {
        int res = 0;
        int[] p = manacher(s);

        // 每個回文半徑 p[i] 所代表的實際回文子字串數為 (p[i] + 1) / 2
        /*
        每個真正的字元都被 # 隔開；每個回文可能跨越 #，所以會比原本字串長度還多
        */
        for (int i : p) {
            res += (i + 1) / 2;
        }

        return res;
    }
}