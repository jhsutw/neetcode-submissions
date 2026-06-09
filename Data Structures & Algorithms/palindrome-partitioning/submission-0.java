public class Solution {
    // 存放所有合法的分割結果
    private List<List<String>> res = new ArrayList<>();
    // 臨時存放當前路徑上的回文子串
    private List<String> part = new ArrayList<>();

    public List<List<String>> partition(String s) {
        // 從索引 j=0, i=0 開始 DFS
        dfs(0, 0, s);
        return res;
    }

    /**
     * @param j   當前子串起始位置
     * @param i   當前子串結束位置
     * @param s   原始字串
     */
    private void dfs(int j, int i, String s) {
        // 當 i 超過字串長度
        if (i >= s.length()) {
            // 只有在 j 也剛好走到末尾時才加入結果
            if (i == j) {
                res.add(new ArrayList<>(part));
            }
            return;
        }

        // 如果 s[j..i] 是回文
        if (isPali(s, j, i)) {
            // 將它（i ~ j的substring）加入當前分割路徑
            part.add(s.substring(j, i + 1));
            // 從下一個位置重新開始分割（從上個substring的下一個char開始往後找）
            dfs(i + 1, i + 1, s);
            // 回溯：移除剛剛加入的子串
            part.remove(part.size() - 1);
        }

        // 無論是不是回文，都要嘗試讓 i 往前推進（擴大substing範圍）
        dfs(j, i + 1, s);
    }

    /**
     * 檢查 s[l..r] 是否為回文
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
🧪 範例執行 (s = "aab")：

初始呼叫 dfs(0,0,"aab")：

1. i=0, j=0 → 檢查 "a" 是回文
   └─ part = ["a"], 呼叫 dfs(1,1)
      2. i=1, j=1 → 檢查 "a" 是回文
         └─ part = ["a","a"], 呼叫 dfs(2,2)
            3. i=2, j=2 → 檢查 "b" 是回文
               └─ part = ["a","a","b"], 呼叫 dfs(3,3)
                  → i >= len 且 i==j → 加入 ["a","a","b"]
            └─ 回溯，part = ["a","a"]
         └─ dfs(1,2) 跳過 j→1, i->2
            檢查 "ab" 不是回文 → 不加入
            再呼叫 dfs(1,3) 結束
      └─ 回溯，part = ["a"]
      └─ dfs(0,2) 檢查 "aa" 是回文
         └─ part = ["aa"], 呼叫 dfs(2,2)
            i=2, j=2 → "b" 是回文
            └─ part = ["aa","b"], 呼叫 dfs(3,3)
               → 加入 ["aa","b"]
         └─ 回溯，part = ["aa"]
      └─ dfs(0,3) 結束
2. dfs(0,1), dfs(0,2) 等皆走完

最終結果 res = [
  ["a","a","b"],
  ["aa","b"]
]
*/
