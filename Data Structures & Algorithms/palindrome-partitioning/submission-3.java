public class Solution {
    /**
     * 主函式：先用動態規劃預計算所有 s[i..j] 是否為回文，
     * 再用遞迴（DFS）組合所有合法分割。
     */
    public List<List<String>> partition(String s) {
        int n = s.length();
        // dp[i][j] = true 表示 s.substring(i, j+1) 是回文
        boolean[][] dp = new boolean[n][n];
        // 動態規劃：按子串長度 l 由小到大
        for (int l = 1; l <= n; l++) {
            for (int i = 0; i <= n - l; i++) {
                int j = i + l - 1;
                // 端點字符相同，且內部子串長度 <= 2 或者內部子串也是回文
                dp[i][j] = (s.charAt(i) == s.charAt(j) &&
                            (l <= 2 || dp[i + 1][j - 1]));
            }
        }
        // 從索引 0 開始，遞迴構建所有分割
        return dfs(s, dp, 0);
    }

    /**
     * 遞迴函式：返回從位置 i 開始的所有回文分割
     * @param s   原始字串
     * @param dp  回文查表
     * @param i   當前分割起點
     * @return    List of List of strings，每個內部列表是一種分割方案
     */
    private List<List<String>> dfs(String s, boolean[][] dp, int i) {
        int n = s.length();
        // Base case：已經切到末尾，回傳一個包含空list的List
        if (i >= n) {
            return new ArrayList<List<String>>() {{ 
                add(new ArrayList<>()); 
            }};
        }

        List<List<String>> ret = new ArrayList<>();
        // 嘗試所有 j >= i，使 s[i..j] 成為一段回文
        for (int j = i; j < n; j++) {
            if (!dp[i][j]) continue;           // 不是回文就跳過
            String prefix = s.substring(i, j + 1);
            // 取得後續從 j+1 開始的所有分割結果
            List<List<String>> nextList = dfs(s, dp, j + 1);
            // 將 prefix 插入到每一種後續分割的開頭，構成新的方案
            // 對於從 j+1 開始遞迴回傳的每一種「後續分割」suffix
            for (List<String> suffix : nextList) {
                // 建立一個新的分割方案 curr
                List<String> curr = new ArrayList<>();
                // 第一部分放上當前這一段回文 prefix
                curr.add(prefix);
                // 再把後續分割的所有子串接到後面
                curr.addAll(suffix);
                // 把組合完成的 curr 加入最終結果 ret
                ret.add(curr);
            }
        }
        return ret;
    }
}

/*
🧪 範例演示 (s = "aab")：

1) DP 預處理：
   s = "aab", n = 3
   dp 長度 1: dp[0][0]=T("a"), dp[1][1]=T("a"), dp[2][2]=T("b")
   dp 長度 2: dp[0][1]=T("aa"), dp[1][2]=F("ab")
   dp 長度 3: dp[0][2]=F("aab")

2) dfs(0):
   j=0 (dp[0][0]=T) → prefix="a"
     nextList = dfs(1) → [ ["a","b"], ["ab"]? no, dp[1][2]=F → nextList = [["a","b"]]
       dfs(1):
         j=1 (dp[1][1]=T) → prefix2="a", next=dfs(2)=[["b"]]
            combine → ["a","b"]
         j=2 (dp[1][2]=F) skip
     combine prefix "a" with each in nextList → ["a","a","b"]
   j=1 (dp[0][1]=T) → prefix="aa"
     nextList = dfs(2) = [["b"]]
     combine → ["aa","b"]
   j=2 (dp[0][2]=F) skip

最終返回：
[
  ["a","a","b"],
  ["aa","b"]
]
*/

// ----------------- 各種方法比較 -----------------
// | 方法  | 核心思路                         | 時間複雜度         | 空間複雜度                     | 優點                         | 缺點                          |
// |-------|----------------------------------|--------------------|--------------------------------|------------------------------|-------------------------------|
// | 方法1 | 雙指針 + 全域 part               | O(n·n!)            | O(n·n!)+O(n)                   | 邏輯完整，可做剪枝           | 程式碼繁瑣，不夠直觀            |
// | 方法2 | for + isPali 回溯                | O(n·n!)            | O(n·n!)+O(n)                   | 程式最簡潔，結構清晰         | 回文判斷重複，效率較低          |
// | 方法3 | DP 預處理 + 全域回溯             | O(n² + n·n!)       | O(n²)+O(n·n!)+O(n)             | 回文判斷 O(1)，效率高         | 需額外 O(n²) 空間             |
// | 方法4 | DP 預處理 + 遞迴返回 List        | O(n² + n·n!)       | O(n²)+O(n·n!)                  | 無全域變數，函數式，易讀     | 遞迴多次 new List，常數開銷略高 |
// ---------------------------------------------------