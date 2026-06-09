// brute force
class Solution {
    public boolean valid(String s) {
        int open = 0;
        for (char c : s.toCharArray()) {
            /* 相同於下面寫法
            if (c == '(') {
                open += 1;
            } else {
                open -= 1;
            }
            */
            open += c == '(' ? 1 : -1; // '(' 加一 / ')' 減一
            if (open < 0) return false; // 如果右括號過多，就不合法
        }
        return open == 0; // 最後必須剛好配對完畢才合法
    }

    // 用遞迴方式加入新的"("或")"，同時判斷是否符合長度以及valid兩條件
    void dfs (String s, List<String> res, int n) {
        // 終止條件：長度要等於n * 2因為一組有"("和")""
        if (n * 2 == s.length()){
            // 丟入valid的function做判斷，符合則加入res
            if (valid(s)) res.add(s);
            return;
        }
        // 會重複進入遞迴直到觸發終止條件
        // 每一層遞迴都同時嘗試加一個 '(' 或 ')'。
        dfs(s + '(', res, n);
        dfs(s + ')', res, n);
    }

    public List<String> generateParenthesis(int n) {
        // 創建一個ArrayList放res
        List<String> res = new ArrayList<>();
        dfs("", res, n);
        return res;
    }
}
