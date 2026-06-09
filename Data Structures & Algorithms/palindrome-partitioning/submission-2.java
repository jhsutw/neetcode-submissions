public class Solution {
    // 1. DP 預處理 dp 矩陣：
    // dp[i][j] 表示 s[i..j] 是否為回文
    boolean[][] dp;

    public List<List<String>> partition(String s) {
        int n = s.length();
        dp = new boolean[n][n];
        // 1) 預先用動態規劃填滿 dp 矩陣
        //    遍歷所有可能長度 l = 1..n
        for (int l = 1; l <= n; l++) { // 字串長度
            //    i 從 0 開始到 n-l
            for (int i = 0; i <= n - l; i++) { // i 為子字串起點
                int j = i + l - 1; // j 為子字串終點
                // 子字串起終點相同（回文） && （子串長度<=2（太短沒內部子字串了） 或 內部子串也是回文）
                dp[i][j] = (s.charAt(i) == s.charAt(j) &&
                           (l <= 2 || dp[i + 1][j - 1]));
            }
        }
        
        List<List<String>> res = new ArrayList<>();
        List<String> part = new ArrayList<>();
        // 2) 透過 DFS，利用 dp 判斷快速分割
        dfs(0, s, part, res);
        return res;
    }

    /**
     * 回溯從位置 i 開始切分，僅在 dp[i][j] 為 true 時才遞迴
     * @param i     當前切分起點
     * @param s     原始字串
     * @param part  當前回文子串路徑
     * @param res   最終結果集
     */
    private void dfs(int i, String s, List<String> part, List<List<String>> res) {
        // 如果已切到尾，加入一組完整方案
        if (i >= s.length()) {
            res.add(new ArrayList<>(part));
            return;
        }
        // 嘗試所有 j >= i，若 dp[i][j] 為回文，則分割
        for (int j = i; j < s.length(); j++) {
            if (dp[i][j]) { // df 矩陣中為true
                part.add(s.substring(i, j + 1));   // 選擇 s[i..j]
                dfs(j + 1, s, part, res);           // 從 j+1 繼續切
                part.remove(part.size() - 1);       // 回溯
            }
        }
    }
}

/*
🧪 範例執行 (s = "aab")：

1) DP 預處理 dp 矩陣：
   l=1: dp[0][0]=T("a"), dp[1][1]=T("a"), dp[2][2]=T("b")
   l=2: dp[0][1]=T("aa"), dp[1][2]=F("ab")
   l=3: dp[0][2]=F("aab")

   最終 dp =
     [T, T, F]
     [ , T, F]
     [ ,  , T]

2) DFS 分割：
   dfs(0):
     j=0 (dp[0][0]=T): part=["a"], dfs(1)
        j=1 (dp[1][1]=T): part=["a","a"], dfs(2)
           j=2 (dp[2][2]=T): part=["a","a","b"], dfs(3) → add ["a","a","b"]
        回溯→ part=["a"]
        j=2 (dp[1][2]=F) skip
     回溯→ part=[]
     j=1 (dp[0][1]=T): part=["aa"], dfs(2)
        j=2 (dp[2][2]=T): part=["aa","b"], dfs(3) → add ["aa","b"]
     回溯→ part=[]
     j=2 (dp[0][2]=F) skip

最終 res = [["a","a","b"], ["aa","b"]]
*/