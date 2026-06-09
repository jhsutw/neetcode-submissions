// 4. Dynamic Programming (Hash Set)
// 跟法1類似，只是（1）用hashset來裝wordDict、（2）先比較wordDict最大單字長度，j往右擴張時最多到該長度，減少時間複雜度
public class Solution {
    private HashSet<String> wordSet; // 用來快速檢查某個子字串是否在字典中
    private Boolean[] memo; // 記錄從索引 i 開始是否能成功切分（null 代表尚未計算）
    private int t; // 字典中最長單字的長度，用來限制內層迴圈搜尋範圍

    public boolean wordBreak(String s, List<String> wordDict) {
        // 把字典轉成 HashSet，加快查找速度 O(1)
        wordSet = new HashSet<>(wordDict);
        // 初始化 memo，大小為 s.length()（每個位置一個狀態）
        memo = new Boolean[s.length()];
        t = 0;
        // 找出字典中最長單字的長度，優化 DFS 搜尋範圍
        for (int i = 0; i < wordDict.size(); i++) {
            t = Math.max(t, wordDict.get(i).length());
        }
        // 從索引 0 開始嘗試切分
        return dfs(s, 0);
    }

    private boolean dfs(String s, int i) {
        // 若走到字串末尾，表示成功切分
        if (i == s.length()) {
            return true;
        }
        // 若這個起點 i 已計算過，直接回傳記錄的結果
        if (memo[i] != null) {
            return memo[i];
        }

        // 嘗試切出從 i 開始、長度不超過 t 的所有可能子字串
        for (int j = i; j < Math.min(i + t, s.length()); j++) {
            // 如果 s[i..j] 在字典中
            if (wordSet.contains(s.substring(i, j + 1))) {
                // 繼續從 j+1 開始切分
                if (dfs(s, j + 1)) {
                    memo[i] = true; // 記錄此起點可以成功
                    return true;
                }
            }
        }
        // 所有切法都失敗，記錄為 false
        memo[i] = false;
        return false;
    }
}