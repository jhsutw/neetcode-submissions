// 4. Manacher's Algorithm
public class Solution {

    // 使用 Manacher's Algorithm，回傳每個中心點的最長半徑陣列
    public int[] manacher(String s) {
        StringBuilder t = new StringBuilder("#");
        // 將原字串加上 '#' 做間隔，方便處理奇偶長度迴文
        for (char c : s.toCharArray()) {
            t.append(c).append("#");
        }

        int n = t.length();
        int[] p = new int[n];  // p[i] 為以 t[i] 為中心的最長迴文半徑
        int l = 0, r = 0;      // [l, r] 為目前掃描過的最右迴文區間

        for (int i = 0; i < n; i++) {
            // 初始半徑：根據對稱性與目前已知的最右邊界進行剪枝
            if (i < r) {
                p[i] = Math.min(r - i, p[l + (r - i)]); // 如果目前中心 i 落在已知最右邊的迴文區間 [l, r] 裡，那麼 p[i] 的最長半徑可以先設為「右界到 i 的距離」與「其對稱點的半徑」兩者的最小值。
            } else {
                p[i] = 0;
            }

            // 嘗試擴展目前以 i 為中心的迴文半徑（往左右擴充字數）
            while (i + p[i] + 1 < n && i - p[i] - 1 >= 0 &&
                   t.charAt(i + p[i] + 1) == t.charAt(i - p[i] - 1)) {
                p[i]++;
            }

            // 若此迴文區間超過目前已知最右邊界，則更新 l, r
            if (i + p[i] > r) {
                l = i - p[i];
                r = i + p[i];
            }
        }

        return p; // 回傳每個中心點的迴文半徑
    }

    // 使用 Manacher 結果找出最長迴文子字串
    public String longestPalindrome(String s) {
        int[] p = manacher(s);    // 取得每個中心的最長半徑
        int resLen = 0, center_idx = 0;

        // 找出半徑最長的中心點
        for (int i = 0; i < p.length; i++) {
            if (p[i] > resLen) {
                resLen = p[i];
                center_idx = i;
            }
        }

        // 計算對應到原始字串的位置
        int resIdx = (center_idx - resLen) / 2; // resLen是左右界間的距離（moving too left），所以除以二就會得到左界
        return s.substring(resIdx, resIdx + resLen);
    }
}

/*
    範例字串 s = "abacdfgdcaba"

    Step 1：預處理
    將字串轉換為帶有分隔符的形式：t = "#a#b#a#c#d#f#g#d#c#a#b#a#"
    這樣可以統一處理奇數與偶數長度的迴文子字串

    Step 2：初始化
    建立長度為 t.length() 的陣列 p[]，p[i] 代表以 t[i] 為中心的最長迴文「半徑」
    並設定變數 l, r 表示當前已知的最右迴文區間 [l, r]

    Step 3：主迴圈
    對每個中心點 i：
        - 如果 i 在區間 [l, r] 內，則利用對稱性給 p[i] 一個初始值
        - 接著向兩邊擴展（左右字符相等）來更新 p[i]
        - 如果擴展後的區間超過 r，則更新 l 和 r

    舉例：
    i = 4 對應到 t = '#a#b#a#' 中的 'b'，左右為 'a'、'a' 相等，因此擴展形成 "aba"
    此時 p[4] = 3，代表以 t[4] 為中心的最長迴文半徑為 3

    Step 4：找出最大 p[i]
    假設 p[center_idx] 是最大值 resLen，則代表最長迴文的中心在 t 的 center_idx
    原字串中的起始索引為 (center_idx - resLen) / 2

    Step 5：回傳結果
    使用 substring(resIdx, resIdx + resLen) 回傳原字串中的最長迴文子字串

    在本例中，最長迴文是 "aba"，出現在最前面和最後面，皆可被正確找出。
*/