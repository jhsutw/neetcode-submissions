class Solution {
    private void backtrack(int openN, int closedN, int n, List<String> res, StringBuilder stack) {
        // 終止條件：當左括號數量等於右括號 且 左括號數量等於n，加入res
        if (openN == closedN && openN == n){
            res.add(stack.toString());
            return;
        }

        // 若左括號數量小於n，加入左括號，並進入遞迴
        if (openN < n){
            stack.append('(');
            backtrack(openN + 1, closedN, n, res, stack);
            // 到達終止條件以後，開始回溯，開始嘗試別條路
            stack.deleteCharAt(stack.length() - 1);
        }

        if (closedN < openN){
            stack.append(')');
            backtrack(openN, closedN + 1, n, res, stack);
            stack.deleteCharAt(stack.length() - 1);
        }

    }
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        StringBuilder stack = new StringBuilder();
        backtrack(0, 0, n, res, stack);
        return res;
    }
}

/*
i.e. n = 2
1. 初始：stack = ""
2. 加左：stack = "("
3. 再加左：stack = "(("
4. 加右：stack = "(()"
5. 再加右：stack = "(())" ← ✅ 合法，加入結果
6. 回溯：刪掉最後一個 ) → stack = "(()"
7. 回溯：刪掉最後一個 ) → stack = "(("（回到加第一個右括號之前）
現在，我們才可以繼續試第二條路線！也就是：
8. 回溯：刪掉 ( → stack = "("
9. 加右：stack = "()"
10. 加左：stack = "()("
11. 加右：stack = "()()" ← ✅ 合法，加入結果
*/
