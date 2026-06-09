public class Solution {
    public List<String> generateParenthesis(int n) {
        // 建立一個二維 list，res.get(k) 代表 k 對括號的所有合法組合
        List<List<String>> res = new ArrayList<>();

        // 初始化：為每個括號數量都配置一個空的 list
        for (int i = 0; i <= n; i++) {
            res.add(new ArrayList<>());
        }

        // base case：0 對括號時只有空字串是合法的
        res.get(0).add("");

        // 外層迴圈從 1 到 n，代表當前要生成 k 對括號的組合
        for (int k = 0; k <= n; k++) {

            // 嘗試將 k 拆成 左 i 對 和 右 (k - i - 1) 對
            for (int i = 0; i < k; i++) {

                // 對左邊 i 對括號的所有組合
                for (String left : res.get(i)) {

                    // 對右邊 (k - i - 1) 對括號的所有組合
                    for (String right : res.get(k - i - 1)) {

                        // 將左側組合用括號包起來 + 右側組合
                        // 形成一個合法的 k 對括號組合，加入 res[k]
                        res.get(k).add("(" + left + ")" + right);
                    }
                }
            }
        }

        // 回傳 n 對括號的所有合法組合
        return res.get(n);
    }
}
/*
🧠 補充理解（以 n = 3 為例）
res[0] = [""]
res[1] = ["()"]
res[2] = ["()()", "(())"]
res[3] = ["()()()", "()(())", "(())()", "(()())", "((()))"]
*/