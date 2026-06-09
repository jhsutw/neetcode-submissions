public class Solution {

    // 定義一個遞迴函式 dfs(i, s)，表示從索引 i 開始到字串尾端的解碼方法總數
    private int dfs(int i, String s) {
        // 若 i 到達字串尾端，表示是一種有效解碼方式
        if (i == s.length()) return 1;

        // 若當前字元是 '0'，無法對應任何字母，直接返回 0 種解法
        if (s.charAt(i) == '0') return 0;

        // 先嘗試單獨解碼一個字元，遞迴處理剩下的子字串
        int res = dfs(i + 1, s);

        // 接著檢查是否可以兩個字元一起解碼（例如 "12" 對應 "L"）
        if (i < s.length() - 1) {
            // 當前為 '1'：無論下一個是什麼數字都可組成 10~19
            // 當前為 '2' 且下一個是 0~6，可組成 20~26
            if (s.charAt(i) == '1' ||
               (s.charAt(i) == '2' && s.charAt(i + 1) < '7')) {
                res += dfs(i + 2, s);  // 合法的兩位數，嘗試跳兩步遞迴
            }
        }

        return res;
    }

    // 主函式：從第 0 個索引開始計算解碼方式
    public int numDecodings(String s) {
        return dfs(0, s);
    }
}
