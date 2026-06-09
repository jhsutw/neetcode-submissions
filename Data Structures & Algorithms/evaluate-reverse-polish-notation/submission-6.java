class Solution {
    public int evalRPN(String[] tokens) {
        List<String> tokenList = new ArrayList<>(Arrays.asList(tokens));
        return dfs(tokenList);
    }

    private int dfs(List<String> tokens){
        String token = tokens.remove(tokens.size() - 1);

        // 若token非運算符號，就讓right/ left在遞迴運算時回傳整數值
        if (!"+-*/".contains(token)){
            return Integer.parseInt(token);
        }

        // 進入遞迴取出運算符號前一個元素為right、前兩個元素為left
        int right = dfs(tokens);
        int left = dfs(tokens);

        // 根據符號做運算（跟else if的寫法一樣，這個寫法更簡潔）
        switch (token) {
            case "+":
                return left + right;
            case "-":
                return left - right;
            case "*":
                return left * right;
            case "/":
                return left / right;
        }

        return 0;
    }
}

/*
以輸入 ["2", "1", "+", "3", "*"] 為例，等價於 (2 + 1) * 3 = 9

運作順序：
1. dfs 拿到 "*"，進入 switch
2. 接著遞迴求 right = dfs(["2", "1", "+", "3"]) → 得到 3
3. 再遞迴求 left = dfs(["2", "1", "+"])
4. 拿到 "+"
5. right = 1、left = 2 → return 3
6. "*"：left=3, right=3 → 回傳 3 * 3 = 9
（先得到token"*", 得到right"3", 得到left“1 + 2"）
（拆解left：先得到"+"，得到right"2", 得到left"1"）
*/
