public class Solution {
    /**
     * 主函式：將字串 s 切分成所有可能的回文子串組合
     * @param s 輸入字串
     * @return   所有合法的回文分割方案
     */
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();  // 用來儲存最終結果
        List<String> part = new ArrayList<>();       // 當前遞迴路徑上的回文片段
        dfs(0, s, part, res);                        // 從字串第 0 位開始回溯
        return res;
    }

    /**
     * 回溯函式：嘗試從位置 i 開始，切出所有以回文為單位的子串
     * @param i     當前起始索引
     * @param s     原始字串
     * @param part  當前已選的回文子串列表
     * @param res   儲存所有合法分割結果
     */
    private void dfs(int i, String s, List<String> part, List<List<String>> res) {
        // 如果已經切到字串末尾，說明 part 中一整套分割合法，加入結果
        if (i >= s.length()) {
            res.add(new ArrayList<>(part));  // 複製一份並加入
            return;
        }
        // 從 i 到 j 嘗試切出所有可能的子串 s[i..j]
        for (int j = i; j < s.length(); j++) {
            // 只有當 s[i..j] 是回文時，才繼續分割
            if (isPali(s, i, j)) {
                part.add(s.substring(i, j + 1));  // 選擇這段回文片段 (i~j的substring)
                dfs(j + 1, s, part, res);         // 從 j+1 繼續（從上個substring後繼續）
                part.remove(part.size() - 1);     // 回溯：撤銷剛加入的片段
            }
        }
    }

    /**
     * 檢查子串 s[l..r] 是否為回文
     * @param s 原始字串
     * @param l 起始索引
     * @param r 結束索引
     * @return  是否為回文
     */
    private boolean isPali(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}

/*
🧪 範例演示 (s = "aab")：

呼叫 partition("aab") → dfs(0, ...)

1. dfs(0, "aab", []):
   j=0 → s[0..0]="a" 是回文
     part=["a"]
     dfs(1, "aab", ["a"]):
       j=1 → s[1..1]="a" 是回文
         part=["a","a"]
         dfs(2, "aab", ["a","a"]):
           j=2 → s[2..2]="b" 是回文
             part=["a","a","b"]
             dfs(3, "aab", ["a","a","b"]):
               i=3 >= len → 加入 ["a","a","b"]
             回溯 → part=["a","a"]
           無更多 j → 返回
         回溯 → part=["a"]
       j=2 → s[1..2]="ab" 不是回文 → 跳過
       返回
     回溯 → part=[]
   j=1 → s[0..1]="aa" 是回文
     part=["aa"]
     dfs(2, "aab", ["aa"]):
       j=2 → s[2..2]="b" 是回文
         part=["aa","b"]
         dfs(3, ...): 加入 ["aa","b"]
       回溯 → part=["aa"]
     返回
   j=2 → s[0..2]="aab" 不是回文 → 跳過
   返回

最終 res = [
  ["a","a","b"],
  ["aa","b"]
]
*/